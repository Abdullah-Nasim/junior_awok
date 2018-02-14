package com.myown.juniorawok.activities.splash_activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.myown.juniorawok.R;

import static com.myown.juniorawok.utils.NavigationController.startMainActivity;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is the first activity which is launched upon application startup.
 * This activity will just wait for 2 seconds and will call the navigation controller method to start
 * MainActivity.
 */

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_LENGTH = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                //Call the NavigationController to start main activity
                startMainActivity(SplashActivity.this);
                finish();

            }
        }, SPLASH_LENGTH);
    }
}
