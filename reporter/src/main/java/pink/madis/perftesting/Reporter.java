package pink.madis.perftesting;

import java.io.IOException;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Reporter {
    private final LogstashReporter reporter;
    private final String version;

    public Reporter(String host, String version) {
        this.version = version;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(
                    new HttpUrl.Builder().scheme("http").host(host).port(1138).build()
                )
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        reporter = retrofit.create(LogstashReporter.class);
    }

    public void reportMeasurement(String testName, long timeMs) throws IOException {
        System.out.println("Sample " + testName + " - " + timeMs + "ms");
        PerfTestResult result = new PerfTestResult();
        result.test = testName;
        result.timeMillis = timeMs;
        result.version = version;
        reporter.reportResult(result).execute();
    }
}
