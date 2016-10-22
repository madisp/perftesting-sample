package pink.madis.perftesting.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

public class MeasureActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long start = SystemClock.elapsedRealtime();
        Log.d("MeasureActivity", "Starting main process...");
        startActivity(
            new Intent(this, MainActivity.class)
                .putExtra(MainActivity.EXTRA_MEASURE_STARTUP_TIME_START, start)
        );
        finish();
    }
}
