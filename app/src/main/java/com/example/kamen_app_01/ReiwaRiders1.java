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
    int i=0,gavvoverhenshin=0,flag=0,geatsflag=0,saber=0,gavvmode=0,hellrise=0,primitive=0,pdhenshin=0,pdfinisher;
    int overindex,masterindex;
    MediaPlayer mp,mp1,end;
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
        int[] sounds = {R.raw.zerotwo,R.raw.sabercross,R.raw.reviultimate,R.raw.geats9,R.raw.gotchardrainbow,R.raw.gavvover};
        int[] henshinsounds={R.raw.henshinzerotwo,R.raw.henshincrosssaber,R.raw.henshinrevicerex,R.raw.henshingeats9,R.raw.henshingotchardrainbow,R.raw.henshingavvover};
        int[] longpress={R.raw.lpzeroone,R.raw.lpsaber,R.raw.lprevi,R.raw.lpgeats,R.raw.lpgotchard};
        int[] finishersounds={R.raw.finisher_zerotwo,R.raw.finisher_crosssaber,R.raw.finisher_reviultimate,R.raw.finisher_geats9_1,R.raw.finisher_gotchardrainbow,R.raw.lpgavvover};
        int[] gavvover={R.raw.gavvover0,R.raw.gavvover1,R.raw.gavvover2,R.raw.gavvover3};
        int[] gavvmaster={R.raw.mastergummy,R.raw.mastersnack,R.raw.mastermarsh,R.raw.masterchoco,R.raw.mastercandy,R.raw.masterdonuts,R.raw.mastercake};
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
        ArrayList<Integer> finishersound = new ArrayList<>();
        for (int j : finishersounds) {
            finishersound.add(j);
        }
        ArrayList<Integer> gavvoversounds = new ArrayList<>();
        for (int j : gavvover) {
            gavvoversounds.add(j);
        }
        ArrayList<Integer> gavvmastersounds = new ArrayList<>();
        for (int j : gavvmaster) {
            gavvmastersounds.add(j);
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
                if(mp1!=null) {
                    mp1.release();
                    mp1 = null;
                    imageView.clearAnimation();
                }
                flag=0;
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.transition2);
                    mp.start();
                    i++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
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
                    switch(i)
                    {
                        case 0: hellrise=0;break;
                        case 1: saber=0;primitive=0;pdhenshin=0;pdfinisher=0;break;
                        case 3: geatsflag=0; break;
                        case 5: gavvmode=0;gavvoverhenshin=0; overindex=-1; masterindex=-1; break;
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
                    if(mp1!=null)
                    {
                        mp1.release();
                        mp1=null;
                    }
                    imageView.startAnimation(fade);
                    mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.judgement_finishtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        if(i==0)
                        {
                            switch(hellrise)
                            {
                                case 0:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_zerotwo);break;
                                case 1:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_zeronehellrise);break;
                            }
                        }
                        else if(i==1)
                        {
                            if(primitive==1)
                            {
                                switch(pdfinisher)
                                {
                                    case 0:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_saberprimitivedragon);pdfinisher=1;break;
                                    case 1:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_saberprimitivedragon1);pdfinisher=0;break;
                                }
                            }
                            else
                            {
                                switch(saber)
                                {
                                    case 0:mp1= MediaPlayer.create(ReiwaRiders1.this,finishersound.get(i));saber=1;break;
                                    case 1:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_crosssaber1);saber=2;break;
                                    case 2:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_crosssaber2);saber=0;break;
                                }
                            }
                        }
                        else if(i==3 && geatsflag!=0)
                        {
                            switch(geatsflag)
                            {

                                case 1:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_geats9_1);break;
                                case 2:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_geats9_2);break;
                                case 3:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_geats9_3);break;
                                case 4:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_geats9_4);break;

                            }
                        }
                        else if(i==5 && gavvmode!=0)
                        {
                            switch (gavvmode)
                            {
                                case 1:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.lpgavvmaster);break;
                                case 2:mp1=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_amazinggummy);break;
                            }
                        }
                        else
                        {
                            mp1 = MediaPlayer.create(ReiwaRiders1.this, finishersound.get(i));
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
                    if(i==0 && hellrise==1)
                    {
                        mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.henshinzeronehellrise);
                    }
                    else if(i==1 && primitive==1)
                    {
                        if(pdhenshin==0)
                        {
                            mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.henshinsaberprimitivedragon);
                            pdhenshin=1;
                        }
                        else
                        {
                            mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.henshinsaberprimitivedragonalt);
                            pdhenshin=0;
                        }
                    }
                    else if(i==5 && gavvmode!=0)
                    {
                        switch (gavvmode)
                        {
                            case 1: mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.henshingavvmaster);break;
                            case 2: mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.henshinamazinggummy);break;
                        }
                    }
                    else
                    {
                        mp = MediaPlayer.create(ReiwaRiders1.this, henshinsound.get(i));
                        if(i==5 && gavvmode==0)
                        {
                            gavvoverhenshin=1;
                        }
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
                    if(i==0)
                    {
                        switch(hellrise)
                        {
                            case 0:if(flag==0)
                            {
                                flag=1;
                                mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.zerotwo);
                            }
                            else if(flag==1)
                            {
                                flag=0;
                                mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.lpzeroone);
                            }break;
                            case 1:mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.zeroonehellrise);break;
                        }
                    }
                    else if(i==1 && primitive==1)
                    {
                        mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.saberprimitvedragon);
                    }
                    else if(i<=4 && flag==1)
                    {
                        flag=0;
                        mp=MediaPlayer.create(ReiwaRiders1.this,longpresssound.get(i));

                    }
                    else if(i==5)
                    {
                        switch(gavvmode)
                        {
                            case 0:if(gavvoverhenshin==1){
                                mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.oversmash);
                            }
                            else {
                                mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.gavvover);
                            }break;
                            case 1:mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.gavvmaster);break;
                            case 2:mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.amazinggummy);break;
                        }
                    }
                    else
                    {
                        mp=MediaPlayer.create(ReiwaRiders1.this,sound.get(i));
                        if(i<=4)
                        {
                            flag=1;
                        }
                    }
                    mp.start();
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
                    boolean downSwipe = diffY > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean upSwipe = diffY < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean rightSwipe = diffX > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    boolean leftSwipe= diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    if(i==0)
                    {
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
                        if(upSwipe && hellrise==0)
                        {
                            hellrise=1;
                            imageView.setImageResource(R.drawable.zeroonehellrise);
                        }
                        if(downSwipe && hellrise==1)
                        {
                            hellrise=0;
                            imageView.setImageResource(R.drawable.zerotwo);
                        }
                    }
                    else if(i==1)
                    {
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
                        if(upSwipe && primitive==0)
                        {
                            primitive=1;
                            imageView.setImageResource(R.drawable.saberprimitivedragon);
                        }
                        if(downSwipe && primitive==1)
                        {
                            primitive=0;
                            imageView.setImageResource(R.drawable.sabercross);
                        }
                    }
                    else if(i==3)
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
                            mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_geats9);
                            mp.start();
                            switch(geatsflag)
                            {
                                case 0:geatsflag=2;break;
                                case 1:geatsflag=0;break;
                            }
                        }
                        if(leftSwipe)
                        {
                            mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_geats9);
                            mp.start();
                            switch(geatsflag)
                            {
                                case 0:geatsflag=1;break;
                                case 2:geatsflag=0;break;
                            }
                        }
                        if(upSwipe)
                        {
                            mp=MediaPlayer.create(ReiwaRiders1.this,R.raw.finisher_geats9);
                            mp.start();
                            switch(geatsflag)
                            {
                                case 0:geatsflag=3;break;
                                case 4:geatsflag=0;break;
                            }
                        }
                        if(downSwipe) {
                            mp = MediaPlayer.create(ReiwaRiders1.this, R.raw.finisher_geats9);
                            mp.start();
                            switch (geatsflag)
                            {
                                case 0:geatsflag=4;break;
                                case 3:geatsflag=0;break;
                            }
                        }
                    }
                    else if(i==5)
                    {
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
                        if(gavvmode==0 && gavvoverhenshin==1)
                        {
                            if(leftSwipe)
                            {
                                overindex--;
                                if(overindex<0)
                                {
                                    overindex=gavvoversounds.size()-1;
                                }
                                mp=MediaPlayer.create(ReiwaRiders1.this,gavvoversounds.get(overindex));
                                mp.start();

                            }
                            if(rightSwipe)
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
                        if(gavvmode==1)
                        {
                            if(leftSwipe)
                            {
                                masterindex--;
                                if(masterindex<0)
                                {
                                    masterindex=gavvmastersounds.size()-1;
                                }
                                mp=MediaPlayer.create(ReiwaRiders1.this,gavvmastersounds.get(masterindex));
                                mp.start();

                            }
                            if(rightSwipe)
                            {
                                masterindex++;
                                if(masterindex>=gavvmastersounds.size())
                                {
                                    masterindex=0;
                                }
                                mp=MediaPlayer.create(ReiwaRiders1.this,gavvmastersounds.get(masterindex));
                                mp.start();
                            }
                        }
                        if (downSwipe)
                        {
                            switch(gavvmode)
                            {
                                case 0: gavvmode=1;masterindex=-1;imageView.setImageResource(R.drawable.gavvmaster);break;
                                case 2: gavvmode=0;overindex=-1;gavvoverhenshin=0;imageView.setImageResource(R.drawable.gavvover);break;
                            }
                        }
                        if (upSwipe)
                        {
                            switch(gavvmode)
                            {
                                case 0: gavvmode=2;imageView.setImageResource(R.drawable.gavvamazing);break;
                                case 1: gavvmode=0;overindex=-1;gavvoverhenshin=0;imageView.setImageResource(R.drawable.gavvover);break;
                            }
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
            if (mp1 != null) {
                mp1.release();
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
        if(mp1!=null) {
            mp1.release();
            mp1 = null;
            imageView.clearAnimation();
            imageView.setClickable(true);
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
        if(mp1!=null)
        {
            mp1.release();
            mp1=null;
        }
        super.onDestroy();
    }
}