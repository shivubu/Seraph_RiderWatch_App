package com.example.kamen_app_01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    MediaPlayer mp,mp1,end;
    private Drawable[] backgroundImages;
    private int currentImageIndex = 0,flag=0,zt_index,ztweap_index,zt_flag=0,hazard_flag=0,genmflag=0,fumetsuflag=0,bahamut=0,sabermode=0,flag1=0,ohmaflag=0,superherosenki1=0,superherosenki2=0,wonder1=0,wonder2=0,geatsflag=0;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Animation fade= AnimationUtils.loadAnimation(this,R.anim.customfade);
        backgroundImages= new Drawable[]{ AppCompatResources.getDrawable(this,R.drawable.seraph),
                AppCompatResources.getDrawable(this,R.drawable.faiznext),
                AppCompatResources.getDrawable(this,R.drawable.decadecomplete21),
                AppCompatResources.getDrawable(this,R.drawable.oootajadoreternity),
                AppCompatResources.getDrawable(this,R.drawable.exaidnovel),
                AppCompatResources.getDrawable(this,R.drawable.genmmusou),
                AppCompatResources.getDrawable(this,R.drawable.crossbuild),
                AppCompatResources.getDrawable(this,R.drawable.evoltblackhole),
                AppCompatResources.getDrawable(this,R.drawable.omaz),
                AppCompatResources.getDrawable(this,R.drawable.zerothree),
                AppCompatResources.getDrawable(this,R.drawable.saber),
                AppCompatResources.getDrawable(this,R.drawable.geatsdea),
                AppCompatResources.getDrawable(this,R.drawable.gotchard)
        };
        int[] sounds = {R.raw.seraph0,R.raw.faiznext,R.raw.decadecomplete21,R.raw.oootajadoreternity,R.raw.exaidnovel,R.raw.genmmusou,R.raw.crossbuild,R.raw.evolblackhole,R.raw.omazio,R.raw.zerothree,R.raw.saberwa,R.raw.geatsdea,R.raw.gotchardultima};
        int[] henshinsounds={R.raw.henshinseraph_ver1,R.raw.henshinfaiznext,R.raw.henshindecadecomplete21,R.raw.henshinoootajadoreternity,R.raw.henshinexaidnovel,R.raw.henshingenmmusou,R.raw.henshincrossbuild,R.raw.henshinevolblackhole,R.raw.henshinzioohma,R.raw.henshinzerothree,R.raw.henshinsaberwa,R.raw.henshingeatsdea2,R.raw.henshingotchardultima};
        int[] longpresssounds={R.raw.finisher_seraph,R.raw.lpfaiznext,R.raw.finisher_decadecomplete,R.raw.lpoootajadoreternity,R.raw.lpexaidnovel,R.raw.lpgenmmusou,R.raw.lpcrossbuild,R.raw.finisher_evolblackhole,R.raw.finisher_zioohma,R.raw.lpzerothree,R.raw.finisher_saberwa1,R.raw.lpgeatsdea,R.raw.lpgotchardultima};
        int[] zt_sounds={R.raw.zt_create,R.raw.zt_singularity,R.raw.zt_ability,R.raw.zt_there_ark_ability,R.raw.zt_outsiders_ability};
        int[] ztweap_sounds={R.raw.attache_calibur,R.raw.attache_shotgun,R.raw.attache_arrow,R.raw.shotriser,R.raw.slashriser,R.raw.thousand_jacker,R.raw.authorise_blaster,R.raw.hopper_blade};
        ArrayList<Integer> sound = new ArrayList<>();
        for (int j : sounds) {
            sound.add(j);
        }
        ArrayList<Integer> henshinsound = new ArrayList<>();
        for (int j : henshinsounds) {
            henshinsound.add(j);
        }
        ArrayList<Integer> longpress = new ArrayList<>();
        for (int longpresssound : longpresssounds) {
            longpress.add(longpresssound);
        }
        ArrayList<Integer> zt_sound = new ArrayList<>();
        for (int j : zt_sounds) {
            zt_sound.add(j);
        }
        ArrayList<Integer> ztweap_sound = new ArrayList<>();
        for (int j : ztweap_sounds) {
            ztweap_sound.add(j);
        }
        imageView = findViewById(R.id.imageView);
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
                flag1=0;
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                    mp.start();
                    currentImageIndex++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                    mp.start();
                    currentImageIndex--;
                }
                // Wrap around the image array
                if (currentImageIndex < 0) {
                    currentImageIndex = backgroundImages.length - 1;
                } else if (currentImageIndex >= backgroundImages.length) {
                    currentImageIndex = 0;
                }
                // Update the background image
                if (backgroundImages.length > 0) {
                    if(currentImageIndex==5 && fumetsuflag==1)
                    {
                        imageView.setImageResource(R.drawable.genmfumetsu);
                    }
                    else
                    {
                        imageView.setImageDrawable(backgroundImages[currentImageIndex]);
                    }
                    switch(currentImageIndex)
                    {
                        case 8: ohmaflag=0;break;
                        case 9: zt_index=-1;ztweap_index=-1;break;
                        case 10: sabermode=0;bahamut=0;superherosenki1=superherosenki2=0;wonder1=wonder2=0;break;
                        case 11: geatsflag=0;break;
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
                    mp=MediaPlayer.create(MainActivity.this,R.raw.judgement_finishtime);
                    mp.start();
                    mp.setOnCompletionListener(mp -> {
                        if(currentImageIndex==5 && flag==1 || currentImageIndex==6 && hazard_flag==1 || currentImageIndex==8 && ohmaflag==1 || currentImageIndex==9 && zt_flag==1)
                        {
                            if(currentImageIndex==5)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmmusoualt);
                                genmflag=1;
                            }
                            if(currentImageIndex==6)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.lpcrossbuildhazard);
                            }
                            if(currentImageIndex==8)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_ohmazio);
                            }
                            if(currentImageIndex==9)
                            {
                                mp1 = MediaPlayer.create(MainActivity.this, R.raw.lpzerothreealt);
                                zt_flag=0;
                            }
                        }
                        else if(currentImageIndex==5 && fumetsuflag==1)
                        {
                            mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmhyperfumetsu);
                        }
                        else if(currentImageIndex==10)
                        {
                            if(sabermode==0)
                            {
                                switch(wonder2)
                                {
                                    case 0 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_saberwa1);wonder2=1;break;
                                    case 1 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_saberwa2);wonder2=2;break;
                                    case 2 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_saberwa3);wonder2=0;break;
                                }
                            }
                            else if(sabermode==1)
                            {
                                switch(bahamut)
                                {
                                    case 0 : mp1=MediaPlayer.create(MainActivity.this,R.raw.lpsaberub1);bahamut=1;break;
                                    case 1 : mp1=MediaPlayer.create(MainActivity.this,R.raw.lpsaberub2);bahamut=0;break;
                                }
                            }
                            else if(sabermode==2)
                            {
                                switch(superherosenki2)
                                {
                                    case 0 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_sabershs1);superherosenki2=1;break;
                                    case 1 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_sabershs2);superherosenki2=0;break;
                                }
                            }
                        }
                        else if(currentImageIndex==11 && geatsflag!=0)
                        {
                            switch(geatsflag)
                            {
                                case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgeatsdea_1);
                                        break;
                                case 2:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgeatsdea_2);
                                        break;
                            }
                        }
                        else {
                            mp1 = MediaPlayer.create(MainActivity.this, longpress.get(currentImageIndex));
                            if (currentImageIndex == 9 && zt_flag == 0) {
                                zt_flag = 1;
                            }
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
                    if(currentImageIndex==5 && fumetsuflag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshingenmhyperfumetsu);
                    }
                    else if(currentImageIndex==6 && hazard_flag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshincrossbuildhazard);

                    }
                    else if(currentImageIndex==8 && ohmaflag==1)
                    {
                        ohmaflag=0;
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshinohmazio);
                    }
                    else if(currentImageIndex==10 && sabermode!=0)
                    {
                        if(sabermode==1)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.henshinsaberub);
                        }
                        else if(sabermode==2)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.henshinsabershs);
                        }
                    }
                    else
                    {
                        mp = MediaPlayer.create(MainActivity.this, henshinsound.get(currentImageIndex));
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
                    if(currentImageIndex==5 && fumetsuflag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.genmhyperfumetsu);
                    }
                    else if(flag1==1)
                    {
                        if(currentImageIndex==2)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpdecadecomplete21);
                        }
                        if(currentImageIndex==7)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpevolblackhole);
                        }
                        if(currentImageIndex==8)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpohma);
                        }
                    }
                    else if(currentImageIndex==10)
                    {
                        if(sabermode==0)
                        {
                            switch(wonder1)
                            {
                                case 0 : mp=MediaPlayer.create(MainActivity.this,R.raw.saberwa);wonder1=1;break;
                                case 1 : mp=MediaPlayer.create(MainActivity.this,R.raw.saberwa1);wonder1=2;break;
                                case 2 : mp=MediaPlayer.create(MainActivity.this,R.raw.saberwa2);wonder1=0;break;
                            }
                        }
                        else if(sabermode==1)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.saberub);
                        }
                        else if(sabermode==2)
                        {
                            switch(superherosenki1)
                            {
                                case 0 : mp=MediaPlayer.create(MainActivity.this,R.raw.sabershs);superherosenki1=1;break;
                                case 1 : mp=MediaPlayer.create(MainActivity.this,R.raw.lpsabershs1);superherosenki1=2;break;
                                case 2 : mp=MediaPlayer.create(MainActivity.this,R.raw.lpsabershs2);superherosenki1=0;break;
                            }
                        }
                    }
                    else{
                        mp = MediaPlayer.create(MainActivity.this, sound.get(currentImageIndex));
                        if(currentImageIndex==2 || currentImageIndex==7 || currentImageIndex==8 )
                        {
                            flag1=1;
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
                    boolean b1 = diffY > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean b2 = diffY < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean b = diffX > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    if(currentImageIndex==5)
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
                        if(diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE && flag==0)
                        {
                            imageView.startAnimation(fade);
                            mp=MediaPlayer.create(MainActivity.this,R.raw.genmpause);
                            mp.start();
                            flag=1;
                            mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                        }
                        if(b)
                        {
                            if(flag==1 && genmflag==0)
                            {
                                imageView.startAnimation(fade);
                                mp=MediaPlayer.create(MainActivity.this,R.raw.genmrestart);
                                mp.start();
                                flag=0;
                                mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                            }
                            if(flag==1 && genmflag==1)
                            {
                                imageView.startAnimation(fade);
                                mp=MediaPlayer.create(MainActivity.this,R.raw.genmrestartalt);
                                mp.start();
                                flag=0;
                                genmflag=0;
                                mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                            }

                        }
                        if (b1) {
                            if(fumetsuflag==0)
                            {
                                fumetsuflag=1;
                                imageView.setImageResource(R.drawable.genmfumetsu);
                                flag=0;
                                genmflag=0;
                            }
                        }
                        if (b2)
                        {
                            if(fumetsuflag==1)
                            {
                                fumetsuflag=0;
                                imageView.setImageResource(R.drawable.genmmusou);
                            }
                        }

                    }
                    if(currentImageIndex==6)
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
                        if (b1)
                        {
                            hazard_flag=1;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.hazardon);
                            mp.start();
                        }
                        if (b2)
                        {
                            hazard_flag=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.hazardoff);
                            mp.start();
                        }
                    }
                    if(currentImageIndex==8)
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
                        if(diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE && flag==0)
                        {
                            ohmaflag=1;
                        }
                        if(b)
                        {
                            ohmaflag=0;
                        }

                    }
                    boolean b3 = diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    if(currentImageIndex==9)
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
                        if(b)
                        {
                            zt_index++;
                            if(zt_index>=zt_sounds.length)
                            {
                                zt_index=0;
                            }
                            mp=MediaPlayer.create(MainActivity.this,zt_sound.get(zt_index));
                            mp.start();
                        }
                        if(b3)
                        {
                            zt_index--;
                            if(zt_index<0)
                            {
                                zt_index=zt_sounds.length-1;
                            }
                            mp=MediaPlayer.create(MainActivity.this,zt_sound.get(zt_index));
                            mp.start();
                        }
                        if(b2)
                        {
                            ztweap_index++;
                            if(ztweap_index>=ztweap_sounds.length)
                            {
                                ztweap_index=0;
                            }
                            mp=MediaPlayer.create(MainActivity.this,ztweap_sound.get(ztweap_index));
                            mp.start();

                        }
                        if(b1)
                        {
                            ztweap_index--;
                            if(ztweap_index<0)
                            {
                                ztweap_index=ztweap_sounds.length-1;
                            }
                            mp=MediaPlayer.create(MainActivity.this,ztweap_sound.get(ztweap_index));
                            mp.start();

                        }
                    }
                    if(currentImageIndex==10)
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
                        if (b1) {
                            if(sabermode==0)
                            {
                                sabermode=1;
                                imageView.setImageResource(R.drawable.saberub);
                                bahamut=0;
                            }
                            else if(sabermode==2)
                            {
                                sabermode=0;
                                imageView.setImageResource(R.drawable.saber);
                                wonder1=0;
                                wonder2=0;
                            }
                        }
                        if (b2)
                        {
                            if(sabermode==1)
                            {
                                sabermode=0;
                                imageView.setImageResource(R.drawable.saber);
                                wonder1=0;
                            }
                            else if(sabermode==0)
                            {
                                sabermode=2;
                                imageView.setImageResource(R.drawable.sabershs);
                                superherosenki1=0;
                                superherosenki2=0;
                            }
                        }
                    }
                    if(currentImageIndex==11)
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
                        if(b)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.finisher_geats9);
                            mp.start();
                            geatsflag=2;
                        }
                        if(b3)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.finisher_geats9);
                            mp.start();
                            geatsflag=1;

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
            if(mp1!=null)
            {
                mp1.release();
            }
            end = MediaPlayer.create(this,R.raw.transition);
            end.start();
            end.setOnCompletionListener(mp -> {
                end.release();
                end=null;
            });
            Intent i = new Intent(MainActivity.this,Menu.class);
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
        if(mp1!=null)
        {
            mp1.release();
            mp1=null;
        }
        imageView.clearAnimation();
        imageView.setClickable(true);
        super.onPause();
    }
}