package by.bntu.fitr.core.kafka.producer;

import by.bntu.fitr.core.config.KafkaProducerConfig;
import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.metric.TimedMetric;
import by.bntu.fitr.core.provider.PropertyApiProvider;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaFuture;

import java.util.Collections;


public class KafkaTimedMetricProducer extends GeneralKafkaProducer<TimedMetric> {
    private KafkaProducer<String, String> kafkaTimedMetricProducer;

    public KafkaTimedMetricProducer(PropertyApiProvider propertyApiProvider) {
        super(propertyApiProvider, CommonConstant.PROFILER_KAFKA_TIMED_METRIC_PREFIX);
        kafkaTimedMetricProducer = new KafkaProducer<String, String>(kafkaProperties);
        kafkaProducerGlobalStorage.setKafkaTimedMetricProducer(this);
    }

    @Override
    public void send(TimedMetric message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaProducerConfig.getTopicName(), message.onlyValues());
        try {
            kafkaTimedMetricProducer.send(record);
        } catch (Exception e) {

        }
    }
}
