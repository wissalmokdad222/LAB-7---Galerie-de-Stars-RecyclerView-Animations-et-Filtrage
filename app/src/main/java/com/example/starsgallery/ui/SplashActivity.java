package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.starsgallery.R;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);

        logo.animate()
                .rotation(360f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .translationY(1000f)
                .alpha(0f)
                .setDuration(3000)
                .withEndAction(() -> {
                    startActivity(new Intent(SplashActivity.this, ListActivity.class));
                    finish();
                });
    }
}