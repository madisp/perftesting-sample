package pink.madis.perftesting;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Simple Retrofit REST endpoint that can send test results to logstash
 */
interface LogstashReporter {
    @PUT("/")
    @Headers("Content-Type: application/json")
    Call<ResponseBody> reportResult(@Body PerfTestResult result);
}
