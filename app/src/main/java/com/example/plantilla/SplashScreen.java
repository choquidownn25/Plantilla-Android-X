package com.example.plantilla;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
// import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.plantilla.account.activity.PagerActivity;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class SplashScreen extends Activity {

    private Context contexts;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String message = "", url = "";
    private String TAG  = SplashScreen.class.getName();

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_splash_screen);
        //Bloquear orientación de pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        contexts = getApplication();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            Intent intent = getIntent();
            message = intent.getStringExtra("message");
            url = intent.getStringExtra("url");
            Log.d("notification Dato", message + url);
        } catch (Exception e) {
            Log.d("error notificacion", e.toString());
            Fabric.getLogger().e(Fabric.TAG, "Failed to load splah", e);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                        Fabric.getLogger().e(Fabric.TAG, "Failed to load splah", e);
                        e.printStackTrace();
                    }
                Intent intent = new Intent(SplashScreen.this, PagerActivity.class);
                intent.putExtra("message", message);
                intent.putExtra("url", url);
                startActivity(intent);
                finish();
            }
        });
        thread.start();
    }


}