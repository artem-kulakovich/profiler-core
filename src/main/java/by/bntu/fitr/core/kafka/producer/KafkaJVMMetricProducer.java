package by.bntu.fitr.core.kafka.producer;

import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.metric.JVMMetric;
import by.bntu.fitr.core.metric.TimedMetric;
import by.bntu.fitr.core.provider.PropertyApiProvider;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaJVMMetricProducer extends GeneralKafkaProducer<JVMMetric> {
    private KafkaProducer<String, String> kafkaJVMMetricProducer;

    public KafkaJVMMetricProducer(PropertyApiProvider propertyApiProvider) {
        super(propertyApiProvider, CommonConstant.PROFILER_KAFKA_JVM_METRIC_PREFIX);
        kafkaJVMMetricProducer = new KafkaProducer<String, String>(kafkaProperties);
        kafkaProducerGlobalStorage.setKafkaJVMMetricProducer(this);
    }

    @Override
    public void send(JVMMetric message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaProducerConfig.getTopicName(), message.onlyValues());
        try {
            kafkaJVMMetricProducer.send(record);
        } catch (Exception e) {

        }
    }
}
