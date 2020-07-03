package com.zakiyah.Pawfella.pawapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zakiyah.Pawfella.pprojekkk.R;

public class aboutpage extends AppCompatActivity {
    TextView credit,projecter;
    Button bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutpage);
        bck = findViewById(R.id.button17);
        credit = findViewById(R.id.paw_about);
        String utter = "PawFella version 1.2.0\n";
        utter=utter+"designed to compatible with android 5.0 and above (SDK 21++)\n";
        utter=utter+"recommended running in android 7.0\n\n\n";
        utter=utter+"PawFella is a pet care application and belonging to:\n";
        utter=utter+"Zakiyah Artanti Ratnadewi\nSchool of Business and Management, ITB \n";
        utter = utter+"19719147";

        credit.setText(utter);
        projecter = findViewById(R.id.textView40);
        projecter.setText(
                "This awesome application, was built by:\n" +
                "Hafid Abi Daniswara (STEI ITB) as software developer\n"+
                "Zakiyah Artanti Ratnadewi (SBM ITB) as PawFella founder\n\n\n"+
                "for Inquiries :\n"+
                "zakiyah_artanti@sbm-itb.ac.id\n"+
                "abi.daniswara@gmail.com\n"
        );
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(aboutpage.this,Main4Activity.class));
                finish();
            }
        });


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int targetSdkVersion = 0;
            PackageInfo packageInfo = null;
            try {
                packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                targetSdkVersion = packageInfo.applicationInfo.targetSdkVersion;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            if(targetSdkVersion>=Build.VERSION.SDK_INT){
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(aboutpage.this,Main4Activity.class));
        finish();
    }
}
