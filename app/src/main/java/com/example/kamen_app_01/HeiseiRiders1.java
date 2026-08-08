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

public class HeiseiRiders1 extends BaseKamenActivity {
    int i=0,flag=0,kabuto=0,hculoop=0,blade,decade_index;
    private Drawable[] backgroundImages;
    ImageView myLocalImage;
    int bkf=4,hk=6,deno=7,kiva=8,decade=9;
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
        Animation fade= AnimationUtils.loadAnimation(this,R.anim.customfade);
        backgroundImages= new Drawable[]{ AppCompatResources.getDrawable(this,R.drawable.kuuga),
                AppCompatResources.getDrawable(this,R.drawable.agito),
                AppCompatResources.getDrawable(this,R.drawable.ryuki),
                AppCompatResources.getDrawable(this,R.drawable.faiz),
                AppCompatResources.getDrawable(this,R.drawable.blade),
                AppCompatResources.getDrawable(this,R.drawable.hibiki),
                AppCompatResources.getDrawable(this,R.drawable.kabuto),
                AppCompatResources.getDrawable(this,R.drawable.deno),
                AppCompatResources.getDrawable(this,R.drawable.kiva),
                AppCompatResources.getDrawable(this,R.drawable.decadec)
        };
        int[] sounds = {R.raw.kuugault, R.raw.agitoshining,R.raw.ryukisurvive,R.raw.faizblaster,R.raw.bladeking,R.raw.hibikiarmed,R.raw.kabutohyper,R.raw.denoliner,R.raw.kivaemperor,R.raw.decadecomplete};
        int[] henshinsounds={R.raw.henshinkuugault,R.raw.henshinagitoshining,R.raw.henshinryukisurvive,R.raw.henshinfaizblaster,R.raw.henshinbladeking,R.raw.henshinhibikiarmed,R.raw.henshinkabutohyper,R.raw.henshindenoliner,R.raw.henshinkivaemperor,R.raw.henshindecadecomplete};
        int[] longprsssounds={R.raw.lpkuuga,R.raw.lpagito,R.raw.lpryuki,R.raw.lpfaiz,R.raw.lpblade,R.raw.lphibiki,R.raw.lpkabuto,R.raw.lpdeno,R.raw.lpkiva,R.raw.lpdecade};
        int[] finishersounds={R.raw.finisher_kuugault,R.raw.finisher_agitoshining,R.raw.finisher_ryukisurvive,R.raw.finisher_faizblaster,R.raw.finisher_bladeking_1,R.raw.finisher_hibikiarmed,R.raw.finisher_kabutohyper,R.raw.finisher_denoliner,R.raw.finisher_kivaemperor,R.raw.finisher_decadecomplete};
        int[] decadesounds={R.raw.decade_invisible,R.raw.decade_illusion,R.raw.decade_finalformride,R.raw.decade_finalkamenattackformride,R.raw.decade_rekka,R.raw.decade_terebi_kun};
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
        ArrayList<Integer> finishersound = new ArrayList<>();
        for (int j : finishersounds) {
            finishersound.add(j);
        }
        ArrayList<Integer> decadesound = new ArrayList<>();
        for (int j : decadesounds) {
            decadesound.add(j);
        }
        myLocalImage = findViewById(R.id.imageView6);
        myLocalImage.setImageDrawable(backgroundImages[i]);
        myLocalImage.setFocusable(true);
        myLocalImage.requestFocus();
        myLocalImage.setOnTouchListener(new View.OnTouchListener() {
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
                    if(i==bkf)
                    {
                        myLocalImage.clearAnimation();
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
                        mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.transition2);
                        mp.start();
                        if(upSwipe)
                        {
                            blade=0;
                        }
                        else if(rightSwipe)
                        {
                            blade=1;
                        }
                        else if(downSwipe)
                        {
                            blade=2;
                        }
                        else if(leftSwipe)
                        {
                            blade=3;
                        }
                    }
                    else if(i==hk)
                    {
                        myLocalImage.clearAnimation();
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
                        if(loopPlayer!=null)
                        {
                            loopPlayer.release();
                            loopPlayer=null;
                        }
                        if(leftSwipe)
                        {
                            mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.transition2);
                            mp.start();
                            switch(kabuto)
                            {
                                case 0:kabuto=1;break;
                                case 2:kabuto=0;break;
                            }
                        }
                        if(rightSwipe)
                        {
                            mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.transition2);
                            mp.start();
                            switch(kabuto)
                            {
                                case 0:kabuto=2;break;
                                case 1:kabuto=0;break;
                            }
                        }
                        if (downSwipe) {
                            if(hculoop==1)
                            {
                                myLocalImage.clearAnimation();
                                mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.hyperclockover);
                                mp.start();
                                hculoop=0;
                            }
                        }
                        if (upSwipe)
                        {
                            myLocalImage.startAnimation(fade);
                            mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.hyperclockup);
                            mp.start();
                            mp.setOnCompletionListener(mp -> {
                                loopPlayer=PerfectLoopMediaPlayer.create(HeiseiRiders1.this,R.raw.hyperclockuploop);
                                loopPlayer.start();
                                hculoop=1;
                            });
                        }
                    } else if (i==deno) {
                        myLocalImage.clearAnimation();
                        if (mp != null) {
                            mp.release();
                            mp = null;
                        }
                        if (mp1 != null) {
                            mp1.release();
                            mp1 = null;
                        }
                        if (loopPlayer != null) {
                            loopPlayer.release();
                            loopPlayer = null;
                        }
                        if (upSwipe || downSwipe) {
                            mp = MediaPlayer.create(HeiseiRiders1.this, R.raw.denliner_alt);
                            mp.start();
                        }

                    }
                    else if (i==kiva) {
                        myLocalImage.clearAnimation();
                        if (mp != null) {
                            mp.release();
                            mp = null;
                        }
                        if (mp1 != null) {
                            mp1.release();
                            mp1 = null;
                        }
                        if (loopPlayer != null) {
                            loopPlayer.release();
                            loopPlayer = null;
                        }
                        if (leftSwipe) {
                            mp = MediaPlayer.create(HeiseiRiders1.this, R.raw.garulu_fever);
                            mp.start();
                        } else if (downSwipe) {
                            mp = MediaPlayer.create(HeiseiRiders1.this, R.raw.basha_fever);
                            mp.start();
                        }
                        else if(rightSwipe)
                        {
                            mp = MediaPlayer.create(HeiseiRiders1.this, R.raw.dogga_fever);
                            mp.start();
                        }
                        else if(upSwipe)
                        {
                            mp = MediaPlayer.create(HeiseiRiders1.this, R.raw.finisher_kivaemperor_zanvat1);
                            mp.start();
                            mp.setOnCompletionListener(mp -> {
                                mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.finisher_kivaemperor_zanvat2);
                                mp1.start();
                            });
                        }

                    }
                    else if (i==decade) {
                        myLocalImage.clearAnimation();
                        if (mp != null) {
                            mp.release();
                            mp = null;
                        }
                        if (mp1 != null) {
                            mp1.release();
                            mp1 = null;
                        }
                        if (loopPlayer != null) {
                            loopPlayer.release();
                            loopPlayer = null;
                        }
                        if (upSwipe || downSwipe) {
                            mp = MediaPlayer.create(HeiseiRiders1.this, R.raw.aurora_curtain);
                            mp.start();
                        }
                        else if(rightSwipe) {
                            decade_index++;
                            if(decade_index>=decadesound.size())
                            {
                                decade_index=0;
                            }
                            mp=MediaPlayer.create(HeiseiRiders1.this,decadesound.get(decade_index));
                            mp.start();
                        }
                        else if(leftSwipe)
                        {
                            decade_index--;
                            if(decade_index<0)
                            {
                                decade_index=decadesound.size()-1;
                            }
                            mp=MediaPlayer.create(HeiseiRiders1.this,decadesound.get(decade_index));
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
                    if(loopPlayer!=null)
                    {
                        loopPlayer.release();
                        loopPlayer=null;
                    }
                    myLocalImage.startAnimation(fade);
                    mp = MediaPlayer.create(HeiseiRiders1.this,R.raw.judgement_finishtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        if(i==bkf)
                        {
                            switch(blade)
                            {
                                case 0:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.finisher_bladeking_1);break;
                                case 1:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.finisher_bladeking_2);break;
                                case 2:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.finisher_bladeking_3);break;
                                case 3:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.finisher_bladeking_4);break;
                            }
                        }
                        else if(i==hk && kabuto!=0)
                        {
                            switch (kabuto)
                            {
                                case 1:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.maxhypercyclone);break;
                                case 2:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.maxhypertyphoon);break;
                            }
                        }
                        else {
                            mp1 = MediaPlayer.create(HeiseiRiders1.this, finishersound.get(i));
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
                    if(loopPlayer!=null)
                    {
                        loopPlayer.release();
                        loopPlayer=null;
                    }
                    myLocalImage.startAnimation(fade);
                    mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.finaljudgementformtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        mp1=MediaPlayer.create(HeiseiRiders1.this,henshinsound.get(i));
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
                    if(loopPlayer!=null)
                    {
                        loopPlayer.release();
                        loopPlayer=null;
                    }
                    myLocalImage.startAnimation(fade);
                    if(flag==0){
                        flag=1;
                        mp = MediaPlayer.create(HeiseiRiders1.this, sound.get(i));
                        mp.start();
                        mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
                    }
                    else if(flag==1)
                    {
                        flag=0;
                        mp = MediaPlayer.create(HeiseiRiders1.this, longpress.get(i));
                        mp.start();
                        mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
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
                if(mp1!=null) {
                    mp1.release();
                    mp1 = null;
                }
                if(loopPlayer!=null)
                {
                    loopPlayer.release();
                    loopPlayer=null;
                }
                myLocalImage.clearAnimation();
                flag=0;
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.transition2);
                    mp.start();
                    i++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.transition2);
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
                    myLocalImage.setImageDrawable(backgroundImages[i]);
                    switch(i)
                    {
                        case 4:blade=0;
                        case 6:kabuto=hculoop=0;break;
                        case 9:decade_index=-1;break;
                    }
                }
                return true;
            }
            return false;
        });
    }


}