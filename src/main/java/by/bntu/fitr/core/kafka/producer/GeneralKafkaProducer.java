package by.bntu.fitr.core.kafka.producer;

import by.bntu.fitr.core.config.KafkaProducerConfig;
import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.provider.PropertyApiProvider;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class GeneralKafkaProducer<T> {
    protected final ApplicationContext applicationContext = ApplicationContext.getInstance();
    protected final KafkaProducerGlobalStorage kafkaProducerGlobalStorage = KafkaProducerGlobalStorage.getInstance();
    protected KafkaProducerConfig kafkaProducerConfig;
    protected PropertyApiProvider propertyApiProvider;
    protected Properties kafkaProperties;
    protected String prefix;

    public GeneralKafkaProducer(PropertyApiProvider propertyApiProvider, String prefix) {
        this.propertyApiProvider = propertyApiProvider;
        this.prefix = prefix;
        this.kafkaProducerConfig = initKafkaConfig(prefix);
        this.kafkaProperties = mapKafkaConfigsToKafkaProperties(this.kafkaProducerConfig);
        createTopic(this.kafkaProperties);
    }

    protected abstract void send(T message);

    private KafkaProducerConfig initKafkaConfig(String prefix) {
        KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();

        String acksPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, prefix, CommonConstant.ACKS);
        String keySerializerPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, prefix, CommonConstant.KEY_SERIALIZER);
        String valueSerializerPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, prefix, CommonConstant.VALUE_SERIALIZER);
        String topicPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, prefix, CommonConstant.TOPIC);
        String partitionNumsPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, prefix, CommonConstant.NUMS_PARTITION);
        String sessionTimeOutPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, prefix, CommonConstant.SESSION_TIMEOUT_MS);
        String heartBeatTimeOutPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, prefix, CommonConstant.HEART_BEAT_INTERVAL_MS);
        String bootstrapServerPropertyName = buildPrefixDividedByDot(CommonConstant.PROFILER_KAFKA_PREFIX, CommonConstant.BOOTSTRAP_SERVER);

        for (Map.Entry<Object, Object> propertyEntry : propertyApiProvider.getProperties().entrySet()) {
            String propertyKey = (String) propertyEntry.getKey();
            String propertyValue = (String) propertyEntry.getValue();

            if (propertyKey.contains(acksPropertyName)) {
                kafkaProducerConfig.setAcks(propertyValue);
            } else if (propertyKey.contains(keySerializerPropertyName)) {
                kafkaProducerConfig.setKeySerializer(propertyValue);
            } else if (propertyKey.contains(valueSerializerPropertyName)) {
                kafkaProducerConfig.setValueSerializer(propertyValue);
            } else if (propertyKey.contains(topicPropertyName)) {
                kafkaProducerConfig.setTopicName(propertyValue);
            } else if (propertyKey.contains(partitionNumsPropertyName)) {
                kafkaProducerConfig.setNumOfPartition(Integer.parseInt(propertyValue));
            } else if (propertyKey.contains(sessionTimeOutPropertyName)) {
                kafkaProducerConfig.setSessionTimeOut(Integer.valueOf(propertyValue));
            } else if (propertyKey.contains(heartBeatTimeOutPropertyName)) {
                kafkaProducerConfig.setHeartBeatTimeOut(Integer.valueOf(propertyValue));
            } else if (propertyKey.contains(bootstrapServerPropertyName)) {
                kafkaProducerConfig.setBootstrapServer(propertyValue);
            }
        }

        return kafkaProducerConfig;
    }

    protected Properties mapKafkaConfigsToKafkaProperties(KafkaProducerConfig kafkaProducerConfig) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put(CommonConstant.BOOTSTRAP_SERVER, kafkaProducerConfig.getBootstrapServer());
        kafkaProperties.put(CommonConstant.KEY_SERIALIZER, kafkaProducerConfig.getKeySerializer());
        kafkaProperties.put(CommonConstant.VALUE_SERIALIZER, kafkaProducerConfig.getValueSerializer());
        kafkaProperties.put(CommonConstant.NUMS_PARTITION, kafkaProducerConfig.getNumOfPartition());
        kafkaProperties.put(CommonConstant.ACKS, kafkaProducerConfig.getAcks());
        kafkaProperties.put(CommonConstant.HEART_BEAT_INTERVAL_MS, kafkaProducerConfig.getHeartBeatTimeOut());
        kafkaProperties.put(CommonConstant.SESSION_TIMEOUT_MS, kafkaProducerConfig.getSessionTimeOut());
        return kafkaProperties;
    }

    protected void createTopic(Properties kafkaProperties) {
        try (Admin admin = Admin.create(kafkaProperties)) {
            NewTopic newTopic = new NewTopic(kafkaProducerConfig.getTopicName(), kafkaProducerConfig.getNumOfPartition(), (short) 1);
            CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));
        }
    }


    private String buildPrefixDividedByDot(String... prefixes) {
        StringBuilder finalPrefix = new StringBuilder();

        for (int i = 0; i < prefixes.length; i++) {
            if (i == prefixes.length - 1) {
                finalPrefix.append(prefixes[i]);
            } else {
                finalPrefix.append(prefixes[i]).append(".");
            }
        }

        return finalPrefix.toString();
    }
}
