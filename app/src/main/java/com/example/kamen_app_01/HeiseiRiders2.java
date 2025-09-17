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
    int i=0,flag=0,kiwami=0,hazard,w,kiwamicounter,kiwami1,tricounter,triflag;
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
        int[] finishersounds={R.raw.finisher_doublecjex,R.raw.finisher_oooputo,R.raw.finisher_fourzecosmic,R.raw.finisher_wizardinfinity,R.raw.finisher_gaimkiwami_3,R.raw.finisher_drivetrideron,R.raw.finisher_ghostmugen,R.raw.finisher_exaidmuteki,R.raw.finisher_buildgenius,R.raw.finisher_grandzio_1};
        int[] kiwamisounds={R.raw.kiwami1,R.raw.kiwami2,R.raw.kiwami3,R.raw.kiwami4,R.raw.kiwami5,R.raw.kiwami6,R.raw.kiwami7,R.raw.kiwami8,R.raw.kiwami9,R.raw.kiwami10,R.raw.kiwami11,R.raw.kiwami12,R.raw.kiwami13,R.raw.kiwami14,R.raw.kiwami15,R.raw.kiwami16};
        int[] trideronhenshins={R.raw.henshin_trideron123,R.raw.henshin_trideronsaver,R.raw.henshin_triderondream,R.raw.henshin_triderongenbar,R.raw.henshin_trideronweather,R.raw.henshin_triderontough,R.raw.henshin_triderongrand};
        int[] trideronfinishers={R.raw.finisher_trideron123,R.raw.finisher_trideronsaver,R.raw.finisher_triderondream,R.raw.finisher_triderongenbar,R.raw.finisher_trideronweather,R.raw.finisher_triderontough,R.raw.finisher_triderongrand};
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
        ArrayList<Integer> kiwamisound = new ArrayList<>();
        for (int j : kiwamisounds) {
            kiwamisound.add(j);
        }
        ArrayList<Integer> trideronhenshin = new ArrayList<>();
        for (int j : trideronhenshins) {
            trideronhenshin.add(j);
        }
        ArrayList<Integer> trideronfinisher = new ArrayList<>();
        for (int j : trideronfinishers) {
            trideronfinisher.add(j);
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
                    switch (i)
                    {
                        case 0:w=0;break;
                        case 4: kiwami=0;kiwami1=0;kiwamicounter=-1;break;
                        case 5: tricounter=-1;triflag=0;break;
                        case 8: hazard=0;break;
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
                    boolean downSwipe = diffY > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean upSwipe = diffY < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean rightSwipe = diffX > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    boolean leftSwipe= diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    if(i==0)
                    {
                        imageView.clearAnimation();
                        if(mp!=null) {
                            mp.release();
                            mp = null;
                        }
                        if(mp1!=null)
                        {
                            mp1.release();
                            mp1=null;
                        }
                        if(downSwipe)
                        {
                            w=1;
                            imageView.setImageResource(R.drawable.doublegold);
                            imageView.startAnimation(fade);
                            mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.doublegoldmode);
                            mp.start();
                            mp.setOnCompletionListener(mp1 -> {
                                mp.release();
                                mp=null;
                                imageView.clearAnimation();
                            });
                        }
                        if(upSwipe)
                        {
                            imageView.setImageResource(screen.get(i));
                            w=0;
                        }
                        if(leftSwipe || rightSwipe)
                        {
                            mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.zonemaxdrive);
                            mp.start();
                        }
                    }
                    else if(i==3)
                    {
                        imageView.clearAnimation();
                        if(mp!=null) {
                            mp.release();
                            mp = null;
                        }
                        if(mp1!=null)
                        {
                            mp1.release();
                            mp1=null;
                        }
                        if(downSwipe)
                        {
                            imageView.setImageResource(R.drawable.wizardgold);
                            imageView.startAnimation(fade);
                            mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.finisher_wizardinfinity_1);
                            mp.start();
                            mp.setOnCompletionListener(mp1 -> {
                                mp.release();
                                mp=null;
                                imageView.clearAnimation();
                            });
                        }
                        if(upSwipe)
                        {
                            imageView.setImageResource(screen.get(i));
                        }
                    }
                    else if(i==4)
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
                        if (downSwipe)
                        {
                            mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.slash);
                            mp.start();
                            kiwami++;
                            if(kiwami>3)
                            {
                                kiwami=0;
                            }
                        }
                        if(upSwipe)
                        {
                            switch(kiwami1)
                            {
                                case 0:mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.finisher_gaimkiwami1);kiwami1=1;mp.start();break;
                                case 1:mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.finisher_gaimkiwami2);kiwami1=0;mp.start();break;
                            }
                        }
                        if(rightSwipe)
                        {
                            kiwamicounter++;
                            if(kiwamicounter>=kiwamisounds.length)
                            {
                                kiwamicounter=0;
                            }
                            mp=MediaPlayer.create(HeiseiRiders2.this,kiwamisound.get(kiwamicounter));
                            mp.start();
                        }
                        if(leftSwipe)
                        {
                            kiwamicounter--;
                            if(kiwamicounter<0)
                            {
                                kiwamicounter=kiwamisounds.length-1;
                            }
                            mp=MediaPlayer.create(HeiseiRiders2.this,kiwamisound.get(kiwamicounter));
                            mp.start();
                        }
                    }
                    else if(i==5)
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
                        if(rightSwipe)
                        {
                            tricounter++;
                            if(tricounter>=trideronhenshins.length)
                            {
                                tricounter=0;
                            }
                            mp=MediaPlayer.create(HeiseiRiders2.this,trideronhenshin.get(tricounter));
                            mp.start();
                        }
                        if(leftSwipe)
                        {
                            tricounter--;
                            if(tricounter<0)
                            {
                                tricounter=trideronhenshins.length-1;
                            }
                            mp=MediaPlayer.create(HeiseiRiders2.this,trideronhenshin.get(tricounter));
                            mp.start();
                        }
                        if(downSwipe)
                        {
                            tricounter=-1;
                            triflag=0;
                        }
                        if(upSwipe)
                        {
                            tricounter=-1;
                            triflag=1;
                        }
                    }
                    else if(i==8)
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
                        if(leftSwipe)
                        {
                            switch(hazard)
                            {
                                case 0:mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.oneside);mp.start();break;
                                case 1:mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.onesidehazard);mp.start();break;
                            }
                        }
                        else if(rightSwipe)
                        {
                            switch (hazard)
                            {
                                case 0:mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.otherside);mp.start();break;
                                case 1:mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.othersidehazard);mp.start();break;
                            }
                        }
                        if(downSwipe)
                        {
                            hazard=1;
                            mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.hazardon);
                            mp.start();
                        }
                        if(upSwipe){
                            hazard = 0;
                            mp = MediaPlayer.create(HeiseiRiders2.this, R.raw.hazardoff);
                            mp.start();
                        }

                    }
                    else if(i==9)
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
                        if(leftSwipe || rightSwipe)
                        {
                            mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.finisher_grandzio);
                            mp.start();
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
                        if(i==0 && w!=0)
                        {
                            mp1=MediaPlayer.create(HeiseiRiders2.this,R.raw.xtrememaxdrive);
                        }
                        else if(i==4 && kiwami!=0) {
                            switch (kiwami) {
                                case 1:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, R.raw.finisher_gaimkiwami_1);kiwami=0;
                                    break;
                                case 2:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, R.raw.finisher_gaimkiwami_2);kiwami=0;
                                    break;
                                case 3:
                                    mp1 = MediaPlayer.create(HeiseiRiders2.this, R.raw.finisher_gaimkiwami_3);kiwami=0;
                                    break;
                            }
                        }
                        else if(i==5)
                        {
                            if(tricounter==-1)
                            {
                                switch(triflag)
                                {
                                    case 0:mp1=MediaPlayer.create(HeiseiRiders2.this,finishersound.get(i));break;
                                    case 1:mp1=MediaPlayer.create(HeiseiRiders2.this,R.raw.finisher_trideronall);break;
                                }
                                mp1.start();
                            }
                            else {
                                mp1=MediaPlayer.create(HeiseiRiders2.this,trideronfinisher.get(tricounter));
                                mp1.start();
                            }
                        }
                        else if(i==8 && hazard==1)
                        {
                            mp1=MediaPlayer.create(HeiseiRiders2.this,R.raw.lpbuildgeniushazard);
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
                    if(i==8 && hazard==1)
                    {
                        mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.henshinbuildgeniushazard);
                    }
                    else
                    {
                        mp=MediaPlayer.create(HeiseiRiders2.this,henshinsound.get(i));
                    }
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