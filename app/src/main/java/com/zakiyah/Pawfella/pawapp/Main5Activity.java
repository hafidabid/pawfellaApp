package com.zakiyah.Pawfella.pawapp;

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
import android.widget.Button;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {
    Button kembalilah;
    private RecyclerView recyclerView;
    private ArrayList<rumahsakit> daftarRsDarurat;
    private ViewAdapt viewAdapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        kembalilah= findViewById(R.id.button4);
        kembalilah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main5Activity.this,Main3Activity.class));
                finish();
            }
        });
        addata();

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
                window.setStatusBarColor(ContextCompat.getColor(Main5Activity.this,R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        recyclerView = findViewById(R.id.recycler_view);
        viewAdapt = new ViewAdapt(daftarRsDarurat);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Main5Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewAdapt);



    }

    public void addata(){
        daftarRsDarurat = new ArrayList<>();
        daftarRsDarurat.add(new rumahsakit("Gloriavet Bandung","Setrasari Plaza C - 3 \nJl. Prof. Dr. Sutami","+622287775171",4.8,"Buka pukul 9.00 - 17.00"));
        daftarRsDarurat.add(new rumahsakit("Bandung Vet Clinic","Jalan Cikaso No 88 B \nSukamaju, Kota Bandung","+6281333312189",4.3,"Buka pukul 9.00 - 17.00"));
        daftarRsDarurat.add(new rumahsakit("PDHB dr. Anton","Ruko Gyan Plaza B32 \nJl. Terusan Pasirkoja, Bandung","+62226046610",4.5,"Buka pukul 17.00 - 20.00"));
        daftarRsDarurat.add(new rumahsakit("911","No Darurat Universal","911",5,"Gunakan dalam keadaan sangat darurat"));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Main5Activity.this,Main3Activity.class));
        finish();
    }
}

class rumahsakit{
    public String nama;
    public String alamat;
    public String phone;
    public double rate;
    public String keterangan;
    public rumahsakit(){

    }
    public rumahsakit(String nama,String alamat, String phone, double rating, String keterangan){
        this.nama= nama;
        this.alamat= alamat;
        this.phone = phone;
        this.rate = rating;
        this.keterangan = keterangan;
    }

    public void setTime(String o, String c){

    }

    public boolean tokoBuka (){
        return true;
    }
}
