package com.eduardtarassov.neonscraper;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import android.os.PowerManager;
import android.content.Context;

public class MainActivity extends AndroidApplication {
    private PowerManager.WakeLock wl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;

        // Passing configurations that we are going to use accelerometer and compass (for motion input)
        cfg.useAccelerometer = true;
        cfg.useCompass = true;

        initialize(new NSGame(), cfg);

        // Prevents screen from going to sleep.
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
    }

    @Override protected void onPause() {

        wl.release();
        super.onPause();
    }

    @Override protected void onResume() {

        wl.acquire();
        super.onResume();
    }
}