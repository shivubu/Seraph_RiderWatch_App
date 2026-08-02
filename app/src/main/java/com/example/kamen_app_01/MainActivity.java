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

public class MainActivity extends BaseKamenActivity{
    ImageView myLocalImage;
    private Drawable[] backgroundImages;
    private int currentImageIndex = 0,flag=0,zt_index,ztweap_index,hazard_flag=0,genmflag=0,fumetsuflag=0,bahamut=0,sabermode=0,flag1=0,ohmaflag=0,superherosenki1=0,superherosenki2=0,wonder1=0,wonder2=0,geatsflag=0,fumetsu=0,dea,rampage_index,phblade_index,exc_index,drivemode,sru,finalForm,gotchard,gotchardfinisher;
    int ksru=1,fn=2,dc=3,ote=4,dn=5,gm=7,cb=8,eb=9,oz=10,zt=11,swa=12,dg=13,gu=14;
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
                AppCompatResources.getDrawable(this,R.drawable.gotchard),
                AppCompatResources.getDrawable(this,R.drawable.gavvhexenheim),
        };
        int[] sounds = {R.raw.seraph0,R.raw.kuugasruform,R.raw.faiznext,R.raw.decadecomplete21,R.raw.oootajadoreternity,R.raw.drivespecial,R.raw.exaidnovel,R.raw.genmmusou,R.raw.crossbuild,R.raw.evolblackhole,R.raw.omazio,R.raw.zerothree,R.raw.saberwa,R.raw.geatsdea,R.raw.gotchardultima,R.raw.gavvhexenheim};
        int[] henshinsounds={R.raw.henshin_seraph_prime,R.raw.henshinkuugasru,R.raw.henshinfaiznext,R.raw.henshindecadecomplete21,R.raw.henshin_oootajadoreternity,R.raw.henshindrivespecial,R.raw.henshinexaidnovel,R.raw.henshingenmmusou,R.raw.henshincrossbuild,R.raw.henshinevolblackhole,R.raw.henshinzioohma,R.raw.henshinzerothree,R.raw.henshinsaberwa,R.raw.henshingeatsdea_0,R.raw.henshingotchardultima,R.raw.henshin_gavvhexenheim};
        int[] longpresssounds={R.raw.finisher_seraph,R.raw.finisher_kuugasru_1,R.raw.exceedcharge,R.raw.finisher_decadecomplete,R.raw.finisher_oootajadoreternity,R.raw.finisher_drivespecial,R.raw.lpexaidnovel,R.raw.lpgenmmusou,R.raw.lpcrossbuild,R.raw.finisher_evolblackhole,R.raw.finisher_zioohma,R.raw.lpzerothree,R.raw.finisher_saberwa1,R.raw.finisher_geatsdea,R.raw.lpgotchardultima,R.raw.finisher_gavvhexenheim};
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
        myLocalImage = findViewById(R.id.imageView);
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
                    if(loopPlayer!=null)
                    {
                        loopPlayer.release();
                        loopPlayer=null;
                    }
                    myLocalImage.startAnimation(fade);
                    if(currentImageIndex==0 && finalForm==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.finisher_seraphsupreme);
                        mp.start();
                        mp.setOnCompletionListener(mp2 -> myLocalImage.clearAnimation());
                    }
                    else
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.judgement_finishtime);
                        mp.start();
                        mp.setOnCompletionListener(mp -> {
                            if(currentImageIndex==ksru)
                            {
                                switch (sru)
                                {
                                    case 0:mp1=MediaPlayer.create(MainActivity.this,longpress.get(currentImageIndex));sru=1;break;
                                    case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_kuugasru_2);sru=0;break;
                                }
                            }
                            else if(currentImageIndex==fn && exc_index!=-1)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,exceed_sound.get(exc_index));
                            }
                            else if(currentImageIndex==dn && drivemode==1)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_drivenext);
                            }
                            else if(currentImageIndex==gm && flag==1 || currentImageIndex==cb && hazard_flag==1 || currentImageIndex==oz && ohmaflag==1 || currentImageIndex==zt && ztweap_index==8)
                            {
                                if(currentImageIndex==gm)
                                {
                                    mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmmusoualt);
                                    genmflag=1;
                                }
                                if(currentImageIndex==cb)
                                {
                                    mp1=MediaPlayer.create(MainActivity.this,R.raw.lpcrossbuildhazard);
                                }
                                if(currentImageIndex==oz)
                                {
                                    mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_ohmazio);
                                }
                                if(currentImageIndex==zt)
                                {
                                    mp1 = MediaPlayer.create(MainActivity.this, R.raw.lpzerothreealt);
                                }
                            }
                            else if(currentImageIndex==gm && fumetsuflag==1)
                            {
                                switch (fumetsu)
                                {
                                    case 0:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmhyperfumetsu1);break;
                                    case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmhyperfumetsu2);break;
                                    case 2:mp1=MediaPlayer.create(MainActivity.this,R.raw.lpgenmhyperfumetsu3);break;
                                }
                            }
                            else if(currentImageIndex== swa)
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
                            else if(currentImageIndex==dg && geatsflag!=0)
                            {
                                switch(geatsflag)
                                {
                                    case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_1);break;
                                    case 2:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_2);break;
                                    case 3:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_3);break;
                                    case 4:mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_geatsdea_4);break;
                                }
                            }
                            else if(currentImageIndex==gu && gotchard!=0) {
                                switch (gotchard) {
                                    case 1:
                                        switch (gotchardfinisher)
                                        {
                                            case 0 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_gotchardrainbow);gotchardfinisher=1;break;
                                            case 1 : mp1=MediaPlayer.create(MainActivity.this,R.raw.finisher_miraclegotchard);gotchardfinisher=0;break;
                                        }break;
                                    case 2:
                                        mp1 = MediaPlayer.create(MainActivity.this, R.raw.finisher_shiningdaybreak);
                                        break;
                                }
                            }
                            else {
                                mp1 = MediaPlayer.create(MainActivity.this, longpress.get(currentImageIndex));
                            }
                            mp1.start();
                            mp1.setOnCompletionListener(mp2 -> myLocalImage.clearAnimation());
                        });
                    }

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
                    if(currentImageIndex==0)
                    {
                        switch(finalForm) {
                            case 0:
                                mp = MediaPlayer.create(MainActivity.this, henshinsound.get(currentImageIndex));
                                break;
                            case 1:
                                mp = MediaPlayer.create(MainActivity.this, R.raw.henshin_seraphsupreme_prime);
                                break;
                        }
                        mp.start();
                        mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
                    }
                    else {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.finaljudgementformtime);
                        mp.start();
                        mp.setOnCompletionListener(mp -> {
                            if(currentImageIndex==0 && finalForm==1)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.henshin_seraphsupreme_prime);
                            }
                            else if(currentImageIndex==dn && drivemode==1)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.henshindrivenext);
                            }
                            else if(currentImageIndex==gm && fumetsuflag==1)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.henshingenmhyperfumetsu);
                            }
                            else if(currentImageIndex==cb && hazard_flag==1)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.henshincrossbuildhazard);

                            }
                            else if(currentImageIndex==oz && ohmaflag==1)
                            {
                                mp1=MediaPlayer.create(MainActivity.this,R.raw.henshinohmazio);
                            }
                            else if(currentImageIndex==swa && sabermode!=0)
                            {
                                switch(sabermode)
                                {
                                    case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.henshinsaberub);break;
                                    case 2:mp1=MediaPlayer.create(MainActivity.this,R.raw.henshinsabershs);break;
                                }
                            }
                            else if(currentImageIndex==dg)
                            {
                                switch(dea)
                                {
                                    case 0:mp1=MediaPlayer.create(MainActivity.this,R.raw.henshingeatsdea_0);dea=1;break;
                                    case 1:mp1=MediaPlayer.create(MainActivity.this,R.raw.henshingeatsdea_1);dea=0;break;
                                }
                            }
                            else if(currentImageIndex==gu && gotchard!=0) {
                                switch (gotchard) {
                                    case 1:
                                        mp1 = MediaPlayer.create(MainActivity.this, R.raw.henshin_miraclegotchard);
                                        break;
                                    case 2:
                                        mp1 = MediaPlayer.create(MainActivity.this, R.raw.henshin_shiningdaybreak);
                                        break;
                                }
                            }
                            else
                            {
                                mp1 = MediaPlayer.create(MainActivity.this, henshinsound.get(currentImageIndex));
                            }
                            mp1.start();
                            mp1.setOnCompletionListener(mp2 -> myLocalImage.clearAnimation());
                        });
                    }
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
                    if(currentImageIndex==0 && finalForm==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.seraphsupremeridewatch);
                    }
                    else if(currentImageIndex==dn && drivemode==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.drivenext);
                    }
                    else if(currentImageIndex==gm && fumetsuflag==1)
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.genmhyperfumetsu);
                    }
                    else if(flag1==1)
                    {
                        if(currentImageIndex==dc)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpdecadecomplete21);
                        }
                        if(currentImageIndex==eb)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpevolblackhole);
                        }
                        if(currentImageIndex==oz)
                        {
                            flag1=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.lpohma);
                        }
                    }
                    else if(currentImageIndex==swa)
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
                    else if(currentImageIndex==gu && gotchard!=0) {
                        switch (gotchard) {
                            case 1:
                                mp = MediaPlayer.create(MainActivity.this, R.raw.miraclegotchard);
                                break;
                            case 2:
                                mp = MediaPlayer.create(MainActivity.this, R.raw.gotchardshiningdaybreak);
                                break;
                        }
                    }
                    else
                    {
                        mp = MediaPlayer.create(MainActivity.this, sound.get(currentImageIndex));
                        if(currentImageIndex==dc || currentImageIndex==eb || currentImageIndex==oz )
                        {
                            flag1=1;
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
                    boolean leftSwipe = diffX < -SWIPE_THRESHOLD_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(diffY) < SWIPE_THRESHOLD_DISTANCE;
                    if(currentImageIndex==0)
                    {
                        myLocalImage.clearAnimation();
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
                            myLocalImage.setImageResource(R.drawable.finalform);
                            finalForm=1;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                        }
                        if(upSwipe && finalForm==1)
                        {
                            myLocalImage.setImageResource(R.drawable.seraph);
                            finalForm=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                        }
                        if(leftSwipe && loopPlayer==null)
                        {
                            loopPlayer=PerfectLoopMediaPlayer.create(MainActivity.this,R.raw.standby);
                            loopPlayer.start();
                        }
                        if(rightSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.time_effect);
                            mp.start();
                        }

                    }
                    else if(currentImageIndex==ksru)
                    {
                        myLocalImage.clearAnimation();
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
                    else if (currentImageIndex == fn) {
                        myLocalImage.clearAnimation();
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
                            myLocalImage.startAnimation(fade);
                            mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
                        }
                        if(downSwipe)
                        {
                            mp= MediaPlayer.create(MainActivity.this,R.raw.predictiveai);
                            mp.start();
                            exc_index=-1;
                        }
                    }
                    else if (currentImageIndex==dc) {
                        myLocalImage.clearAnimation();
                        if (mp != null) {
                            mp.release();
                            mp = null;
                        }
                        if (mp1 != null) {
                            mp1.release();
                            mp1 = null;
                        }
                        if (upSwipe || downSwipe) {
                            mp = MediaPlayer.create(MainActivity.this, R.raw.aurora_curtain);
                            mp.start();
                        }

                    } else if (currentImageIndex==ote)
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
                        if(downSwipe ||  upSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.ooo_te_eternityscan);
                            mp.start();
                        }

                    } else if (currentImageIndex == dn)
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
                        if(downSwipe && drivemode==0)
                        {
                            drivemode=1;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            myLocalImage.setImageResource(R.drawable.drivenext);

                        }
                        if(upSwipe && drivemode==1)
                        {
                            drivemode=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            myLocalImage.setImageDrawable(backgroundImages[currentImageIndex]);
                        }
                    }
                    else if(currentImageIndex==gm)
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
                        if(fumetsuflag==0)
                        {
                            if(leftSwipe && flag==0)
                            {
                                myLocalImage.startAnimation(fade);
                                mp=MediaPlayer.create(MainActivity.this,R.raw.genmpause);
                                mp.start();
                                flag=1;
                                mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
                            }
                            if(rightSwipe)
                            {
                                if(flag==1 && genmflag==0)
                                {
                                    myLocalImage.startAnimation(fade);
                                    mp=MediaPlayer.create(MainActivity.this,R.raw.genmrestart);
                                    mp.start();
                                    flag=0;
                                    mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
                                }
                                if(flag==1 && genmflag==1)
                                {
                                    myLocalImage.startAnimation(fade);
                                    mp=MediaPlayer.create(MainActivity.this,R.raw.genmrestartalt);
                                    mp.start();
                                    flag=0;
                                    genmflag=0;
                                    mp.setOnCompletionListener(mp -> myLocalImage.clearAnimation());
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
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            myLocalImage.setImageResource(R.drawable.genmfumetsu);
                            flag=0;
                            genmflag=0;
                        }
                        if (upSwipe && fumetsuflag==1)
                        {
                            fumetsuflag=0;
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            myLocalImage.setImageResource(R.drawable.genmmusou);

                        }

                    }
                    else if(currentImageIndex==cb)
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
                    if(currentImageIndex==oz)
                    {
                        myLocalImage.clearAnimation();
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
                    else if(currentImageIndex==zt)
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
                    else if(currentImageIndex==swa)
                    {
                        myLocalImage.clearAnimation();
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
                                mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                                mp.start();
                                myLocalImage.setImageResource(R.drawable.saberub);
                                bahamut=0;
                            }
                            else if(sabermode==2)
                            {
                                sabermode=0;
                                mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                                mp.start();
                                myLocalImage.setImageResource(R.drawable.saber);
                                wonder1=0;
                                wonder2=0;
                            }
                        }
                        if (upSwipe)
                        {
                            if(sabermode==1)
                            {
                                sabermode=0;
                                mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                                mp.start();
                                myLocalImage.setImageResource(R.drawable.saber);
                                wonder1=0;
                            }
                            else if(sabermode==0)
                            {
                                sabermode=2;
                                mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                                mp.start();
                                myLocalImage.setImageResource(R.drawable.sabershs);
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
                    else if(currentImageIndex==dg)
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
                    else if(currentImageIndex==gu)
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
                        if(downSwipe)
                        {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            switch(gotchard)
                            {
                                case 0: gotchard=1;myLocalImage.setImageResource(R.drawable.miracle_gotchard);break;
                                case 2: gotchard=0;myLocalImage.setImageResource(R.drawable.gotchard);break;
                            }
                        }
                        if(upSwipe) {
                            mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                            mp.start();
                            switch (gotchard) {
                                case 0:
                                    gotchard = 2;
                                    myLocalImage.setImageResource(R.drawable.gotchard_daybreak);
                                    break;
                                case 1:
                                    gotchard = 0;
                                    myLocalImage.setImageResource(R.drawable.gotchard);
                                    break;
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
                    myLocalImage.clearAnimation();
                }
                if(mp1!=null) {
                    mp1.release();
                    mp1 = null;
                    myLocalImage.clearAnimation();
                }
                if(loopPlayer!=null)
                {
                    loopPlayer.release();
                    loopPlayer=null;
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
                    myLocalImage.setImageDrawable(backgroundImages[currentImageIndex]);
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
                        case 14: gotchard=0;gotchardfinisher=0;break;
                    }
                }
                return true;
            }
            return false;
        });
    }

}