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

public class HeiseiRiders2 extends AppCompatActivity {
    int i=0,flag=0,grand=0,kiwami=0;
    MediaPlayer mp,mp1,end;
    ImageView imageView;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_heisei_riders2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Animation fade= AnimationUtils.loadAnimation(this,R.anim.customfade);
        int[] rw = {R.drawable.w,
                R.drawable.ooo,
                R.drawable.fourze,
                R.drawable.wizard,
                R.drawable.gaim,
                R.drawable.drive,
                R.drawable.ghost,
                R.drawable.exaid,
                R.drawable.build,
                R.drawable.grandzio};
        int[] sounds = {R.raw.doublecjex, R.raw.oooputo,R.raw.fourzecosmic,R.raw.wizardinfinity,R.raw.gaimkiwami,R.raw.drivetrideron,R.raw.ghostmugen,R.raw.exaidmuteki,R.raw.buildgenius,R.raw.grandzio};
        int[] henshinsounds={R.raw.henshindoublecjx,R.raw.henshinoooputo,R.raw.henshinfourzecosmic,R.raw.henshinwizardinfinity,R.raw.henshingaimkiwami1,R.raw.henshindrivetrideron,R.raw.henshinghostmugen,R.raw.henshinexaidmuteki,R.raw.henshinbuildgenius,R.raw.henshingrandzio};
        int[] longpresssounds={R.raw.lpdouble,R.raw.lpooo,R.raw.lpfourze,R.raw.lpwizard,R.raw.lpgaim,R.raw.lpdrive,R.raw.lpghost,R.raw.lpexaid,R.raw.lpbuild,R.raw.lpzio};
        int[] finishersounds={R.raw.finisher_doublecjex,R.raw.finisher_oooputo,R.raw.finisher_fourzecosmic,R.raw.finisher_wizardinfinity,R.raw.finisher_gaimkiwami,R.raw.finisher_drivetrideron,R.raw.finisher_ghostmugen,R.raw.finisher_exaidmuteki,R.raw.finisher_buildgenius,R.raw.finisher_grandzio};
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
        for (int j : longpresssounds) {
            longpresssound.add(j);
        }
        ArrayList<Integer> finishersound = new ArrayList<>();
        for (int j : finishersounds) {
            finishersound.add(j);
        }
        imageView = findViewById(R.id.imageView7);
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
                if(mp1!=null)
                {
                    mp1.release();
                    mp1=null;
                    imageView.clearAnimation();
                }
                flag=0;
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.transition2);
                    mp.start();
                    i++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.transition2);
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
                    if(i==4)
                    {
                        kiwami=0;
                    }
                    if(i==9)
                    {
                        grand=0;
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
                public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    assert e1 != null;
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    float SWIPE_THRESHOLD_VELOCITY = 200;
                    float SWIPE_THRESHOLD_DISTANCE = 100;
                    if(i==4)
                    {
                        imageView.clearAnimation();
                        if(mp!=null)
                        {
                            mp.release();
                            mp=null;
                        }
                        if(mp1!=null)
                        {
                            mp1.release();
                            mp1=null;
                        }
                        if (diffY > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE)
                        {
                            mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.slash);
                            mp.start();
                            kiwami++;
                            if(kiwami>3)
                            {
                                kiwami=0;
                            }

                        }
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }

                @Override
                public void onLongPress(@NonNull MotionEvent e) {
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    if(mp1!=null)
                    {
                        mp1.release();
                        mp1=null;
                    }
                    imageView.startAnimation(fade);
                    mp = MediaPlayer.create(HeiseiRiders2.this,R.raw.judgement_finishtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        if(i==4) {
                            switch (kiwami) {
                                case 0:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, finishersound.get(i));
                                    break;
                                case 1:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, R.raw.finisher_gaimkiwami_1);
                                    break;
                                case 2:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, R.raw.finisher_gaimkiwami_2);
                                    break;
                                case 3:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, R.raw.finisher_gaimkiwami_3);
                                    break;
                            }
                        }
                        else if(i==9) {
                            switch (grand) {
                                case 0:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, finishersound.get(i));
                                    grand = 1;
                                    break;
                                case 1:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, R.raw.finisher_grandzio_1);
                                    grand = 0;
                                    break;

                            }
                        }
                        else
                        {
                            mp1=MediaPlayer.create(HeiseiRiders2.this,finishersound.get(i));
                        }
                        mp1.start();
                        mp1.setOnCompletionListener(mp2 -> imageView.clearAnimation());
                    });
                    super.onLongPress(e);
                }

                @Override
                public boolean onDoubleTap(@NonNull MotionEvent e) {
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    if(mp1!=null)
                    {
                        mp1.release();
                        mp1=null;
                    }
                    imageView.startAnimation(fade);
                    mp = MediaPlayer.create(HeiseiRiders2.this, henshinsound.get(i));
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
                    if(mp1!=null)
                    {
                        mp1.release();
                        mp1=null;
                    }
                    imageView.startAnimation(fade);
                    if(flag==0){
                        flag=1;
                        mp = MediaPlayer.create(HeiseiRiders2.this, sound.get(i));
                        mp.start();
                        mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    }
                    else if(flag==1)
                    {
                        flag=0;
                        mp = MediaPlayer.create(HeiseiRiders2.this, longpresssound.get(i));
                        mp.start();
                        mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    }
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
            if (mp1 != null) {
                mp1.release();
            }
            end = MediaPlayer.create(this,R.raw.transition);
            end.start();
            end.setOnCompletionListener(mp -> {
                end.release();
                end=null;
            });
            Intent i = new Intent(HeiseiRiders2.this,Menu.class);
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
        if(mp1!=null)
        {
            mp1.release();
            mp1=null;
            imageView.clearAnimation();
            imageView.setClickable(true);
        }
        super.onPause();
    }

}