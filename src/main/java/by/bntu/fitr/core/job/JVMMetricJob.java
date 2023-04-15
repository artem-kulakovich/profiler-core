package by.bntu.fitr.core.job;

import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.kafka.producer.KafkaJVMMetricProducer;
import by.bntu.fitr.core.kafka.producer.KafkaProducerGlobalStorage;
import by.bntu.fitr.core.metric.JVMMetric;
import by.bntu.fitr.core.provider.JVMApiProvider;

public class JVMMetricJob extends Thread {
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();
    private final KafkaJVMMetricProducer kafkaJVMMetricProducer = KafkaProducerGlobalStorage.getInstance().getKafkaJVMMetricProducer();
    private final JVMApiProvider jvmApiProvider;

    public JVMMetricJob(JVMApiProvider jvmApiProvider) {
        this.jvmApiProvider = jvmApiProvider;
    }

    @Override
    public void run() {
        while (true) {
            collectJVMMetric();
        }
    }

    public void collectJVMMetric() {
        JVMMetric jvmMetric = jvmApiProvider.getJVMMetric();

        System.out.println(jvmMetric.toString());
        kafkaJVMMetricProducer.send(jvmMetric);

        try {
            Thread.sleep(applicationContext.getJvmMetricCollectRateTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
