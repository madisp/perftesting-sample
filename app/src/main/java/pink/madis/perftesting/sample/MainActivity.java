package pink.madis.perftesting.sample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import java.io.IOException;

import pink.madis.perftesting.Reporter;

public class MainActivity extends Activity {
    public static final String EXTRA_MEASURE_STARTUP_TIME_START = "pink.madis.perftesting.measuretime.start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView) findViewById(R.id.hello);

//        net.danlew.android.joda.JodaTimeAndroid.init(this);
//        tv.setText(getString(R.string.cur_time, org.joda.time.DateTime.now().toString()));
        tv.setText(getString(R.string.cur_time, "now"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        long end = SystemClock.elapsedRealtime();
        long start = getIntent().getLongExtra(EXTRA_MEASURE_STARTUP_TIME_START, -1);
        if (start != -1) {
            long timeMs = end - start;
            new ReporterAsyncTask().execute(timeMs);
        }
    }

    private static class ReporterAsyncTask extends AsyncTask<Long, Void, Void> {
        @Override
        protected Void doInBackground(Long... params) {
            if (params.length != 1) {
                throw new IllegalArgumentException("ReporterAsyncTask needs exactly one long as params");
            }
            // only works on emulator!
            Reporter reporter = new Reporter("10.0.2.2", BuildConfig.VERSION_NAME);
            try {
                reporter.measureAndReport("startup", params[0]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
