package by.bntu.fitr.core.provider;

import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.metric.DiskMetric;
import by.bntu.fitr.core.metric.GarbageCollectorMetric;
import by.bntu.fitr.core.metric.HeapMetric;
import by.bntu.fitr.core.metric.JVMMetric;

import java.io.File;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JVMApiProvider {
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();

    public GarbageCollectorMetric getGarbageCollectorMetric() {
        Long totalGarbageCollectionsCount = 0L;
        Long totalGarbageCollectorExecutionTime = 0L;
        LocalDateTime startedDate = LocalDateTime.now();


        for (GarbageCollectorMXBean gc : ManagementFactory.getGarbageCollectorMXBeans()) {
            totalGarbageCollectionsCount += gc.getCollectionCount();
            totalGarbageCollectorExecutionTime += gc.getCollectionTime();
        }

        LocalDateTime endedDate = LocalDateTime.now();
        GarbageCollectorMetric garbageCollectorMetric = new GarbageCollectorMetric();
        garbageCollectorMetric.setMetricType(CommonConstant.GARBAGE_COLLECTOR_METRIC_TYPE);
        garbageCollectorMetric.setTotalGarbageCount(totalGarbageCollectionsCount);
        garbageCollectorMetric.setTotalGarbageExecutionTime(totalGarbageCollectorExecutionTime);
        garbageCollectorMetric.setStartedDate(startedDate);
        garbageCollectorMetric.setEndedDate(endedDate);

        return garbageCollectorMetric;
    }

    public List<DiskMetric> getDisksSpaceMetric() {
        List<DiskMetric> diskMetrics = new ArrayList<>();

        for (String monitoredDisk : applicationContext.getMonitoredDisk()) {
            LocalDateTime startedDate = LocalDateTime.now();
            File disk = new File(monitoredDisk);
            DiskMetric diskMetric = new DiskMetric();

            diskMetric.setMetricType(CommonConstant.DISK_METRIC_TYPE);
            diskMetric.setDiskName(monitoredDisk.substring(0, 1));
            diskMetric.setPath(disk.getPath());
            diskMetric.setFreeSpace(disk.getFreeSpace() / CommonConstant.MB);
            diskMetric.setTotalSpace(disk.getTotalSpace() / CommonConstant.MB);
            diskMetric.setUsedSpace(disk.getUsableSpace() / CommonConstant.MB);
            LocalDateTime endedDate = LocalDateTime.now();
            diskMetric.setStartedDate(startedDate);
            diskMetric.setEndedDate(endedDate);

            diskMetrics.add(diskMetric);
        }
        return diskMetrics;
    }

    public HeapMetric getHeapMetric() {
        LocalDateTime startedDate = LocalDateTime.now();
        Runtime runtime = Runtime.getRuntime();
        HeapMetric heapMetric = new HeapMetric();
        heapMetric.setMetricType(CommonConstant.HEAP_METRIC_TYPE);
        heapMetric.setUsedMemory((runtime.totalMemory() - runtime.freeMemory()) / CommonConstant.MB);
        heapMetric.setFreeMemory(runtime.freeMemory() / CommonConstant.MB);
        heapMetric.setAvailableMemory(runtime.totalMemory() / CommonConstant.MB);
        heapMetric.setMaxAvailableMemory(runtime.maxMemory() / CommonConstant.MB);
        LocalDateTime endedDate = LocalDateTime.now();
        heapMetric.setStartedDate(startedDate);
        heapMetric.setEndedDate(endedDate);

        return heapMetric;
    }

    public JVMMetric getJVMMetric() {
        JVMMetric jvmMetric = new JVMMetric();
        jvmMetric.setGarbageCollectorMetric(getGarbageCollectorMetric());
        jvmMetric.setDisksMetric(getDisksSpaceMetric());
        jvmMetric.setHeapMetric(getHeapMetric());
        return jvmMetric;
    }
}
