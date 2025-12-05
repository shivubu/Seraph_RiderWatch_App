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
    private int currentImageIndex = 0,flag=0,zt_index,ztweap_index,hazard_flag=0,genmflag=0,fumetsuflag=0,bahamut=0,sabermode=0,flag1=0,ohmaflag=0,superherosenki1=0,superherosenki2=0,wonder1=0,wonder2=0,geatsflag=0,fumetsu=0,dea,rampage_index,phblade_index,exc_index,drivemode,sru,finalForm;
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
                AppCompatResources.getDrawable(this,R.drawable.kuugasru),
                AppCompatResources.getDrawable(this,R.drawable.faiznext),
                AppCompatResources.getDrawable(this,R.drawable.decadecomplete21),
                AppCompatResources.getDrawable(this,R.drawable.oootajadoreternity),
                AppCompatResources.getDrawable(this,R.drawable.drivespecial),
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
        int[] sounds = {R.raw.seraph0,R.raw.kuugasru,R.raw.faiznext,R.raw.decadecomplete21,R.raw.oootajadoreternity,R.raw.drivespecial,R.raw.exaidnovel,R.raw.genmmusou,R.raw.crossbuild,R.raw.evolblackhole,R.raw.omazio,R.raw.zerothree,R.raw.saberwa,R.raw.geatsdea,R.raw.gotchardultima};
        int[] henshinsounds={R.raw.henshin_seraph_prime,R.raw.henshinkuugasru,R.raw.henshinfaiznext,R.raw.henshindecadecomplete21,R.raw.henshinoootajadoreternity,R.raw.henshindrivespecial,R.raw.henshinexaidnovel,R.raw.henshingenmmusou,R.raw.henshincrossbuild,R.raw.henshinevolblackhole,R.raw.henshinzioohma,R.raw.henshinzerothree,R.raw.henshinsaberwa,R.raw.henshingeatsdea_0,R.raw.henshingotchardultima};
        int[] longpresssounds={R.raw.finisher_seraph,R.raw.finisher_kuugasru_1,R.raw.exceedcharge,R.raw.finisher_decadecomplete,R.raw.lpoootajadoreternity,R.raw.finisher_drivespecial,R.raw.lpexaidnovel,R.raw.lpgenmmusou,R.raw.lpcrossbuild,R.raw.finisher_evolblackhole,R.raw.finisher_zioohma,R.raw.lpzerothree,R.raw.finisher_saberwa1,R.raw.finisher_geatsdea,R.raw.lpgotchardultima};
        int[] zt_sounds={R.raw.zt_create,R.raw.zt_singularity,R.raw.zt_ability,R.raw.zt_there_ark_ability,R.raw.zt_outsiders_ability};
        int[] ztweap_sounds={R.raw.attache_calibur,R.raw.attache_shotgun,R.raw.attache_arrow,R.raw.shotriser,R.raw.slashriser,R.raw.thousand_jacker,R.raw.authorise_blaster,R.raw.hopper_blade,R.raw.zerothree_lifeon};
        int[] phblade_sounds={R.raw.progrisingslash,R.raw.dockingrise,R.raw.gigantslash,R.raw.ultimaterise};
        int[] rampage_sounds={R.raw.rampagegattling,R.raw.powerrampage,R.raw.speedrampage,R.raw.elementrampage,R.raw.allrampage};
        int[] faiz_weaps={R.raw.faiz_shot,R.raw.faiz_pointer,R.raw.faiz_edge,R.raw.faiz_blaster,R.raw.faiz_edgeandblaster};
        int[] exceed_sounds={R.raw.ec_faizshot,R.raw.ec_pointer,R.raw.ec_edge,R.raw.ec_blaster,R.raw.ec_edgeandblaster};
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
        ArrayList<Integer> phblade_sound = new ArrayList<>();
        for (int j : phblade_sounds) {
            phblade_sound.add(j);
        }
        ArrayList<Integer> rampage_sound = new ArrayList<>();
        for (int j : rampage_sounds) {
            rampage_sound.add(j);
        }
        ArrayList<Integer> faiz_weap = new ArrayList<>();
        for (int j : faiz_weaps) {
            faiz_weap.add(j);
        }
        ArrayList<Integer> exceed_sound = new ArrayList<>();
        for (int j : exceed_sounds) {
            exceed_sound.add(j);
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
                    imageView.setImageDrawable(backgroundImages[currentImageIndex]);
                    switch(currentImageIndex)
                    {
                        case 0:finalForm=0;
                        case 1:sru=0;break;
                        case 2:exc_index=-1;break;
                        case 5:drivemode=0;break;
                        case 7:fumetsu=0;flag=0;genmflag=0;fumetsuflag=0;break;
                        case 10: ohmaflag=0;break;
                        case 11: zt_index=-1;ztweap_index=-1;phblade_index=-1;rampage_index=-1;break;
                        case 12: sabermode=0;bahamut=0;superherosenki1=superherosenki2=0;wonder1=wonder2=0;break;
                        case 13: geatsflag=0;dea=0;break;
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
                        if(currentImageIndex==0 && finalForm==1)
                        {
                            mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_seraphsupreme);
                        }
                        else if(currentImageIndex==1)
                        {
                            switch (sru)
                            {
                                case 0:mp1=MediaPlayer.create(MainActivity.this,longpress.get(currentImageIndex));sru=1;break;
                                case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_kuugasru_2);sru=0;break;
                            }
                        }
                        else if(currentImageIndex==2 && exc_index!=-1)
                        {
                            mp1=MediaPlayer.create(MainActivity.this,exceed_sound.get(exc_index));
                        }
                        else if(currentImageIndex==5 && drivemode==1)
                        {
                            mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_drivenext);
                        }
                        else if(currentImageIndex==7 && flag==1 || currentImageIndex==8 && hazard_flag==1 || currentImageIndex==10 && ohmaflag==1 || currentImageIndex==11 && ztweap_index==8)
                        {
                            if(currentImageIndex==7)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmmusoualt);
                                genmflag=1;
                            }
                            if(currentImageIndex==8)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.lpcrossbuildhazard);
                            }
                            if(currentImageIndex==10)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_ohmazio);
                            }
                            if(currentImageIndex==11)
                            {
                                mp1 = MediaPlayer.create(MainActivity.this, R.raw.lpzerothreealt);
                            }
                        }
                        else if(currentImageIndex==7 && fumetsuflag==1)
                        {
                            switch (fumetsu)
                            {
                                case 0:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmhyperfumetsu1);break;
                                case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmhyperfumetsu2);break;
                                case 2:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmhyperfumetsu3);break;
                            }
                        }
                        else if(currentImageIndex==12)
                        {
                            if(sabermode==0)
                            {
                                switch(wonder2)
                                {
                                    case 0 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_saberwa1);break;
                                    case 1 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_saberwa2);break;
                                    case 2 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_saberwa3);break;
                                }
                            }
                            else if(sabermode==1)
                            {
                                switch(bahamut)
                                {
                                    case 0 : mp1=MediaPlayer.create(MainActivity.this,R.raw.lpsaberub1);break;
                                    case 1 : mp1=MediaPlayer.create(MainActivity.this,R.raw.lpsaberub2);break;
                                }
                            }
                            else if(sabermode==2)
                            {
                                switch(superherosenki2)
                                {
                                    case 0 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_sabershs1);break;
                                    case 1 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_sabershs2);break;
                                }
                            }
                        }
                        else if(currentImageIndex==13 && geatsflag!=0)
                        {
                            switch(geatsflag)
                            {
                                case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_1);break;
                                case 2:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_2);break;
                                case 3:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_3);break;
                                case 4:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_4);break;
                            }
                        }
                        else {
                            mp1 = MediaPlayer.create(MainActivity.this, longpress.get(currentImageIndex));
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
                    if(currentImageIndex==0 && finalForm==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshin_seraphsupreme_prime);
                    }
                    else if(currentImageIndex==5 && drivemode==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshindrivenext);
                    }
                    else if(currentImageIndex==7 && fumetsuflag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshingenmhyperfumetsu);
                    }
                    else if(currentImageIndex==8 && hazard_flag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshincrossbuildhazard);

                    }
                    else if(currentImageIndex==10 && ohmaflag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.henshinohmazio);
                    }
                    else if(currentImageIndex==12 && sabermode!=0)
                    {
                        switch(sabermode)
                        {
                            case 1:mp=MediaPlayer.create(MainActivity.this,R.raw.henshinsaberub);break;
                            case 2:mp=MediaPlayer.create(MainActivity.this,R.raw.henshinsabershs);break;
                        }
                    }
                    else if(currentImageIndex==13)
                    {
                        switch(dea)
                        {
                            case 0:mp=MediaPlayer.create(MainActivity.this,R.raw.henshingeatsdea_0);dea=1;break;
                            case 1:mp=MediaPlayer.create(MainActivity.this,R.raw.henshingeatsdea_1);dea=0;break;
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
                    if(currentImageIndex==0 && finalForm==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.seraphsupremeridewatch);
                    }
                    else if(currentImageIndex==5 && drivemode==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.drivenext);
                    }
                    else if(currentImageIndex==7 && fumetsuflag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.genmhyperfumetsu);
                    }
                    else if(flag1==1)
                    {
                        if(currentImageIndex==3)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpdecadecomplete21);
                        }
                        if(currentImageIndex==9)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpevolblackhole);
                        }
                        if(currentImageIndex==10)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpohma);
                        }
                    }
                    else if(currentImageIndex==12)
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
                        if(currentImageIndex==3 || currentImageIndex==9 || currentImageIndex==10 )
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
                    boolean downSwipe = diffY > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean upSwipe = diffY < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffX) < SWIPE_THRESHOLD_DISTANCE;
                    boolean rightSwipe = diffX > SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    boolean leftSwipe = diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    if(currentImageIndex==0)
                    {
                        imageView.clearAnimation();
                        if (mp != null) {
                            mp.release();
                            mp = null;
                        }
                        if (mp1 != null) {
                            mp1.release();
                            mp1 = null;
                        }
                        if(downSwipe && finalForm==0)
                        {
                            imageView.setImageResource(R.drawable.finalform);
                            finalForm=1;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                        }
                        if(upSwipe && finalForm==1)
                        {
                            imageView.setImageResource(R.drawable.seraph);
                            finalForm=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                        }

                    }
                    else if(currentImageIndex==1)
                    {
                        imageView.clearAnimation();
                        if (mp != null) {
                            mp.release();
                            mp = null;
                        }
                        if (mp1 != null) {
                            mp1.release();
                            mp1 = null;
                        }
                        if(leftSwipe||rightSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.kuugasru_pyrokenetics);
                            mp.start();
                        }
                    }
                    else if (currentImageIndex == 2) {
                        imageView.clearAnimation();
                        if (mp != null) {
                            mp.release();
                            mp = null;
                        }
                        if (mp1 != null) {
                            mp1.release();
                            mp1 = null;
                        }
                        if (rightSwipe) {
                            exc_index++;
                            if (exc_index >= faiz_weaps.length) {
                                exc_index = 0;
                            }
                            mp = MediaPlayer.create(MainActivity.this, faiz_weap.get(exc_index));
                            mp.start();
                        }
                        if (leftSwipe) {
                            exc_index--;
                            if (exc_index < 0) {
                                exc_index = faiz_weaps.length - 1;
                            }
                            mp = MediaPlayer.create(MainActivity.this, faiz_weap.get(exc_index));
                            mp.start();
                        }
                        if(upSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpfaiznext);
                            mp.start();
                            imageView.startAnimation(fade);
                            mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                        }
                        if(downSwipe)
                        {
                            mp= MediaPlayer.create(MainActivity.this,R.raw.predictiveai);
                            mp.start();
                            exc_index=-1;
                        }
                    }
                    else if (currentImageIndex == 5)
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
                        if(downSwipe && drivemode==0)
                        {
                            drivemode=1;
                            imageView.setImageResource(R.drawable.drivenext);
                        }
                        if(upSwipe && drivemode==1)
                        {
                            drivemode=0;
                            imageView.setImageDrawable(backgroundImages[currentImageIndex]);
                        }
                    }
                    else if(currentImageIndex==7)
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
                        if(fumetsuflag==0)
                        {
                            if(leftSwipe && flag==0)
                            {
                                imageView.startAnimation(fade);
                                mp=MediaPlayer.create(MainActivity.this,R.raw.genmpause);
                                mp.start();
                                flag=1;
                                mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                            }
                            if(rightSwipe)
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
                        }
                        else {
                            if(leftSwipe)
                            {
                                mp=MediaPlayer.create(MainActivity.this,R.raw.fumetsu_1);
                                mp.start();
                                switch(fumetsu)
                                {
                                    case 0:fumetsu=1;break;
                                    case 2:fumetsu=0;break;
                                }
                            }
                            if(rightSwipe)
                            {
                                mp=MediaPlayer.create(MainActivity.this,R.raw.fumetsu_2);
                                mp.start();
                                switch(fumetsu)
                                {
                                    case 0:fumetsu=2;break;
                                    case 1:fumetsu=0;break;
                                }
                            }
                        }
                        if (downSwipe && fumetsuflag==0)
                        {
                            fumetsuflag=1;
                            fumetsu=0;
                            imageView.setImageResource(R.drawable.genmfumetsu);
                            flag=0;
                            genmflag=0;
                        }
                        if (upSwipe && fumetsuflag==1)
                        {
                            fumetsuflag=0;
                            imageView.setImageResource(R.drawable.genmmusou);

                        }

                    }
                    else if(currentImageIndex==8)
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
                            hazard_flag=1;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.hazardon);
                            mp.start();
                        }
                        if (upSwipe)
                        {
                            hazard_flag=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.hazardoff);
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
                        if(leftSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.ohma);
                            mp.start();
                            mp.setOnCompletionListener(MediaPlayer::release);
                            ohmaflag=1;
                        }
                        if(rightSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.ohma);
                            mp.start();
                            mp.setOnCompletionListener(MediaPlayer::release);
                            ohmaflag=0;
                        }
                        if(upSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.king_girigirislash);
                            mp.start();
                            mp.setOnCompletionListener(MediaPlayer::release);
                        }

                    }
                    else if(currentImageIndex==11)
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
                        if(upSwipe)
                        {
                            ztweap_index++;
                            if(ztweap_index>=ztweap_sounds.length)
                            {
                                ztweap_index=0;
                            }
                            mp=MediaPlayer.create(MainActivity.this,ztweap_sound.get(ztweap_index));
                            mp.start();

                        }
                        if(downSwipe)
                        {
                            ztweap_index--;
                            if(ztweap_index<0)
                            {
                                ztweap_index=ztweap_sounds.length-1;
                            }
                            mp=MediaPlayer.create(MainActivity.this,ztweap_sound.get(ztweap_index));
                            mp.start();

                        }
                        if(ztweap_index==3)
                        {
                            if(rightSwipe)
                            {
                                rampage_index++;
                                if(rampage_index>=rampage_sounds.length)
                                {
                                    rampage_index=0;
                                }
                                mp=MediaPlayer.create(MainActivity.this,rampage_sound.get(rampage_index));
                                mp.start();
                            }
                            if(leftSwipe) {
                                rampage_index--;
                                if (rampage_index < 0) {
                                    rampage_index = rampage_sounds.length - 1;
                                }
                                mp = MediaPlayer.create(MainActivity.this, rampage_sound.get(rampage_index));
                                mp.start();
                            }
                        }
                        else if(ztweap_index==4)
                        {
                            if(leftSwipe||rightSwipe)
                            {
                                mp=MediaPlayer.create(MainActivity.this,R.raw.finisher_slashriser);
                                mp.start();
                            }
                        }
                        else if(ztweap_index==5)
                        {
                            if(leftSwipe||rightSwipe)
                            {
                                mp=MediaPlayer.create(MainActivity.this,R.raw.jackingbreak);
                                mp.start();
                            }
                        }
                        else if(ztweap_index==7)
                        {
                            if(rightSwipe)
                            {
                                phblade_index++;
                                if(phblade_index>=phblade_sounds.length)
                                {
                                    phblade_index=0;
                                }
                                mp=MediaPlayer.create(MainActivity.this,phblade_sound.get(phblade_index));
                                mp.start();
                            }
                            if(leftSwipe)
                            {
                                phblade_index--;
                                if(phblade_index<0)
                                {
                                    phblade_index=phblade_sounds.length-1;
                                }
                                mp=MediaPlayer.create(MainActivity.this,phblade_sound.get(phblade_index));
                                mp.start();
                            }
                        }
                        else
                        {
                            if(rightSwipe)
                            {
                                zt_index++;
                                if(zt_index>=zt_sounds.length)
                                {
                                    zt_index=0;
                                }
                                mp=MediaPlayer.create(MainActivity.this,zt_sound.get(zt_index));
                                mp.start();
                            }
                            if(leftSwipe)
                            {
                                zt_index--;
                                if(zt_index<0)
                                {
                                    zt_index=zt_sounds.length-1;
                                }
                                mp=MediaPlayer.create(MainActivity.this,zt_sound.get(zt_index));
                                mp.start();
                            }
                        }

                    }
                    else if(currentImageIndex==12)
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
                        if (downSwipe) {
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
                        if (upSwipe)
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
                        if(leftSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            if(sabermode==0)
                            {
                                if(wonder2==0)
                                {
                                    wonder2=1;
                                }
                                else if(wonder2==2)
                                {
                                    wonder2=0;
                                }
                            }
                            else if(sabermode==1)
                            {
                                if(bahamut==0)
                                {
                                    bahamut=1;
                                }
                            }
                            else if(sabermode==2)
                            {
                                if(superherosenki2==0)
                                {
                                    superherosenki2=1;
                                }
                            }

                        }
                        if(rightSwipe) {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            if (sabermode == 0) {
                                if (wonder2 == 0) {
                                    wonder2 = 2;
                                } else if (wonder2 == 1) {
                                    wonder2 = 0;
                                }
                            } else if (sabermode == 1) {
                                if (bahamut == 1) {
                                    bahamut = 0;
                                }
                            } else if (sabermode == 2) {
                                if (superherosenki2 == 1) {
                                    superherosenki2 = 0;
                                }
                            }
                        }
                    }
                    else if(currentImageIndex==13)
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
                            mp=MediaPlayer.create(MainActivity.this,R.raw.finisher_geats9);
                            mp.start();
                            switch(geatsflag)
                            {
                                case 0:geatsflag=2;break;
                                case 1:geatsflag=0;break;
                            }
                        }
                        if(leftSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.finisher_geats9);
                            mp.start();
                            switch(geatsflag)
                            {
                                case 0:geatsflag=1;break;
                                case 2:geatsflag=0;break;
                            }
                        }
                        if(upSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.finisher_geats9);
                            mp.start();
                            switch(geatsflag)
                            {
                                case 0:geatsflag=3;break;
                                case 4:geatsflag=0;break;
                            }
                        }
                        if(downSwipe) {
                            mp = MediaPlayer.create(MainActivity.this, R.raw.finisher_geats9);
                            mp.start();
                            switch (geatsflag)
                            {
                                case 0:geatsflag=4;break;
                                case 3:geatsflag=0;break;
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