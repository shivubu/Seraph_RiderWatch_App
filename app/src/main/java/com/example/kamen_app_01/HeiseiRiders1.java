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

public class HeiseiRiders1 extends AppCompatActivity {
    int i=0,flag=0,kabuto=0,hculoop=0,blade;
    MediaPlayer mp,mp1,end,hyperclockup;
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
        Animation fade= AnimationUtils.loadAnimation(this,R.anim.customfade);
        int[] rw = {R.drawable.kuuga, R.drawable.agito,R.drawable.ryuki,R.drawable.faiz,R.drawable.blade,R.drawable.hibiki,R.drawable.kabuto,R.drawable.deno,R.drawable.kiva,R.drawable.decadec};
        int[] sounds = {R.raw.kuugault, R.raw.agitoshining,R.raw.ryukisurvive,R.raw.faizblaster,R.raw.bladeking,R.raw.hibikiarmed,R.raw.kabutohyper,R.raw.denoliner,R.raw.kivaemperor,R.raw.decadecomplete};
        int[] henshinsounds={R.raw.henshinkuugault,R.raw.henshinagitoshining,R.raw.henshinryukisurvive,R.raw.henshinfaizblaster,R.raw.henshinbladeking,R.raw.henshinhibikiarmed,R.raw.henshinkabutohyper,R.raw.henshindenoliner,R.raw.henshinkivaemperor,R.raw.henshindecadecomplete};
        int[] longprsssounds={R.raw.lpkuuga,R.raw.lpagito,R.raw.lpryuki,R.raw.lpfaiz,R.raw.lpblade,R.raw.lphibiki,R.raw.lpkabuto,R.raw.lpdeno,R.raw.lpkiva,R.raw.lpdecade};
        int[] finishersounds={R.raw.finisher_kuugault,R.raw.finisher_agitoshining,R.raw.finisher_ryukisurvive,R.raw.finisher_faizblaster,R.raw.finisher_bladeking_1,R.raw.finisher_hibikiarmed,R.raw.finisher_kabutohyper,R.raw.finisher_denoliner,R.raw.finisher_kivaemperor,R.raw.finisher_decadecomplete};
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
        ArrayList<Integer> finishersound = new ArrayList<>();
        for (int j : finishersounds) {
            finishersound.add(j);
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
                }
                if(mp1!=null) {
                    mp1.release();
                    mp1 = null;
                }
                if(hyperclockup!=null)
                {
                    hyperclockup.release();
                    hyperclockup=null;
                }
                imageView.clearAnimation();
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
                    i = screen.size() - 1;
                } else if (i >= screen.size()) {
                    i = 0;
                }
                // Update the background image
                if (!screen.isEmpty()) {
                    imageView.setImageResource(screen.get(i));
                    switch(i)
                    {
                        case 4:blade=0;
                        case 6:kabuto=hculoop=0;break;
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
                    if(i==6)
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
                        if(hyperclockup!=null)
                        {
                            hyperclockup.release();
                            hyperclockup=null;
                        }
                        if(leftSwipe)
                        {
                            switch(kabuto)
                            {
                                case 0:kabuto=1;break;
                                case 2:kabuto=0;break;
                            }
                        }
                        if(rightSwipe)
                        {
                            switch(kabuto)
                            {
                                case 0:kabuto=2;break;
                                case 1:kabuto=0;break;
                            }
                        }
                        if (downSwipe) {
                            if(hculoop==1)
                            {
                                imageView.clearAnimation();
                                mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.hyperclockover);
                                mp.start();
                                hculoop=0;
                            }
                        }
                        if (upSwipe)
                        {
                            imageView.startAnimation(fade);
                            mp=MediaPlayer.create(HeiseiRiders1.this,R.raw.hyperclockup);
                            mp.start();
                            mp.setOnCompletionListener(mp -> {
                                hyperclockup=MediaPlayer.create(HeiseiRiders1.this,R.raw.hyperclockuploop);
                                hyperclockup.setLooping(true);
                                hyperclockup.start();
                                hculoop=1;
                            });
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
                    if(hyperclockup!=null)
                    {
                        hyperclockup.release();
                        hyperclockup=null;
                    }
                    imageView.startAnimation(fade);
                    mp = MediaPlayer.create(HeiseiRiders1.this,R.raw.judgement_finishtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        if(i==4)
                        {
                            switch(blade)
                            {
                                case 0:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.finisher_bladeking_1);blade=1;break;
                                case 1:mp1=MediaPlayer.create(HeiseiRiders1.this,R.raw.finisher_bladeking_2);blade=0;break;
                            }
                        }
                        else if(i==6 && kabuto!=0)
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
                    if(hyperclockup!=null)
                    {
                        hyperclockup.release();
                        hyperclockup=null;
                    }
                    imageView.startAnimation(fade);
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
                    if(mp1!=null)
                    {
                        mp1.release();
                        mp1=null;
                    }
                    if(hyperclockup!=null)
                    {
                        hyperclockup.release();
                        hyperclockup=null;
                    }
                    imageView.startAnimation(fade);
                    if(flag==0){
                        flag=1;
                        mp = MediaPlayer.create(HeiseiRiders1.this, sound.get(i));
                        mp.start();
                        mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    }
                    else if(flag==1)
                    {
                        flag=0;
                        mp = MediaPlayer.create(HeiseiRiders1.this, longpress.get(i));
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
            if(hyperclockup!=null)
            {
                hyperclockup.release();
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
        }
        if(mp1!=null) {
            mp1.release();
            mp1 = null;
        }
        if(hyperclockup!=null)
        {
            hyperclockup.release();
            hyperclockup=null;
        }
        imageView.clearAnimation();
        imageView.setClickable(true);
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