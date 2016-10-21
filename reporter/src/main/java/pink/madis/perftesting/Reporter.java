package pink.madis.perftesting;

import java.util.concurrent.Callable;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Reporter {
    private final LogstashReporter reporter;
    private final String version;

    public Reporter(String version) {
        this.version = version;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:1138/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        reporter = retrofit.create(LogstashReporter.class);
    }

    public void measureAndReport(int repeatNumber, String testName, Callable<Void> action) throws Exception {
        long start = System.nanoTime();
        action.call();
        long end = System.nanoTime();

        long timeMs = getDurationMillis(start, end);
        System.out.println("Sample " + testName + "#" + repeatNumber + " - " + timeMs + "ms");

        PerfTestResult result = new PerfTestResult();
        result.test = testName;
        result.timeMillis = timeMs;
        result.version = version;

        reporter.reportResult(result).execute();
    }

    private long getDurationMillis(long startNanos, long endNanos) {
        return (endNanos - startNanos) / 1000000;
    }
}
