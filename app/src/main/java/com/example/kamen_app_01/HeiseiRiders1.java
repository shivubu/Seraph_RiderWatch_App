package com.example.kamen_app_01;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HeiseiRiders1 extends AppCompatActivity {
    int i=0;
    MediaPlayer mp,end;
    ImageView imageView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_heisei_riders);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int[] rw = {R.drawable.kuuga, R.drawable.agito,R.drawable.ryuki,R.drawable.faiz,R.drawable.blade,R.drawable.hibiki,R.drawable.kabuto,R.drawable.deno,R.drawable.kiva,R.drawable.decadec};
        int[] sounds = {R.raw.kuugault, R.raw.agitoshining,R.raw.ryukisurvive,R.raw.faizblaster,R.raw.bladeking,R.raw.hibikiarmed,R.raw.kabutohyper,R.raw.denoliner,R.raw.kivaemperor,R.raw.decadecomplete};
        int[] henshinsounds={R.raw.henshinkuugault,R.raw.henshinagitoshining,R.raw.henshinryukisurvive,R.raw.henshinfaizblaster,R.raw.henshinbladeking,R.raw.henshinhibikiarmed,R.raw.henshinkabutohyper,R.raw.henshindenoliner,R.raw.henshinkivaemperor,R.raw.henshindecade21};
        int[] longprsssounds={R.raw.lpkuuga,R.raw.lpagito,R.raw.lpryuki,R.raw.lpfaiz,R.raw.lpblade,R.raw.lphibiki,R.raw.lpkabuto,R.raw.lpdeno,R.raw.lpkiva,R.raw.lpdecade};
        ArrayList<Integer> screen = new ArrayList<>();
        for (int j : rw) {
            screen.add(j);
        }
        ArrayList<Integer> sound = new ArrayList<>();
        for (int j : sounds) {
            sound.add(j);
        }
        ArrayList<Integer> henshinsound = new ArrayList<>();
        for (int j : henshinsounds) {
            henshinsound.add(j);
        }
        ArrayList<Integer> longpress = new ArrayList<>();
        for (int longprsssound : longprsssounds) {
            longpress.add(longprsssound);
        }
        imageView = findViewById(R.id.imageView6);
        imageView.setImageResource(screen.get(i));
        imageView.setFocusable(true);
        imageView.requestFocus();
        imageView.setOnGenericMotionListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_SCROLL &&
                    motionEvent.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)){
                if(mp!=null)
                {
                    mp.release();
                    mp=null;
                    imageView.clearAnimation();
                }
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    i++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    i--;
                }
                // Wrap around the image array
                if (i < 0) {
                    i = screen.size() - 1;
                } else if (i >= screen.size()) {
                    i = 0;
                }
                // Update the background image
                if (!screen.isEmpty()) {
                    mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.transition2);
                    mp.start();
                    imageView.setImageResource(screen.get(i));
                }
                return true;
            }
            return false;
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            final GestureDetector gestureDetector=new GestureDetector(getApplicationContext(),new GestureDetector.SimpleOnGestureListener()
            {

                @Override
                public void onLongPress(@NonNull MotionEvent e) {
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    imageView.startAnimation(AnimationUtils.loadAnimation(HeiseiRiders1.this,R.anim.customfade));
                    mp = MediaPlayer.create(HeiseiRiders1.this, longpress.get(i));
                    mp.start();
                    mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    super.onLongPress(e);
                }
                @Override
                public boolean onDoubleTap(@NonNull MotionEvent e) {
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    imageView.startAnimation(AnimationUtils.loadAnimation(HeiseiRiders1.this,R.anim.customfade));
                    mp = MediaPlayer.create(HeiseiRiders1.this, henshinsound.get(i));
                    mp.start();
                    mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    return super.onDoubleTap(e);
                }
                @Override
                public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    imageView.startAnimation(AnimationUtils.loadAnimation(HeiseiRiders1.this,R.anim.customfade));
                    mp = MediaPlayer.create(HeiseiRiders1.this, sound.get(i));
                    mp.start();
                    mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    return super.onSingleTapConfirmed(e);
                }
            });
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
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
            Intent i = new Intent(HeiseiRiders1.this,Menu.class);
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
            imageView.clearAnimation();
            imageView.setClickable(true);
        }
        super.onPause();
    }
}