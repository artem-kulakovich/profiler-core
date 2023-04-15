package by.bntu.fitr.core.kafka.producer;

import java.util.*;

public class KafkaProducerGlobalStorage {
    public KafkaJVMMetricProducer getKafkaJVMMetricProducer() {
        return kafkaJVMMetricProducer;
    }

    public void setKafkaJVMMetricProducer(KafkaJVMMetricProducer kafkaJVMMetricProducer) {
        this.kafkaJVMMetricProducer = kafkaJVMMetricProducer;
    }

    private static KafkaProducerGlobalStorage instance;
    private KafkaTimedMetricProducer kafkaTimedMetricProducer;
    private KafkaJVMMetricProducer kafkaJVMMetricProducer;

    private KafkaProducerGlobalStorage() {
    }

    public synchronized static KafkaProducerGlobalStorage getInstance() {
        if (instance == null) {
            instance = new KafkaProducerGlobalStorage();
        }

        return instance;
    }

    public KafkaTimedMetricProducer getKafkaTimedMetricProducer() {
        return kafkaTimedMetricProducer;
    }

    public void setKafkaTimedMetricProducer(KafkaTimedMetricProducer kafkaTimedMetricProducer) {
        this.kafkaTimedMetricProducer = kafkaTimedMetricProducer;
    }
}
