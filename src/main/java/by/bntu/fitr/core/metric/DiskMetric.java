package by.bntu.fitr.core.metric;

import by.bntu.fitr.core.constant.CommonConstant;

import java.time.LocalDateTime;

public class DiskMetric {
    private String metricType;
    private String diskName;
    private String path;
    private Long totalSpace;
    private Long usedSpace;
    private Long freeSpace;
    private LocalDateTime startedDate;
    private LocalDateTime endedDate;

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public Long getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(Long usedSpace) {
        this.usedSpace = usedSpace;
    }

    public Long getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Long freeSpace) {
        this.freeSpace = freeSpace;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
    }

    public LocalDateTime getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(LocalDateTime endedDate) {
        this.endedDate = endedDate;
    }

    @Override
    public String toString() {
        return "DiskMetric{" +
                "diskName='" + diskName + '\'' +
                ", path='" + path + '\'' +
                ", totalSpace=" + totalSpace +
                ", usedSpace=" + usedSpace +
                ", freeSpace=" + freeSpace +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                '}';
    }

    public String onlyValues() {
        return "{" + metricType + CommonConstant.DELIMETER +
                diskName + CommonConstant.DELIMETER +
                path + CommonConstant.DELIMETER +
                usedSpace + CommonConstant.DELIMETER +
                totalSpace + CommonConstant.DELIMETER +
                freeSpace + CommonConstant.DELIMETER +
                startedDate + CommonConstant.DELIMETER +
                endedDate + "}";
    }

}
