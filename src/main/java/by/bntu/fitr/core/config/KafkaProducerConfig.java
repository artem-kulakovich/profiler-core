package by.bntu.fitr.core.config;

import java.util.Properties;

public class KafkaProducerConfig {
    private String bootstrapServer;
    private String topicName;
    private String keySerializer;
    private String valueSerializer;
    private int numOfPartition;
    private String acks;
    private Integer sessionTimeOut;
    private Integer heartBeatTimeOut;

    public KafkaProducerConfig() {
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public void setBootstrapServer(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public int getNumOfPartition() {
        return numOfPartition;
    }

    public void setNumOfPartition(int numOfPartition) {
        this.numOfPartition = numOfPartition;
    }

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public Integer getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(Integer sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public Integer getHeartBeatTimeOut() {
        return heartBeatTimeOut;
    }

    public void setHeartBeatTimeOut(Integer heartBeatTimeOut) {
        this.heartBeatTimeOut = heartBeatTimeOut;
    }
}
