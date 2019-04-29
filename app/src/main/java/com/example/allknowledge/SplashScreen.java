package com.example.allknowledge;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.allknowledge.main.MainActivity;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread thread = new Thread(() -> {

            try {
                sleep(3000);
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            finally {
                finish();
            }
        });

        thread.start();
    }



}
