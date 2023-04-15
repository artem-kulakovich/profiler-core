package by.bntu.fitr.core.metric;

import by.bntu.fitr.core.constant.CommonConstant;

import java.util.List;

public class JVMMetric {
    private HeapMetric heapMetric;
    private List<DiskMetric> disksMetric;
    private GarbageCollectorMetric garbageCollectorMetric;

    public JVMMetric() {
    }

    public HeapMetric getHeapMetric() {
        return heapMetric;
    }

    public void setHeapMetric(HeapMetric heapMetric) {
        this.heapMetric = heapMetric;
    }

    public List<DiskMetric> getDisksMetric() {
        return disksMetric;
    }

    public void setDisksMetric(List<DiskMetric> disksMetric) {
        this.disksMetric = disksMetric;
    }

    public GarbageCollectorMetric getGarbageCollectorMetric() {
        return garbageCollectorMetric;
    }

    public void setGarbageCollectorMetric(GarbageCollectorMetric garbageCollectorMetric) {
        this.garbageCollectorMetric = garbageCollectorMetric;
    }

    @Override
    public String toString() {
        return "JVMMetric{" +
                "heapMetric=" + heapMetric +
                ", diskMetric=" + disksMetric +
                ", garbageCollectorMetric=" + garbageCollectorMetric +
                '}';
    }

    public String onlyValues() {
        return "[" + heapMetric.onlyValues() + "]" + CommonConstant.DELIMETER +
                "[" + garbageCollectorMetric.onlyValues() + "]" + CommonConstant.DELIMETER +
                "[" + getListValuesOfDisks() + "]";

    }

    private String getListValuesOfDisks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < disksMetric.size(); i++) {
            result.append(disksMetric.get(i).onlyValues());

            if (i != disksMetric.size() - 1) {
                result.append(CommonConstant.DELIMETER);
            }
        }

        return result.toString();
    }

}
