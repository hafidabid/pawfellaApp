package com.zakiyah.Pawfella.pawapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.util.ArrayList;

public class askvet extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<listdokter> dokterku;
    private askvetadaptor avd;
    ImageButton backToMenu;
    InputMethodManager imm;
    EditText pencarian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askvet);

        //recycler view
        addata();
        recyclerView = findViewById(R.id.rview);
        avd = new askvetadaptor(dokterku);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(askvet.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(avd);
        //end of recyler view

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
                window.setStatusBarColor(ContextCompat.getColor(askvet.this,R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        pencarian = findViewById(R.id.editText3);
        pencarian.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    askvet.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                }else {
                    InputMethodManager umm = (InputMethodManager) askvet.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    umm.hideSoftInputFromWindow(pencarian.getWindowToken(),0);
                }
            }
        });

        backToMenu = findViewById(R.id.backaskvet);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(askvet.this, Main3Activity.class));
                finish();
            }
        });

    }

    public void addata(){
        dokterku = new ArrayList<>();
        dokterku.add(new listdokter("drh. Alya",4.8,2015,"Aero Pet Clinic","Universitas Gadjah Mada","5 years experience"));
        dokterku.add(new listdokter("drh. Naufal ",5.0,2012,"Patra Pet Clinic","Universitas Indonesia","8 years experience"));
        dokterku.add(new listdokter("drh. Gini",4.7,2017,"Amity Pet Clinic","Universitas Airlangga","3 years experience"));
        dokterku.add(new listdokter("drh. Evran ",4.3,2010,"Private Clinic","Universitas Diponegoro","10 years experience"));


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(askvet.this,Main3Activity.class));
        finish();
    }


}

class listdokter{
    String name;
    double rating;
    int startyear;
    String clinic;
    String graduate;
    String experience;

    public listdokter(){}
    public listdokter(String n, double r, int styear, String c, String g, String exp){
        name = n;
        rating = r;
        clinic = c;
        graduate = g;
        startyear = styear;
        experience = exp;
    }
}
