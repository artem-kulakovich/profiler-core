package by.bntu.fitr.core.metric;

import by.bntu.fitr.core.constant.CommonConstant;

import java.time.LocalDateTime;

public class HeapMetric {
    private String metricType;
    private Long usedMemory;
    private Long freeMemory;
    private Long availableMemory;
    private Long maxAvailableMemory;
    private LocalDateTime startedDate;
    private LocalDateTime endedDate;

    public HeapMetric() {
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public Long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(Long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public Long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(Long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public Long getAvailableMemory() {
        return availableMemory;
    }

    public void setAvailableMemory(Long availableMemory) {
        this.availableMemory = availableMemory;
    }

    public Long getMaxAvailableMemory() {
        return maxAvailableMemory;
    }

    public void setMaxAvailableMemory(Long maxAvailableMemory) {
        this.maxAvailableMemory = maxAvailableMemory;
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
        return "HeapMetric{" +
                "usedMemory=" + usedMemory +
                ", freeMemory=" + freeMemory +
                ", availableMemory=" + availableMemory +
                ", maxAvailableMemory=" + maxAvailableMemory +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                '}';
    }

    public String onlyValues() {
        return metricType + CommonConstant.DELIMETER +
                usedMemory + CommonConstant.DELIMETER +
                freeMemory + CommonConstant.DELIMETER +
                availableMemory + CommonConstant.DELIMETER +
                maxAvailableMemory + CommonConstant.DELIMETER +
                startedDate + CommonConstant.DELIMETER +
                endedDate;
    }
}
