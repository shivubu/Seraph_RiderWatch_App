package com.example.kamen_app_01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public abstract class BaseKamenActivity extends AppCompatActivity {

    // 1. Define these ONCE here.// Protected means the child activities (ReiwaRiders1, etc.) can see and use them.
    protected MediaPlayer mp, mp1, end;

    // 2. This is a "Contract". Every activity MUSTimplement this
    // to tell the base class which ImageView to clean up.
    protected abstract ImageView getLocalImageView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }@Override
    protected void onPause() {
        super.onPause();
        // Stop sounds immediately if a notification covers the screen
        if (mp != null && mp.isPlaying()) mp.pause();
        if (mp1 != null && mp1.isPlaying()) mp1.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseResources();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseResources();
    }
    private void releaseResources() {
        // Release MediaPlayers
        if (mp != null) { mp.release(); mp= null; }
        if (mp1 != null) { mp1.release(); mp1 = null;}
        if (end != null) {
            if (end.isPlaying()) {
                // If it's still playing, set a listener to kill it ONLY when done
                // We don't set end =null here because it's a local reference
                // in the listener, but the hardware is now free.
                end.setOnCompletionListener(MediaPlayer::release);
        } else {
            // If it's already done or not playing, release it now end.release();
            end = null;
        }
    }

        // Clean up the specific ImageView for THIS activity
        ImageView imageView = getLocalImageView();
        if (imageView != null) {
            imageView.clearAnimation();
        }

        // Allow watch to sleep
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}