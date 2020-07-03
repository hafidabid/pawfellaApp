package com.zakiyah.Pawfella.pawapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zakiyah.Pawfella.pprojekkk.R;

public class user_detail extends AppCompatActivity {
    ImageButton backToMenu;
    TextView nama, imel,almt,nope,kota,myusn;
    Button editprofil, chgpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        nama  = findViewById(R.id.tmpt_praktek);
        imel  = findViewById(R.id.textView16);
        almt  = findViewById(R.id.textView17);
        nope = findViewById(R.id.textView18);
        kota = findViewById(R.id.textView19);
        editprofil = findViewById(R.id.button11);
        chgpass = findViewById(R.id.button12);
        myusn = findViewById(R.id.show_usn);
        myusn.setText(splashScreen.sp.getString("loggedUser","err"));

        nama.setText(splashScreen.sp.getString("namalengkap","error"));
        imel.setText(splashScreen.sp.getString("emailUser","error"));
        almt.setText(splashScreen.sp.getString("alamat","error"));
        nope.setText(splashScreen.sp.getString("nohp","error"));
        kota.setText(splashScreen.sp.getString("kota","error"));

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            int targetSdkVersion = 0;
            PackageInfo packageInfo = null;
            try {
                packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                targetSdkVersion = packageInfo.applicationInfo.targetSdkVersion;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            //window.setStatusBarColor(ContextCompat.getColor(change_profile.this,R.color.white));
            if(targetSdkVersion<=Build.VERSION.SDK_INT){
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(user_detail.this,R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_detail.this,change_profile.class));
                finish();
            }
        });

        chgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_detail.this,changepass.class));
                finish();
            }
        });

        backToMenu = findViewById(R.id.backaskvet);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_detail.this, Main4Activity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(user_detail.this,Main4Activity.class));
        finish();
    }
}
