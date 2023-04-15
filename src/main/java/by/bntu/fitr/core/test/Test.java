package by.bntu.fitr.core.test;


import by.bntu.fitr.core.annotations.Metric;
import by.bntu.fitr.core.annotations.Timed;

@Metric
public class Test extends Thread {

    @Timed
    public void test() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {
        while (true) {
            test();
        }
    }
}
