package com.example.kamen_app_01;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseKamenActivity extends AppCompatActivity {

    protected MediaPlayer mp, mp1, end;
    protected Handler handler = new Handler(); // Shared handler for all activities

    protected abstract ImageView getLocalImageView();
    protected abstract Class<?> getBackTargetClass();

    // New contract: Return the view that has the Rotary/Scroll listener
    // Return null if the activity doesn't use one.
    protected abstract View getRotaryView();@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mp != null) {
                mp.release();
                mp = null;
            }

            end = MediaPlayer.create(this, R.raw.transition);
            if (end != null) {
                end.start();
            }

            Class<?> target = getBackTargetClass();
            if (target != null) {
                Intent i = new Intent(this, target);startActivity(i);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {super.onPause();
        if (mp != null && mp.isPlaying()) mp.pause();if (mp1 != null && mp1.isPlaying()) mp1.pause();

        // Stop any pending handlertasks immediately when screen is partially covered
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
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
        // 1. MediaPlayers
        if (mp != null) { mp.release(); mp = null; }
        if (mp1!= null) { mp1.release(); mp1 = null; }
        if (end != null) {if (end.isPlaying()) {
            end.setOnCompletionListener(MediaPlayer::release);
        }else {
            end.release();
            end = null;
        }
        }

        //2. Animations
        ImageView imageView = getLocalImageView();
        if (imageView != null) {
            imageView.clearAnimation();
        }

        // 3. Unbind Rotary/Scroll Listener
        View rotaryView =getRotaryView();
        if (rotaryView != null) {
            rotaryView.setOnGenericMotionListener(null);
        }

        // 4. Kill all Handler tasks (prevents memory leaks and background CPU usage)
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }// 5. Allow watch to sleep
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}