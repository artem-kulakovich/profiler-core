package by.bntu.fitr.core.facade;

import by.bntu.fitr.core.annotations.Metric;
import by.bntu.fitr.core.annotations.Timed;
import by.bntu.fitr.core.bytecode.ByteBuddyExecutor;
import by.bntu.fitr.core.bytecode.ByteCodeExecutor;
import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.exception.ProfilerCoreException;
import by.bntu.fitr.core.job.JVMMetricJob;
import by.bntu.fitr.core.kafka.producer.KafkaJVMMetricProducer;
import by.bntu.fitr.core.kafka.producer.KafkaTimedMetricProducer;
import by.bntu.fitr.core.provider.JVMApiProvider;
import by.bntu.fitr.core.provider.PropertyApiProvider;
import by.bntu.fitr.core.provider.ReflectionApiProvider;
import by.bntu.fitr.core.util.FileUtil;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StartApplicationFacade {
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();
    private final PropertyApiProvider propertyApiProvider = new PropertyApiProvider();
    private final ReflectionApiProvider reflectionApiProvider = new ReflectionApiProvider();

    public void startUp() {
        ClassLoader classLoader = StartApplicationFacade.class.getClassLoader();
        applicationContext.setClassLoader(classLoader);

        //Getting path to profiler.property file
        URL propertyFilePath = getPropertyFilePath(CommonConstant.PROPERTY_FILE_NAME);

        //Load property file
        propertyApiProvider.loadProperties(propertyFilePath.getPath());

        applicationContext.setProperties(propertyApiProvider.getProperties());
        applicationContext.setPackageForScan(propertyApiProvider.getProperty(CommonConstant.PROFILER_SCAN_PACKAGES_ATTR));
        applicationContext.setMonitoredDisk(Arrays.stream(propertyApiProvider.getProperty(CommonConstant.PROFILER_MONITOR_DISK_SPACE).split(",")).map(String::trim).collect(Collectors.toList()));
        applicationContext.setJvmMetricCollectRateTime(Long.valueOf(propertyApiProvider.getProperty(CommonConstant.PROFILER_JVM_METRIC_RATE_TIME)));

        URL packageForScanRootPath = getPackageForScanRootPath();
        applicationContext.setPackageForScanRootPath(packageForScanRootPath.getPath());


        //Getting classes marked with Metric annotation
        List<Class> classesWithMetricAnnotation = getClassesMarkedWithAnnotation(Metric.class.getName());
        applicationContext.setClassesWithMetricAnnotation(classesWithMetricAnnotation);

        //Getting methods marked with timed annotation in class marked with metric annotation
        Map<Class, List<String>> methodsWithTimedAnnotation = getClassesAndMethodsMarkedWithAnnotation(Timed.class.getName());
        applicationContext.setClassMethodsMarkedWithTimedAnnotation(methodsWithTimedAnnotation);

        ByteCodeExecutor byteCodeExecutor = new ByteBuddyExecutor();
        byteCodeExecutor.redefineMethodForTimedMetric(applicationContext.getClassMethodsMarkedWithTimedAnnotation(), applicationContext.getClassLoader());

        KafkaTimedMetricProducer kafkaTimedMetricProducer = new KafkaTimedMetricProducer(propertyApiProvider);
        KafkaJVMMetricProducer kafkaJVMMetricProducer = new KafkaJVMMetricProducer(propertyApiProvider);

        JVMApiProvider jvmApiProvider = new JVMApiProvider();
        JVMMetricJob jvmMetricJob = new JVMMetricJob(jvmApiProvider);
        jvmMetricJob.setName("jvmMetricCollectorThread");
        jvmMetricJob.start();
    }

    private URL getPropertyFilePath(String propertyFileName) {
        URL propertyFilePath = applicationContext.getClassLoader().getResource(CommonConstant.PROPERTY_FILE_NAME);

        if (propertyFilePath == null) {
            throw new ProfilerCoreException("");
        }

        return propertyFilePath;
    }

    private URL getPackageForScanRootPath() {
        URL packageForScanRootPath = applicationContext.getClassLoader().getResource(FileUtil.changeSeparator(applicationContext.getPackageForScan(),
                CommonConstant.DOT_SEPARATOR,
                CommonConstant.SLASH_SEPARATOR));

        if (packageForScanRootPath == null) {
            throw new ProfilerCoreException("");
        }

        return packageForScanRootPath;
    }

    private List<Class> getClassesMarkedWithAnnotation(String annotationName) {
        return reflectionApiProvider.getClassesMarkedWithAnnotation(applicationContext.getPackageForScanRootPath(),
                applicationContext.getPackageForScan(),
                annotationName);
    }

    private Map<Class, List<String>> getClassesAndMethodsMarkedWithAnnotation(String annotationName) {
        return reflectionApiProvider.getMethodsMarkedWithAnnotations(applicationContext.getClassesWithMetricAnnotation(), annotationName);
    }
}
