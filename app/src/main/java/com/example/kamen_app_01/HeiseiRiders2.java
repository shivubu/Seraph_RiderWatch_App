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
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HeiseiRiders2 extends AppCompatActivity {
    int i=0;
    MediaPlayer mp,end;
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
        int[] rw = {R.drawable.w, R.drawable.ooo,R.drawable.fourze,R.drawable.wizard,R.drawable.gaim,R.drawable.drive,R.drawable.ghost,R.drawable.exaid,R.drawable.build,R.drawable.grandzio};
        int[] sounds = {R.raw.doublecjex, R.raw.oooputo,R.raw.fourzecosmic,R.raw.wizardinfinity,R.raw.gaimkiwami,R.raw.drivetrideron,R.raw.ghostmugen,R.raw.exaidmuteki,R.raw.buildgenius,R.raw.grandzio};
        int[] henshinsounds={R.raw.henshindoublecjx,R.raw.henshinoooputo,R.raw.henshinfourzecosmic,R.raw.henshinwizardinfinity,R.raw.henshingaimkiwami,R.raw.henshindrivetrideron,R.raw.henshinghostmugen,R.raw.henshinmutekiexaid,R.raw.henshinbuildgenius,R.raw.henshingrandzio};
        int[] longpresssounds={R.raw.lpdouble,R.raw.lpooo,R.raw.lpfourze,R.raw.lpwizard,R.raw.lpgaim,R.raw.lpdrive,R.raw.lpghost,R.raw.lpexaid,R.raw.lpbuild,R.raw.lpzio};
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
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    i++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
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
                    mp=MediaPlayer.create(HeiseiRiders2.this,R.raw.transition2);
                    mp.start();
                    imageView.setImageResource(screen.get(i));
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
                    imageView.startAnimation(AnimationUtils.loadAnimation(HeiseiRiders2.this,R.anim.customfade));
                    mp = MediaPlayer.create(HeiseiRiders2.this, longpresssound.get(i));
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
                    imageView.startAnimation(AnimationUtils.loadAnimation(HeiseiRiders2.this,R.anim.customfade));
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
                    imageView.startAnimation(AnimationUtils.loadAnimation(HeiseiRiders2.this,R.anim.customfade));
                    mp = MediaPlayer.create(HeiseiRiders2.this, sound.get(i));
                    mp.start();
                    mp.setOnCompletionListener(mp -> imageView.clearAnimation());
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
        super.onPause();
    }

}