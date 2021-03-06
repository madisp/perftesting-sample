package pink.madis.perftesting.sample;

import org.junit.Rule;
import org.junit.Test;

import pink.madis.perftesting.Reporter;

public class TestThreadSleep {

    @Rule
    public RepeatRule repeatRule = new RepeatRule(25);

    private Reporter reporter = new Reporter("localhost", BuildConfig.VERSION_NAME);

    @Test
    public void testSleepsForOneSecond() throws Exception {
        long start = System.nanoTime();
        Thread.sleep(1000);
        long end = System.nanoTime();
        long timeMs = getDurationMillis(start, end);
        reporter.reportMeasurement("threadsleep", timeMs);
    }

    private long getDurationMillis(long startNanos, long endNanos) {
        return (endNanos - startNanos) / 1000000;
    }
}
