package com.example.kamen_app_01;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;public abstract class BaseKamenActivity extends AppCompatActivity {

    protected MediaPlayer mp, mp1, end;protected PerfectLoopMediaPlayer loopPlayer;
    protected Handler handler = new Handler(Looper.getMainLooper());protected abstract ImageView getLocalImageView();
    protected abstract Class<?> getBackTargetClass();
    protected abstract View getRotaryView();

    // NEW: Each child activity will put its unique bezel code here
    protected abstract void setupRotaryLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {if (keyCode == KeyEvent.KEYCODE_BACK) {
        releaseResources();

        end = MediaPlayer.create(this, R.raw.transition);
        if (end != null) {
            end.start();
        }
        Class<?> target = getBackTargetClass();
        if (target != null) {startActivity(new Intent(this, target));
            finish();
        } else {
            //BATTERY FIX: Ensure the app process dies completely on exit
            finishAffinity();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }, 500);
        }return true;
    }
        return super.onKeyDown(keyCode, event);
    }@Override
    protected void onPause() {
        super.onPause();
        if (loopPlayer !=null) loopPlayer.pause();
        if (mp != null && mp.isPlaying()) mp.pause();if (mp1 != null && mp1.isPlaying()) mp1.pause();
        if (handler !=null) handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ROTARY FIX: Re-attach the listener that was cleared in onStop
        setupRotaryLogic();

        // FOCUS FIX: Request focus on the Rotary View specifically
        View rotaryView =getRotaryView();
        if (rotaryView != null) {
            rotaryView.setFocusable(true);
            rotaryView.setFocusableInTouchMode(true);
            rotaryView.postDelayed(rotaryView::requestFocus, 100);
        }
    }

    @Override protected void onStop() {
        super.onStop();
        releaseResources();
    }@Override
    protected void onDestroy() {
        super.onDestroy();
        releaseResources();
    }

    private void releaseResources() {
        // 1. Stop the loop first to prevent it from spawning a 'NextPlayer'
        if (loopPlayer != null) {
            try {
                loopPlayer.stop();loopPlayer.release();
            } catch (Exception e) {
                // Prevent crash if already released}
                loopPlayer = null;
            }
            // 2. Standard players
            if (mp != null) { mp.release(); mp = null; }
            if (mp1 != null) { mp1.release(); mp1 = null; }

            // 3. Transition sound (allow it to finish)
            if (end != null) {
            if (end.isPlaying()) {
                end.setOnCompletionListener(MediaPlayer::release);
            } else {
                end.release();
                end = null;
            }}

        // 4. Hardware Sensors & UI
        View rotaryView = getRotaryView();
        if (rotaryView != null) {
            rotaryView.setOnGenericMotionListener(null);}

        ImageView imageView = getLocalImageView();
        if (imageView != null) {
            imageView.clearAnimation();
        }

        // 5. CPU & Battery
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    }
}