package by.bntu.fitr.core.metric;

import by.bntu.fitr.core.constant.CommonConstant;

import java.time.LocalDateTime;

public class GarbageCollectorMetric {
    private String metricType;
    private Long totalGarbageCount;
    private Long totalGarbageExecutionTime;
    private LocalDateTime startedDate;
    private LocalDateTime endedDate;

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public Long getTotalGarbageCount() {
        return totalGarbageCount;
    }

    public void setTotalGarbageCount(Long totalGarbageCount) {
        this.totalGarbageCount = totalGarbageCount;
    }

    public Long getTotalGarbageExecutionTime() {
        return totalGarbageExecutionTime;
    }

    public void setTotalGarbageExecutionTime(Long totalGarbageExecutionTime) {
        this.totalGarbageExecutionTime = totalGarbageExecutionTime;
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
        return "GarbageCollectorMetric{" +
                "totalGarbageCount=" + totalGarbageCount +
                ", garbageExecutionTime=" + totalGarbageExecutionTime +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                '}';
    }

    public String onlyValues() {
        return metricType + CommonConstant.DELIMETER +
                totalGarbageCount + CommonConstant.DELIMETER +
                totalGarbageExecutionTime + CommonConstant.DELIMETER +
                startedDate + CommonConstant.DELIMETER +
                endedDate;
    }
}