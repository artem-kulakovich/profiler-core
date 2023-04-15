package by.bntu.fitr.core.metric;


import by.bntu.fitr.core.constant.CommonConstant;

import java.time.LocalDateTime;


public class TimedMetric {
    private String metricType;
    private String className;
    private String methodName;
    private Long executionTime;
    private Long executionTimeStart;
    private Long executionTimeEnd;
    private LocalDateTime endedDate;
    private LocalDateTime startedDate = LocalDateTime.now();

    public TimedMetric() {
    }

    public Long getExecutionTime() {
        return executionTimeEnd - executionTimeStart;
    }

    public LocalDateTime getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(LocalDateTime endedDate) {
        this.endedDate = endedDate;
    }

    public void setExecutionTimeStart(Long executionTimeStart) {
        this.executionTimeStart = executionTimeStart;
    }

    public void setExecutionTimeEnd(Long executionTimeEnd) {
        this.executionTimeEnd = executionTimeEnd;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "{executionTime=" + getExecutionTime() +
                ", executionTimeStart=" + executionTimeStart +
                ", executionTimeEnd=" + executionTimeEnd +
                ", endedDate=" + endedDate +
                ", startedDate=" + startedDate +
                ", methodName='" + methodName + '\'' +
                ", metricType='" + metricType + '\'' +
                ", className='" + className + '\'' +
                "}";
    }

    public String onlyValues() {
        return metricType + CommonConstant.DELIMETER +
                className + CommonConstant.DELIMETER +
                methodName + CommonConstant.DELIMETER +
                getExecutionTime() + CommonConstant.DELIMETER +
                executionTimeStart + CommonConstant.DELIMETER +
                executionTimeEnd + CommonConstant.DELIMETER +
                startedDate + CommonConstant.DELIMETER +
                endedDate;
    }
}
