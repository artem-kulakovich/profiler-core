package by.bntu.fitr.core.bytecode;


import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.kafka.producer.KafkaProducerGlobalStorage;
import by.bntu.fitr.core.metric.TimedMetric;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


public class TimedMetricAdvice {

    /*
    @Advice.OnMethodEnter(suppress = Throwable.class)
    public static void onMethodEnter(@Advice.This Object thisObject,
                                     @Advice.Origin Method method) {
        TimedMetric timedMetric = new TimedMetric();
        timedMetric.setMethodName(method.getName());
        timedMetric.setClassName(String.valueOf(thisObject.getClass()));
        timedMetric.setStartedDate(LocalDateTime.now());
        timedMetric.setExecutionTimeStart(System.currentTimeMillis());
        MetricRegistry.getInstance().getTimedMetricRegistry().addMetric(timedMetric);

    }

    @Advice.OnMethodExit(suppress = Throwable.class, onThrowable = Throwable.class)
    public static void onMethodExit() {
        TimedMetric timedMetric = MetricRegistry.getInstance().getTimedMetricRegistry().getTimedMetric();
        timedMetric.setEndedDate(LocalDateTime.now());
        timedMetric.setExecutionTimeEnd(System.currentTimeMillis());
    }

     */

    @Advice.OnMethodEnter
    public static TimedMetric enter() throws Exception {
        TimedMetric timedMetric = new TimedMetric();
        timedMetric.setExecutionTimeStart(System.currentTimeMillis());
        return timedMetric;
    }

    @Advice.OnMethodExit
    public static void exit(@Advice.Origin Method method,
                            @Advice.This Object object,
                            @Advice.Enter TimedMetric timedMetric) throws Exception {
        long end = System.currentTimeMillis();
        timedMetric.setEndedDate(LocalDateTime.now());
        timedMetric.setMethodName(method.getName());
        timedMetric.setMetricType(CommonConstant.TIMED_METRIC_TYPE);
        timedMetric.setExecutionTimeEnd(end);
        timedMetric.setClassName(object.getClass().getName());
        System.out.println(timedMetric);
        KafkaProducerGlobalStorage.getInstance().getKafkaTimedMetricProducer().send(timedMetric);
    }
    /*
    is working
    @RuntimeType
    public static Object intercept(@This Object self) {

        return null;
    }

    /*
    @Advice.OnMethodEnter
    static long invokeBeforeEnterMethod(
            @Advice.Origin String method) {
        ServiceManager.getInstance().getMetricRegistry().addMetric(new TimedMetric().setStartedDate(System.currentTimeMillis()));
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    static void invokeAfterExitMethod(
            @Advice.Origin String method,
            @Advice.Enter long startTime
    ) {
        System.out.println("Method " + method + " took " + (System.currentTimeMillis() - startTime) + "ms");
    }

     */
}


