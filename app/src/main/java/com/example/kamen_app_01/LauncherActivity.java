package com.example.kamen_app_01;import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LauncherActivity extends BaseKamenActivity {
    private ImageView im2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launch);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Initialize the view immediately
        im2 = findViewById(R.id.imageView2);View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars =insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;});
        }

        if (im2 != null) {
            Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
            im2.setOnClickListener(v -> {
                im2.setClickable(false);

                // Safety check for MediaPlayer
                try {
                    mp = MediaPlayer.create(this, R.raw.transition);
                    if (mp != null) {
                        mp.start();mp.setOnCompletionListener(mediaPlayer -> {
                            startActivity(new Intent(LauncherActivity.this, Menu.class));
                            finish();
                        });
                    } else {
                        // Fallback if sound fails to load startActivity(new Intent(LauncherActivity.this, Menu.class));
                        finish();
                    }} catch (Exception e) {
                    startActivity(new Intent(LauncherActivity.this, Menu.class));
                    finish();
                }

                if (rotate != null) {
                    handler.postDelayed(() -> im2.startAnimation(rotate), 250);
                }
            });
        }}

    @Override
    protected ImageView getLocalImageView() {
        // Safety check: if onCreate hasn't finished, find it manually
        if (im2 == null) im2 = findViewById(R.id.imageView2);
        return im2;
    }

    @Override
    protected Class<?> getBackTargetClass() {
        return null; // Exit app
    }

    @Override
    protected View getRotaryView() {
        return null; // No rotary on launch screen
    }

    @Override
    protected void setupRotaryLogic() {
        // No rotary logic needed for this screen
    }
}