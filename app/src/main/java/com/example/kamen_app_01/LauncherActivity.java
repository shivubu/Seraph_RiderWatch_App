package com.example.kamen_app_01;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LauncherActivity extends AppCompatActivity {
    ImageView im2;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launch);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Animation rotate= AnimationUtils.loadAnimation(this,R.anim.rotate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        im2=findViewById(R.id.imageView2);
        if(rotate==null)
        {
            im2.setClickable(false);
        }
        mp=MediaPlayer.create(this,R.raw.transition);
        im2.setOnClickListener(v -> {
            im2.setClickable(false);
            mp.start();
            new Handler(Looper.getMainLooper()).postDelayed(() -> im2.startAnimation(rotate), 300);
        });
        mp.setOnCompletionListener(mp -> {
            startActivity(new Intent(LauncherActivity.this, Menu.class));
            finish();
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mp != null) {
                mp.release();
            }
            im2.clearAnimation();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onPause() {
        if(mp!=null)
        {
            mp.release();
            mp=null;
            im2.clearAnimation();
            im2.setClickable(true);
        }
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        if(mp!=null)
        {
            mp.release();
            mp=null;
        }
        super.onDestroy();
    }
}