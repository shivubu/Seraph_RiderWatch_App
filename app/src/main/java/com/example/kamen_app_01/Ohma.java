package com.example.kamen_app_01;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Ohma extends AppCompatActivity {
    VideoView belt_text;
    MediaPlayer mp,end;
    ImageView ohmabelt, ridewatch,backgroundimage;
    private Drawable[] ridewatches;
    private int currentImageIndex = 0;
    private AudioManager audiomanager;
    int originalvolume,maxvolume,targetvolume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ohma);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ridewatch =findViewById(R.id.imageView14);
        ridewatch.setFocusable(true);
        ridewatch.requestFocus();
        backgroundimage=findViewById(R.id.imageView15);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        backgroundimage.setVisibility(View.INVISIBLE);
        ridewatches = new Drawable[]{
                AppCompatResources.getDrawable(this,R.drawable.omaz),
                AppCompatResources.getDrawable(this,R.drawable.ohma_kuuga),
                AppCompatResources.getDrawable(this,R.drawable.ohma_agito),
                AppCompatResources.getDrawable(this,R.drawable.ohma_ryuki),
                AppCompatResources.getDrawable(this,R.drawable.ohma_faiz),
                AppCompatResources.getDrawable(this,R.drawable.ohma_blade),
                AppCompatResources.getDrawable(this,R.drawable.ohma_hibiki),
                AppCompatResources.getDrawable(this,R.drawable.ohma_kabuto),
                AppCompatResources.getDrawable(this,R.drawable.ohma_deno),
                AppCompatResources.getDrawable(this,R.drawable.ohma_kiva),
                AppCompatResources.getDrawable(this,R.drawable.ohma_decade),
                AppCompatResources.getDrawable(this,R.drawable.ohma_double),
                AppCompatResources.getDrawable(this,R.drawable.ohma_ooo),
                AppCompatResources.getDrawable(this,R.drawable.ohma_fourze),
                AppCompatResources.getDrawable(this,R.drawable.ohma_wizard),
                AppCompatResources.getDrawable(this,R.drawable.ohma_gaim),
                AppCompatResources.getDrawable(this,R.drawable.ohma_drive),
                AppCompatResources.getDrawable(this,R.drawable.ohma_ghost),
                AppCompatResources.getDrawable(this,R.drawable.ohma_exaid),
                AppCompatResources.getDrawable(this,R.drawable.ohma_build),
                AppCompatResources.getDrawable(this,R.drawable.ohma_zio),


        };
        int[] vids={R.raw.ohma_attack_gif,R.raw.kuuga_gif,R.raw.agito_gif,R.raw.ryuki_gif,R.raw.faiz_gif,R.raw.blade_gif,R.raw.hibiki_gif,R.raw.kabuto_gif,R.raw.deno_gif,R.raw.kiva_gif,R.raw.decade_gif,R.raw.double_gif,R.raw.ooo_gif,R.raw.fourze_gif,R.raw.wizard_gif,R.raw.gaim_gif,R.raw.drive_gif,R.raw.ghost_gif,R.raw.exaid_gif,R.raw.build_gif,R.raw.zio_gif};
        ArrayList<Integer> video=new ArrayList<>();
        for (int vid : vids) {
            video.add(vid);
        }
        audiomanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(audiomanager!=null)
        {
            originalvolume=audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);
            maxvolume=audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            if(originalvolume==maxvolume)
            {
                targetvolume=maxvolume;
            }
            else
            {
                targetvolume=(int)(originalvolume+(0.40*maxvolume));
            }
        }
        else
        {
            Toast.makeText(this, "No Audio Manager", Toast.LENGTH_SHORT).show();
        }
        belt_text=findViewById(R.id.videoView2);
        belt_text.setVisibility(View.INVISIBLE);
        ohmabelt=findViewById(R.id.imageView13);
        ridewatch.setVisibility(View.INVISIBLE);
        ridewatch.setOnGenericMotionListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_SCROLL &&
                    motionEvent.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)){
                if(mp!=null)
                {
                    mp.release();
                    mp=null;
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
                    mp=MediaPlayer.create(Ohma.this,R.raw.transition2);
                    mp.start();
                    currentImageIndex++;
                } else if (delta < 0) {
                    // Rotate counter-clockwise
                    if(mp!=null)
                    {
                        mp.release();
                        mp=null;
                    }
                    mp=MediaPlayer.create(Ohma.this,R.raw.transition2);
                    mp.start();
                    currentImageIndex--;
                }
                // Wrap around the image array
                if (currentImageIndex < 0) {
                    currentImageIndex = ridewatches.length - 1;
                } else if (currentImageIndex >= ridewatches.length) {
                    currentImageIndex = 0;
                }
                // Update the background image
                if (ridewatches.length > 0) {
                    ridewatch.setImageDrawable(ridewatches[currentImageIndex]);
                }
                return true;
            }
            return false;
        });
        ohmabelt.setOnClickListener(v -> {
            audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,targetvolume,0);
            backgroundimage.setVisibility(View.VISIBLE);
            backgroundimage.setColorFilter(filter);
            ohmabelt.setClickable(false);
            belt_text.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.ohma_gif);
            belt_text.start();
            belt_text.setVisibility(View.VISIBLE);
            belt_text.setOnCompletionListener(mp -> {
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,originalvolume,0);
                ohmabelt.setVisibility(View.INVISIBLE);
                belt_text.setVisibility(View.INVISIBLE);
                backgroundimage.setVisibility(View.INVISIBLE);
                ridewatch.setVisibility(View.VISIBLE);
                ridewatch.setClickable(true);
                ridewatch.setFocusable(true);
                ridewatch.requestFocus();

            });

        });
        ridewatch.setOnClickListener(v -> {
            ohmabelt.setClickable(false);
            audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,targetvolume,0);
            belt_text.setVideoPath("android.resource://"+getPackageName()+"/"+video.get(currentImageIndex));
            belt_text.start();
            backgroundimage.setImageDrawable(ridewatches[currentImageIndex]);
            backgroundimage.setVisibility(View.VISIBLE);
            backgroundimage.setColorFilter(filter);
            ridewatch.setVisibility(View.INVISIBLE);
            ohmabelt.setVisibility(View.VISIBLE);
            belt_text.setVisibility(View.VISIBLE);
            belt_text.setOnCompletionListener(mp -> {
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,originalvolume,0);
                backgroundimage.setVisibility(View.INVISIBLE);
                backgroundimage.setColorFilter(null);
                ridewatch.setVisibility(View.VISIBLE);
                ridewatch.setFocusable(true);
                ridewatch.requestFocus();
                ohmabelt.setVisibility(View.INVISIBLE);
                belt_text.setVisibility(View.INVISIBLE);
            });

        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,originalvolume,0);
            end = MediaPlayer.create(this,R.raw.transition);
            end.start();
            end.setOnCompletionListener(mp -> {
                end.release();
                end=null;
            });
            if(belt_text.isPlaying())
            {
                belt_text.stopPlayback();
            }
            Intent i = new Intent(Ohma.this,Menu.class);
            startActivity(i);
            finish();

        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onPause() {
        if(belt_text.isPlaying())
        {
            belt_text.stopPlayback();

        }
        ohmabelt.setVisibility(View.INVISIBLE);
        belt_text.setVisibility(View.INVISIBLE);
        backgroundimage.clearColorFilter();
        backgroundimage.setVisibility(View.INVISIBLE);
        ridewatch.setVisibility(View.VISIBLE);
        ridewatch.setImageDrawable(ridewatches[currentImageIndex]);
        ridewatch.setFocusable(true);
        ridewatch.requestFocus();
        ridewatch.setClickable(true);
        audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,originalvolume,0);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(belt_text.isPlaying())
        {
            belt_text.stopPlayback();
        }
        audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,originalvolume,0);
        super.onDestroy();
    }
}