package com.zakiyah.Pawfella.pawapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zakiyah.Pawfella.pprojekkk.R;

public class Main4Activity extends AppCompatActivity {
    Button logoutbutton;
    Toolbar toolbar;
    TextView a,b;
    BottomNavigationView bottomnav;
    Button chg_profile,chg_pass,mypet,pawcornerb,about_pawfella;
    Button myprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar = findViewById(R.id.toolbar2);
        chg_profile = findViewById(R.id.button5);
        chg_pass = findViewById(R.id.button6);
        mypet = findViewById(R.id.button3);
        pawcornerb = findViewById(R.id.button7);
        about_pawfella = findViewById(R.id.button8);
        myprofile = findViewById(R.id.button10);
        a = findViewById(R.id.textView4);
        a.setText(splashScreen.sp.getString("namalengkap","err"));
        b = findViewById(R.id.textView5);
        b.setText("@"+splashScreen.sp.getString("loggedUser","err"));
        logoutbutton = findViewById(R.id.logoutbutton);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashScreen.resetSession();
                startActivity(new Intent(Main4Activity.this,splashScreen.class));
                finish();
            }
        });

        chg_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,changepass.class));
                finish();
            }
        });

        mypet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogkamingsun comingsoonDialog = new dialogkamingsun();
                comingsoonDialog.judul="Coming Soon Peeps !";
                comingsoonDialog.pesan="this feature is on construction right now, stay update  for further notification";
                comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
            }
        });

        pawcornerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,pawcorner_settings.class));
                finish();
            }
        });

        chg_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,change_profile.class));
                finish();
            }
        });

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,user_detail.class));
                finish();
            }
        });

        about_pawfella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this,aboutpage.class));
                finish();
            }
        });

        bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setSelectedItemId(R.id.profile_menu);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.message_menu){
                    startActivity(new Intent(Main4Activity.this,message.class));
                    finish();
                }else if(menuItem.getItemId()==R.id.main_menu){
                    startActivity(new Intent(Main4Activity.this,Main3Activity.class));
                    finish();
                }else if(menuItem.getItemId()==R.id.profile_menu){
                    //nothing here
                }
                return false;
            }
        });

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
            if(targetSdkVersion<=Build.VERSION.SDK_INT){
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(Main4Activity.this,R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }

//    @Override
//    public boolean onSupportNavigateUp(){
//        startActivity(new Intent(Main4Activity.this,Main3Activity.class));
//        finish();
//        return true;
//    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            onDestroy();
            //System.out.println("backbutton diklik dua kali");
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
