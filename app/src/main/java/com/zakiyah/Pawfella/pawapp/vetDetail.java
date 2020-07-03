package com.zakiyah.Pawfella.pawapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zakiyah.Pawfella.pprojekkk.R;

public class vetDetail extends AppCompatActivity {
    Toolbar toolbar;
    String namadokter,graduatefrom,clinic,pengalaman;
    TextView nmdok, grdfrom, clnc, exprince, rate, praktekfrom;
    Button chatyuk;
    double rating;
    int startpraktek;
    public vetDetail(){

    }
    public vetDetail(String nama, double rate, String grad, String lokpraktek, int startyear, String experience){
        namadokter = nama;
        graduatefrom = grad;
        clinic = lokpraktek;
        pengalaman = experience;
        rating = rate;
        startpraktek = startyear;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_detail);
        Bundle bundle = getIntent().getExtras();
        getextraintent(bundle);
        toolbar = findViewById(R.id.toolbardetaildokter);
        chatyuk = findViewById(R.id.button2);
        nmdok  = findViewById(R.id.textView8_infodokter);
        nmdok.setText(namadokter);
        grdfrom = findViewById(R.id.textView18);
        grdfrom.setText(graduatefrom);
        clnc = findViewById(R.id.tmpt_praktek);
        clnc.setText(clinic);
        exprince = findViewById(R.id.textView17);
        exprince.setText(pengalaman);
        praktekfrom = findViewById(R.id.textView19);
        praktekfrom.setText(Integer.toString(startpraktek));
        rate = findViewById(R.id.textView16);
        rate.setText(String.valueOf(rating));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Dokter");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        chatyuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogkamingsun comingsoonDialog = new dialogkamingsun();
                comingsoonDialog.judul="Coming Soon Peeps !";
                comingsoonDialog.pesan="this feature is on construction right now, stay update  for further notification";
                comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
            }
        });
    }

    public void getextraintent(Bundle b){
        namadokter = b.getString("nama dokter");
        graduatefrom = b.getString("graduate from");
        clinic = b.getString("klinik");
        pengalaman = b.getString("pengalaman kerja");
        rating = b.getDouble("rating dokter");
        startpraktek = b.getInt("start kerja");
    }
    @Override
    public boolean onSupportNavigateUp(){
        startActivity(new Intent(vetDetail.this,askvet.class));
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(vetDetail.this,askvet.class));
        finish();
    }
}
