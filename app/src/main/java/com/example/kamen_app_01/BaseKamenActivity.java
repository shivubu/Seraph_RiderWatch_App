package com.example.kamen_app_01;

import android.content.Intent;import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseKamenActivity extends AppCompatActivity {

    protected MediaPlayer mp, mp1, end;
    protected PerfectLoopMediaPlayer loopPlayer;
    protected Handler handler = new Handler(Looper.getMainLooper());

    protected abstract ImageView getLocalImageView();
    protected abstract Class<?> getBackTargetClass();protected abstract View getRotaryView();
    protected abstract void setupRotaryLogic(); // Each class putsits unique bezel code here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK) {
            releaseResources();
            Class<?> target = getBackTargetClass();
            if (target != null) {
                end = MediaPlayer.create(this, R.raw.transition);
                if(end != null) end.start();
                startActivity(new Intent(this, target));
                finish();
            } else {
                // EXIT APP: Kill process to ensure 0% battery drain
                finishAffinity();new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                },500);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRotaryLogic(); // Re-attaches the listener when you return
        View v = getRotaryView();
        if (v != null) {
            v.setFocusable(true);
            v.setFocusableInTouchMode(true);
            v.postDelayed(v::requestFocus, 100); // Grabs focus so bezel works
        }
    }

    @Override
    protected void onPause() {super.onPause();
        if (loopPlayer != null) loopPlayer.pause();
        if (mp != null && mp.isPlaying()) mp.pause();
        if (mp1 != null && mp1.isPlaying()) mp1.pause();
        if (handler != null) handler.removeCallbacksAndMessages(null);}

    @Override
    protected void onStop() {
        super.onStop();releaseResources();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();releaseResources();
    }

    private void releaseResources() {
        if (loopPlayer != null){
            try { loopPlayer.release(); } catch (Exception e) {}
            loopPlayer = null;
        }if (mp != null) { mp.release(); mp = null; }
        if (mp1!= null) { mp1.release(); mp1 = null; }
        if (end != null) {end.release(); end = null; }
        View rotaryView = getRotaryView();
        if (rotaryView != null) rotaryView.setOnGenericMotionListener(null);
        if (handler != null) handler.removeCallbacksAndMessages(null);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}