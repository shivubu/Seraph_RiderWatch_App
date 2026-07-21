package com.example.kamen_app_01;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
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
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Showa extends BaseKamenActivity {
    int i=0,flag=0,amazon_mode,omega_finisher_index,neo_index,neo_finisher_index,blackrxmode;
    ImageView myLocalImage;
    int ichigo=0,amz=5,brx=11;
    private Drawable[] backgroundImages;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showa);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Animation fade= AnimationUtils.loadAnimation(this,R.anim.customfade);
        backgroundImages= new Drawable[]{ AppCompatResources.getDrawable(this,R.drawable.ichigo),
                AppCompatResources.getDrawable(this,R.drawable.nigo),
                AppCompatResources.getDrawable(this,R.drawable.v3),
                AppCompatResources.getDrawable(this,R.drawable.riderman),
                AppCompatResources.getDrawable(this,R.drawable.x),
                AppCompatResources.getDrawable(this,R.drawable.amazon),
                AppCompatResources.getDrawable(this,R.drawable.stronger),
                AppCompatResources.getDrawable(this,R.drawable.skyrider),
                AppCompatResources.getDrawable(this,R.drawable.super_1),
                AppCompatResources.getDrawable(this,R.drawable.zx),
                AppCompatResources.getDrawable(this,R.drawable.black),
                AppCompatResources.getDrawable(this,R.drawable.rx_ridewatch_1),
                AppCompatResources.getDrawable(this,R.drawable.shin_ridewatch_1),
                AppCompatResources.getDrawable(this,R.drawable.zo_ridewatch_1),
                AppCompatResources.getDrawable(this,R.drawable.j_ridewatch_1)
        };
        int[] sounds = {R.raw.ichigo,
                        R.raw.nigo,
                        R.raw.v3,
                        R.raw.riderman,
                        R.raw.x,
                        R.raw.amazon,
                        R.raw.stronger,
                        R.raw.skyrider,
                        R.raw.super1,
                        R.raw.zx,
                        R.raw.black,
                        R.raw.blackrx,
                        R.raw.shin,
                        R.raw.zo,
                        R.raw.j
                        };
        int[] longprsssounds={R.raw.lpichigo,R.raw.lpnigo,R.raw.lpv3,R.raw.lpriderman,R.raw.lpx,R.raw.lpamazon,R.raw.lpstronger,R.raw.lpskyrider,R.raw.lpsuper1,R.raw.lpzx,R.raw.lpblack,R.raw.lpblackrx,R.raw.lpshin,R.raw.lpzo,R.raw.lpj
        };
        int[] henshinsounds={R.raw.henshin_ichigo,R.raw.henshin_nigo,R.raw.henshin_v3,R.raw.henshin_riderman,R.raw.henshin_x,R.raw.henshin_amazon,R.raw.henshin_stronger,R.raw.henshin_skyrider,R.raw.henshin_super1,R.raw.henshin_zx,R.raw.henshin_black,R.raw.henshin_blackrx,R.raw.henshin_shin,R.raw.henshin_zo,R.raw.henshin_j
        };
        int[] finishersounds={R.raw.finisher_ichigo,R.raw.finisher_nigo,R.raw.finisher_v3,R.raw.finisher_riderman,R.raw.finisher_x,R.raw.finisher_amazon,R.raw.finisher_stronger,R.raw.finisher_skyrider,R.raw.finisher_super1,R.raw.finisher_zx,R.raw.finisher_black,R.raw.finisher_blackrx,R.raw.finisher_shin,R.raw.finisher_zo,R.raw.finisher_j
        };
        int[] neo_sounds={R.raw.neo_blade,R.raw.neo_needle,R.raw.neo_claw};
        ArrayList<Integer> sound = new ArrayList<>();
        for (int j : sounds) {
            sound.add(j);
        }
        ArrayList<Integer> longpress = new ArrayList<>();
        for (int longprsssound : longprsssounds) {
            longpress.add(longprsssound);
        }
        ArrayList<Integer> henshin=new ArrayList<>();
        for (int henshinsound : henshinsounds) {
            henshin.add(henshinsound);
        }
        ArrayList<Integer> finisher=new ArrayList<>();
        for (int finishersound : finishersounds) {
            finisher.add(finishersound);
        }
        ArrayList<Integer> neo=new ArrayList<>();
        for (int neosound : neo_sounds) {
            neo.add(neosound);
        }
        myLocalImage = findViewById(R.id.imageView11);
        myLocalImage.setImageDrawable(backgroundImages[i]);
        myLocalImage.setFocusable(true);
        myLocalImage.requestFocus();
        myLocalImage.setOnTouchListener(new View.OnTouchListener() {
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
                    myLocalImage.startAnimation(fade);
                    mp = MediaPlayer.create(Showa.this, R.raw.judgement_finishtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        if(i==amz && amazon_mode!=0)
                        {
                            switch (amazon_mode)
                            {
                                case 1: mp1=MediaPlayer.create(Showa.this,R.raw.finisher_amazon_alpha_slash);break;
                                case 2:
                                    switch (omega_finisher_index)
                                    {
                                        case 0:mp1=MediaPlayer.create(Showa.this,R.raw.finisher_amazon_omega_punish);omega_finisher_index=1;break;
                                        case 1:mp1=MediaPlayer.create(Showa.this,R.raw.finisher_amazon_omega_break);omega_finisher_index=2;break;
                                        case 2:mp1=MediaPlayer.create(Showa.this,R.raw.finisher_amazon_omega_strike);omega_finisher_index=0;break;
                                    }break;
                                case 3:
                                    switch (neo_finisher_index)
                                    {
                                        case 0:mp1=MediaPlayer.create(Showa.this,R.raw.finisher_amazon_neo_break);neo_finisher_index=1;break;
                                        case 1:mp1=MediaPlayer.create(Showa.this,R.raw.finisher_amazon_neo_strike);neo_finisher_index=2;break;
                                        case 2:mp1=MediaPlayer.create(Showa.this,R.raw.finisher_amazon_neo_slash);neo_finisher_index=0;break;
                                    }break;

                            }
                        }
                        else
                        {
                            mp1=MediaPlayer.create(Showa.this,finisher.get(i));
                        }
                        mp1.start();
                        mp1.setOnCompletionListener(mp2 -> myLocalImage.clearAnimation());
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
                    myLocalImage.startAnimation(fade);
                    mp=MediaPlayer.create(Showa.this,R.raw.judgementformtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        if(i==amz && amazon_mode!=0)
                        {
                            switch(amazon_mode)
                            {
                                case 1:mp1=MediaPlayer.create(Showa.this,R.raw.henshin_amazon_alpha);break;
                                case 2:mp1=MediaPlayer.create(Showa.this,R.raw.henshin_amazon_omega);break;
                                case 3:mp1=MediaPlayer.create(Showa.this,R.raw.henshin_amazon_neo);break;
                            }
                        }
                        else if (i==brx && blackrxmode!=0) {
                            switch(blackrxmode)
                            {
                                case 1:mp1=MediaPlayer.create(Showa.this,R.raw.henshin_roborider);break;
                                case 2:mp1=MediaPlayer.create(Showa.this,R.raw.henshin_biorider);break;
                            }
                        }
                        else
                        {
                            mp1=MediaPlayer.create(Showa.this,henshin.get(i));
                        }
                        mp1.start();
                        mp1.setOnCompletionListener(mp2 -> myLocalImage.clearAnimation());
                    });
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
                    myLocalImage.startAnimation(fade);
                    if(flag==0)
                    {
                        flag=1;
                        if(i==amz && amazon_mode!=0)
                        {
                            switch(amazon_mode)
                            {
                                case 1:mp=MediaPlayer.create(Showa.this,R.raw.amazonalfa);break;
                                case 2:mp=MediaPlayer.create(Showa.this,R.raw.amazonomega);break;
                                case 3:mp=MediaPlayer.create(Showa.this,R.raw.amazonneo);break;
                            }
                        }
                        else if (i==brx && blackrxmode!=0)
                        {
                            switch(blackrxmode)
                            {
                                case 1:mp=MediaPlayer.create(Showa.this,R.raw.roborider);break;
                                case 2:mp=MediaPlayer.create(Showa.this,R.raw.biorider);break;
                            }
                        }
                        else
                        {
                            mp = MediaPlayer.create(Showa.this, sound.get(i));
                        }
                    }
                    else {
                        flag=0;
                        if(i==amz && amazon_mode!=0)
                        {
                            switch(amazon_mode)
                            {
                                case 1:mp=MediaPlayer.create(Showa.this,R.raw.lpamazonalfa);break;
                                case 2:mp=MediaPlayer.create(Showa.this,R.raw.lpamazonomega);break;
                                case 3:mp=MediaPlayer.create(Showa.this,R.raw.lpamazonneo);break;
                            }
                        }
                        else if (i==brx && blackrxmode!=0)
                        {
                            switch(blackrxmode)
                            {
                                case 1:mp=MediaPlayer.create(Showa.this,R.raw.lproborider);break;
                                case 2:mp=MediaPlayer.create(Showa.this,R.raw.lpbiorider);break;
                            }
                        }
                        else
                        {
                            mp = MediaPlayer.create(Showa.this,longpress.get(i));
                        }
                    }
                    mp.start();
                    mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
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
                    myLocalImage.clearAnimation();
                    if(i==ichigo)
                    {
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
                            myLocalImage.startAnimation(fade);
                            mp=MediaPlayer.create(Showa.this,R.raw.riderkick);
                            mp.start();
                            mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
                        }
                    }
                    else if (i==amz)
                    {
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
                        if(upSwipe) {
                            flag=0;
                            mp=MediaPlayer.create(Showa.this,R.raw.transition2);
                            mp.start();
                            switch (amazon_mode)
                            {
                                case 0:myLocalImage.setImageResource(R.drawable.amazon_alfa_1);amazon_mode=1;break;
                                case 1:myLocalImage.setImageResource(R.drawable.amazon_omega_1);amazon_mode=2;omega_finisher_index=0;break;
                                case 2:myLocalImage.setImageResource(R.drawable.amazon_neo_ridewatch_1);amazon_mode=3;neo_index=-1;neo_finisher_index=0;break;
                                case 3:myLocalImage.setImageResource(R.drawable.amazon);amazon_mode=0;break;
                            }
                        }
                        else if(downSwipe)
                        {
                            flag=0;
                            mp=MediaPlayer.create(Showa.this,R.raw.transition2);
                            mp.start();
                            switch (amazon_mode)
                            {
                                case 0:myLocalImage.setImageResource(R.drawable.amazon_neo_ridewatch_1);amazon_mode=3;neo_index=-1;neo_finisher_index=0;break;
                                case 3:myLocalImage.setImageResource(R.drawable.amazon_omega_1);amazon_mode=2;omega_finisher_index=0;break;
                                case 2:myLocalImage.setImageResource(R.drawable.amazon_alfa_1);amazon_mode=1;break;
                                case 1:myLocalImage.setImageResource(R.drawable.amazon);amazon_mode=0;break;
                            }
                        }
                        else if(rightSwipe && amazon_mode==3)
                        {
                            neo_index++;
                            if(neo_index==3)
                            {
                                neo_index=0;
                            }
                            mp=MediaPlayer.create(Showa.this,neo.get(neo_index));
                            mp.start();

                        }
                        else if(leftSwipe && amazon_mode==3)
                        {
                            neo_index--;
                            if(neo_index<0)
                            {
                                neo_index=2;
                            }
                            mp=MediaPlayer.create(Showa.this,neo.get(neo_index));
                            mp.start();
                        }
                    }
                    else if(i==brx)
                    {
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
                        if(upSwipe)
                        {
                            flag=0;
                            mp=MediaPlayer.create(Showa.this,R.raw.transition2);
                            mp.start();
                            switch (blackrxmode)
                            {
                                case 0:myLocalImage.setImageResource(R.drawable.robo_rider_ridewatch_1);blackrxmode=1;break;
                                case 1:myLocalImage.setImageResource(R.drawable.bio_rider_ridewatch_1);blackrxmode=2;break;
                                case 2:myLocalImage.setImageResource(R.drawable.rx_ridewatch_1);blackrxmode=0;break;
                            }
                        }
                        else if(downSwipe)
                        {
                            flag=0;
                            mp=MediaPlayer.create(Showa.this,R.raw.transition2);
                            mp.start();
                            switch (blackrxmode)
                            {
                                case 0:myLocalImage.setImageResource(R.drawable.bio_rider_ridewatch_1);blackrxmode=2;break;
                                case 2:myLocalImage.setImageResource(R.drawable.robo_rider_ridewatch_1);blackrxmode=1;break;
                                case 1:myLocalImage.setImageResource(R.drawable.rx_ridewatch_1);blackrxmode=0;break;
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
    protected ImageView getLocalImageView() {
        return myLocalImage;}
    protected Class<?> getBackTargetClass() {
        // This tells the Base class togo to the Menu when back is pressed
        return Menu.class;
    }
    @Override
    protected View getRotaryView() {// This tells the base class to unbind the listener from the imageView
        return myLocalImage;
    }
    @Override
    protected void setupRotaryLogic() {
        myLocalImage.setOnGenericMotionListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_SCROLL &&
                    motionEvent.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)){
                myLocalImage.setFocusable(true);
                myLocalImage.requestFocus();
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
                myLocalImage.clearAnimation();
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    mp=MediaPlayer.create(Showa.this,R.raw.transition2);
                    mp.start();
                    i++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    mp=MediaPlayer.create(Showa.this,R.raw.transition2);
                    mp.start();
                    i--;
                }
                // Wrap around the image array
                if (i < 0) {
                    i = backgroundImages.length - 1;
                } else if (i >= backgroundImages.length) {
                    i = 0;
                }
                // Update the background image
                if (backgroundImages.length>0) {
                    flag=0;
                    myLocalImage.setImageDrawable(backgroundImages[i]);
                    switch(i)
                    {
                        case 5: amazon_mode=omega_finisher_index=neo_finisher_index=0;neo_index=-1;break;
                        case 11: blackrxmode=0;break;
                    }
                }
                return true;
            }
            return false;
        });
    }
}