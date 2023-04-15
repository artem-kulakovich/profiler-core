package by.bntu.fitr.core.constant;

public interface CommonConstant {
    //Profiler properties constants.
    String PROPERTY_FILE_NAME = "profiler.properties";
    String PROFILER_SCAN_PACKAGES_ATTR = "profiler.metrics.scan-packages";
    String PROFILER_DATABASE_CONNECTION_HOST = "profiler.metrics.database.connection.host";
    String PROFILER_DATABASE_CONNECTION_USER = "profiler.metrics.database.connection.user";
    String PROFILER_DATABASE_CONNECTION_PASSWORD = "profiler.metrics.database.connection.password";
    String PROFILER_METRICS_COLLECT_DATABASE = "profiler.metrics.collect-database";
    String PROFILER_MONITOR_DISK_SPACE = "profiler.monitor-disk-space";
    String PROFILER_JVM_METRIC_RATE_TIME = "profiler.jvm-metric.rate-time";


    String PROFILER_KAFKA_PREFIX = "profiler.kafka";
    String TOPIC = "topic";
    String BOOTSTRAP_SERVER = "bootstrap.servers";
    String KEY_SERIALIZER = "key.serializer";
    String VALUE_SERIALIZER = "value.serializer";
    String NUMS_PARTITION = "num.partitions";
    String SESSION_TIMEOUT_MS = "session.timeout.ms";
    String ACKS = "acks";
    String HEART_BEAT_INTERVAL_MS = "heartbeat.interval.ms";
    String PROFILER_KAFKA_TIMED_METRIC_PREFIX = "timed-metric";
    String PROFILER_KAFKA_COUNTER_METRIC_PREFIX = "counter-metric";
    String PROFILER_KAFKA_JVM_METRIC_PREFIX = "jvm-metric";

    //Util constants.
    String SLASH_SEPARATOR = "/";
    String DOT_SEPARATOR = "\\.";

    //Core constants.
    String METRIC_ANNOTATION_NAME = "Metric";
    String TIMED_ANNOTATION_NAME = "Timed";
    String TIMED_METRIC_TYPE = "Timed metric";
    String COUNTER_METRIC_TYPE = "Counter metric";
    String GARBAGE_COLLECTOR_METRIC_TYPE = "Garbage collector metric";
    String DISK_METRIC_TYPE = "Disk metric type";
    String HEAP_METRIC_TYPE = "Heap metric type";


    Long MB = 1024L * 1024L;

    String DELIMETER = ",";
}
