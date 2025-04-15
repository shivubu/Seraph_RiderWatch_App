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
    MediaPlayer mp,end;
    private Drawable[] backgroundImages;
    private int currentImageIndex = 0,flag=0;
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
        backgroundImages= new Drawable[]{ AppCompatResources.getDrawable(this,R.drawable.seraph),
                AppCompatResources.getDrawable(this,R.drawable.faiznext),
                AppCompatResources.getDrawable(this,R.drawable.decadecomplete21),
                AppCompatResources.getDrawable(this,R.drawable.oootajadoreternity),
                AppCompatResources.getDrawable(this,R.drawable.exaidnovel),
                AppCompatResources.getDrawable(this,R.drawable.genmmusou),
                AppCompatResources.getDrawable(this,R.drawable.crossbuild),
                AppCompatResources.getDrawable(this,R.drawable.evoltblackhole),
                AppCompatResources.getDrawable(this,R.drawable.omaz),
                AppCompatResources.getDrawable(this,R.drawable.geatsdea)};
        int[] sounds = {R.raw.seraph0,R.raw.faiznext,R.raw.decadecomplete21,R.raw.oootajadoreternity,R.raw.exaidnovel,R.raw.genmmusou,R.raw.crossbuild,R.raw.evolblackhole,R.raw.omazio,R.raw.geatsdea};
        int[] henshinsounds={R.raw.henshinseraph,R.raw.henshinfaiznext,R.raw.henshindecadecomplete21,R.raw.henshinoootajadoreternity,R.raw.henshinexaidnovel,R.raw.henshingenmmusou,R.raw.henshincrossbuild,R.raw.henshinevolblackhole,R.raw.henshinzioohma,R.raw.henshingeatsdea2};
        int[] longpresssounds={R.raw.lpseraph,R.raw.lpfaiznext,R.raw.lpdecadecomplete21,R.raw.lpoootajadoreternity,R.raw.lpexaidnovel,R.raw.lpgenmmusou,R.raw.lpcrossbuild,R.raw.lpevolblackhole,R.raw.lpohma,R.raw.lpgeatsdea};
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
                float delta = -motionEvent.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledHorizontalScrollFactor(ViewConfiguration.get(getApplicationContext()), getApplicationContext());
                if (delta > 0) {
                    // Rotate clockwise
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    mp=MediaPlayer.create(MainActivity.this,R.raw.transition2);
                    mp.start();
                    currentImageIndex++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
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
                    imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.customfade));
                    if(currentImageIndex==5 && flag==1 )
                    {
                        mp=MediaPlayer.create(MainActivity.this,R.raw.lpgenmmusoualt);
                        mp.start();
                        mp.setOnCompletionListener(mp -> {
                            imageView.clearAnimation();
                            flag=2;
                        });
                    }
                    else
                    {
                        mp = MediaPlayer.create(MainActivity.this, longpress.get(currentImageIndex));
                        mp.start();
                        mp.setOnCompletionListener(mp -> {
                            imageView.clearAnimation();
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
                    imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.customfade));
                    mp = MediaPlayer.create(MainActivity.this, henshinsound.get(currentImageIndex));
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
                    imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.customfade));
                    mp = MediaPlayer.create(MainActivity.this, sound.get(currentImageIndex));
                    mp.start();
                    mp.setOnCompletionListener(mp -> imageView.clearAnimation());
                    return super.onSingleTapConfirmed(e);
                }

                @Override
                public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    if(currentImageIndex==5)
                    {
                        if(flag==0)
                        {
                            imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.customfade));
                            mp=MediaPlayer.create(MainActivity.this,R.raw.genmpause);
                            mp.start();
                            mp.setOnCompletionListener(mp -> {
                                imageView.clearAnimation();
                                flag=1;
                            });
                        }
                        if(flag==1)
                        {
                            imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.customfade));
                            mp=MediaPlayer.create(MainActivity.this,R.raw.genmrestart);
                            mp.start();
                            mp.setOnCompletionListener(mp -> {
                                imageView.clearAnimation();
                                flag=0;
                            });
                        }
                        if(flag==2)
                        {
                            imageView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.customfade));
                            mp=MediaPlayer.create(MainActivity.this,R.raw.genmrestartalt);
                            mp.start();
                            mp.setOnCompletionListener(mp -> {
                                imageView.clearAnimation();
                                flag=0;
                            });
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
            imageView.clearAnimation();
            imageView.setClickable(true);
        }

        super.onPause();
    }
}