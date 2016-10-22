package pink.madis.perftesting.sample;

import org.junit.Test;

import java.util.Random;

import pink.madis.perftesting.Reporter;

/*
 * Just to illustrate the importance of getting narrow-band reults,
 * i.e. how hard it is to see small regressions with large spread
 */
public class TestRandomTimes {
    private final Random rand = new Random();
    private final Reporter reporter = new Reporter("localhost", BuildConfig.VERSION_NAME);

    private final static long ERROR   = 200;
    private final static long REGRESS = 800;

    @Test
    public void testSpread() throws Exception {
        for (int i = 0; i < 20; i++) {
            long timeMs = 8000 + (rand.nextLong() % ERROR);
            if (i >= 10) {
                // introduce regression
                timeMs += 400;
            }
            reporter.reportMeasurement("consistency", timeMs);
            Thread.sleep(200);
        }
    }
}
