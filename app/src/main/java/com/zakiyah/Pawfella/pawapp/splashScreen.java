package com.zakiyah.Pawfella.pawapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.io.UnsupportedEncodingException;

public class splashScreen extends AppCompatActivity {
    public static SharedPreferences sp;
    public static SharedPreferences.Editor spedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences("PawSetup",Context.MODE_PRIVATE);
        spedit = sp.edit();
        int punyaSP = sp.getInt("onSession",99);
        if(punyaSP==99){
            createSP();
        }

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //window.setStatusBarColor(ContextCompat.getColor(splashScreen.this,R.color.splash_color));
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int onSess = sp.getInt("onSession",99);
                if(onSess==0){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else if(onSess==1){
                    startActivity(new Intent(getApplicationContext(),Main3Activity.class));
                }
                finish();
            }
        },1800L);

    }


    protected void createSP(){
        spedit.putInt("onSession",0);
        spedit.putString("loggedUser","nulluser");
        spedit.putInt("antiFraudMode",0);
        spedit.commit();
    }

    public static void resetSession(){
        spedit.putInt("onSession",0);
        spedit.putString("loggedUser","nulluser");
        spedit.putString("loggedUser","");
        spedit.putString("emailUser","");
        spedit.putString("namalengkap","");
        spedit.putString("alamat","");
        spedit.putString("nohp","");
        spedit.putString("kota","");
        spedit.commit();
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }
}
