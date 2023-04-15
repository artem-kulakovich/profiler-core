package by.bntu.fitr.core.context;


import by.bntu.fitr.core.config.KafkaProducerConfig;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ApplicationContext {
    private static ApplicationContext instance;
    private ClassLoader classLoader;
    private Properties properties;
    private List<Class> classesWithMetricAnnotation;
    private Map<Class, List<String>> classMethodsMarkedWithTimedAnnotation;
    private String packageForScan;
    private String packageForScanRootPath;
    private Map<String, KafkaProducerConfig> kafkaConfigMap;
    private List<String> monitoredDisk;
    private Long jvmMetricCollectRateTime;


    private ApplicationContext() {
    }

    public static synchronized ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<Class> getClassesWithMetricAnnotation() {
        return classesWithMetricAnnotation;
    }

    public void setClassesWithMetricAnnotation(List<Class> classesWithMetricAnnotation) {
        this.classesWithMetricAnnotation = classesWithMetricAnnotation;
    }

    public Map<Class, List<String>> getClassMethodsMarkedWithTimedAnnotation() {
        return classMethodsMarkedWithTimedAnnotation;
    }

    public void setClassMethodsMarkedWithTimedAnnotation(Map<Class, List<String>> classMethodsMarkedWithTimedAnnotation) {
        this.classMethodsMarkedWithTimedAnnotation = classMethodsMarkedWithTimedAnnotation;
    }

    public String getPackageForScan() {
        return packageForScan;
    }

    public void setPackageForScan(String packageForScan) {
        this.packageForScan = packageForScan;
    }

    public String getPackageForScanRootPath() {
        return packageForScanRootPath;
    }

    public void setPackageForScanRootPath(String packageForScanRootPath) {
        this.packageForScanRootPath = packageForScanRootPath;
    }

    public Map<String, KafkaProducerConfig> getKafkaConfigMap() {
        return kafkaConfigMap;
    }

    public void setKafkaConfigMap(Map<String, KafkaProducerConfig> kafkaConfigMap) {
        this.kafkaConfigMap = kafkaConfigMap;
    }

    public List<String> getMonitoredDisk() {
        return monitoredDisk;
    }

    public void setMonitoredDisk(List<String> monitoredDisk) {
        this.monitoredDisk = monitoredDisk;
    }

    public Long getJvmMetricCollectRateTime() {
        return jvmMetricCollectRateTime;
    }

    public void setJvmMetricCollectRateTime(Long jvmMetricCollectRateTime) {
        this.jvmMetricCollectRateTime = jvmMetricCollectRateTime;
    }
}
