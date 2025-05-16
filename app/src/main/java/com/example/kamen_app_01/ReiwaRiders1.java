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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ReiwaRiders1 extends AppCompatActivity {
    int i=0,gavvoverhenshin=0;
    int overindex;
    MediaPlayer mp,end;
    ImageView imageView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reiwa_riders1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Animation fade= AnimationUtils.loadAnimation(this,R.anim.customfade);
        int[] rw = {R.drawable.zerotwo,R.drawable.sabercross,R.drawable.reviultimate,R.drawable.geats9,R.drawable.gotchardrainbow,R.drawable.gavvover};
        int[] sounds = {R.raw.zeroone,R.raw.saber,R.raw.revi,R.raw.geats,R.raw.gotchard,R.raw.gavvover};
        int[] henshinsounds={R.raw.henshinzerotwo,R.raw.henshincrosssaber,R.raw.henshinrevicerex,R.raw.henshingeats9,R.raw.henshingotchardrainbow,R.raw.henshingavvover};
        int[] longpress={R.raw.lpzeroone,R.raw.lpsaber,R.raw.lprevi,R.raw.lpgeats,R.raw.lpgotchard,R.raw.lpgavvover};
        int[] gavvover={R.raw.gavvover0,R.raw.gavvover1,R.raw.gavvover2,R.raw.gavvover3};
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
        ArrayList<Integer> longpresssound = new ArrayList<>();
        for (int j : longpress) {
            longpresssound.add(j);
        }
        ArrayList<Integer> gavvoversounds = new ArrayList<>();
        for (int j : gavvover) {
            gavvoversounds.add(j);
        }
        imageView = findViewById(R.id.imageView8);
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
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.transition2);
                    mp.start();
                    i++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.transition2);
                    mp.start();
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
                    imageView.setImageResource(screen.get(i));
                    if(i==5)
                    {
                        gavvoverhenshin=0;
                        overindex=-1;
                    }
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
                    imageView.startAnimation(fade);
                    mp = MediaPlayer.create(ReiwaRiders1.this, longpresssound.get(i));
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
                    imageView.startAnimation(fade);
                    mp = MediaPlayer.create(ReiwaRiders1.this, henshinsound.get(i));
                    mp.start();
                    if(i==5)
                    {
                        gavvoverhenshin=1;
                    }
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
                    imageView.startAnimation(fade);
                    if(i==5 && gavvoverhenshin==1)
                    {
                        mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.oversmash);
                        mp.start();
                    }
                    else
                    {
                        mp = MediaPlayer.create(ReiwaRiders1.this, sound.get(i));
                        mp.start();
                    }
                    mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    return super.onSingleTapConfirmed(e);
                }
                @Override
                public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    assert e1 != null;
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    float SWIPE_THRESHOLD_VELOCITY = 200;
                    float SWIPE_THRESHOLD_DISTANCE = 100;
                    if(i==5 && gavvoverhenshin==1)
                    {
                        if(mp!=null)
                        {
                            mp.release();
                            mp=null;
                            imageView.clearAnimation();
                        }
                        if(diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE)
                        {
                            overindex--;
                            if(overindex<0)
                            {
                                overindex=gavvoversounds.size()-1;
                            }
                            mp=MediaPlayer.create(ReiwaRiders1.this,gavvoversounds.get(overindex));
                            mp.start();

                        }
                        if(diffX > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE)
                        {
                            overindex++;
                            if(overindex>=gavvoversounds.size())
                            {
                                overindex=0;
                            }
                            mp=MediaPlayer.create(ReiwaRiders1.this,gavvoversounds.get(overindex));
                            mp.start();
                        }
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
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
            Intent i = new Intent(ReiwaRiders1.this,Menu.class);
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