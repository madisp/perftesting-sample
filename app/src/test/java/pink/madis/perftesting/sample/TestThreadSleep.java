package pink.madis.perftesting.sample;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.Callable;

import pink.madis.perftesting.Reporter;

public class TestThreadSleep {

    @Rule
    public RepeatRule repeatRule = new RepeatRule(25);

    private Reporter reporter = new Reporter("1.0.0");

    @Test
    public void testSleepsForOneSecond() throws Exception {
        reporter.measureAndReport(repeatRule.getRepeatNumber(), "testSleepsForOneSecond", new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(1100);
                return null;
            }
        });
    }
}
