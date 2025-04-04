package com.example.kamen_app_01;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class Menu extends AppCompatActivity {
    ImageView heiseigen1,heiseigen2,custom,reiwagen1,showa,ohma;
    MediaPlayer mp,end;
    Animation fade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fade=AnimationUtils.loadAnimation(this,R.anim.customfade);
        heiseigen1=findViewById(R.id.imageView3);
        heiseigen2=findViewById(R.id.imageView4);
        custom=findViewById(R.id.imageView5);
        reiwagen1=findViewById(R.id.imageView9);
        showa=findViewById(R.id.imageView10);
        ohma=findViewById(R.id.imageView12);
        heiseigen1.setOnClickListener(v -> {
            try {
                onClickButton(heiseigen1, HeiseiRiders1.class.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        heiseigen2.setOnClickListener(v -> {
            try {
                onClickButton(heiseigen2, HeiseiRiders2.class.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        custom.setOnClickListener(v -> {
            try {
                onClickButton(custom, MainActivity.class.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        reiwagen1.setOnClickListener(v -> {
            try {
                onClickButton(reiwagen1, ReiwaRiders1.class.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        showa.setOnClickListener(v -> {
            try {
                onClickButton(showa, Showa.class.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        ohma.setOnClickListener(v -> {
            try {
                onClickButton(ohma, Ohma.class.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }

        });



    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mp != null) {
                mp.release();
            }
            end = MediaPlayer.create(this,R.raw.transition);
            end.start();
            end.setOnCompletionListener(mp -> {
                end.release();
                end=null;
            });
            Intent i = new Intent(Menu.this, LauncherActivity.class);
            startActivity(i);
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
            heiseigen1.clearAnimation();
            heiseigen2.clearAnimation();
            custom.clearAnimation();
            reiwagen1.clearAnimation();
            showa.clearAnimation();
            ohma.clearAnimation();
            heiseigen1.setClickable(true);
            heiseigen2.setClickable(true);
            custom.setClickable(true);
            reiwagen1.setClickable(true);
            showa.setClickable(true);
            ohma.setClickable(true);
        }
        super.onPause();
    }
    protected void onClickButton(ImageView button_name,Activity TargetClass_name)
    {
        custom.setClickable(false);
        heiseigen1.setClickable(false);
        heiseigen2.setClickable(false);
        reiwagen1.setClickable(false);
        showa.setClickable(false);
        ohma.setClickable(false);
        button_name.startAnimation(fade);
        mp=MediaPlayer.create(Menu.this,R.raw.transition1);
        mp.start();
        mp.setOnCompletionListener(mp -> {
            showa.clearAnimation();
            mp.release();
            startActivity(new Intent(Menu.this, TargetClass_name.getClass()));
            finish();
        });

    }

}