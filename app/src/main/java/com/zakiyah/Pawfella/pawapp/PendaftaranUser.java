package com.zakiyah.Pawfella.pawapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class PendaftaranUser extends AppCompatActivity {

    Spinner kotaSpin;
    EditText usernameForm;
    EditText passForm ;
    EditText emailForm ;
    EditText namaForm ;
    EditText alamatForm ;
    EditText noHapeForm ;
    String kotaAsal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registform);
        Button kembali = findViewById(R.id.backbutton);
        Button finalkeun = findViewById(R.id.button);
        kotaSpin = (Spinner) findViewById(R.id.cityedit);
        usernameForm =(EditText) findViewById(R.id.usn);
        passForm = (EditText) findViewById(R.id.pass);
        emailForm = (EditText) findViewById(R.id.email);
        namaForm = (EditText) findViewById(R.id.fullname);
        alamatForm = (EditText) findViewById(R.id.alamat);
        noHapeForm = (EditText) findViewById(R.id.nomorHP);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PendaftaranUser.this,MainActivity.class));
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

        finalkeun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kotaAsal = kotaSpin.getSelectedItem().toString();
                try {
                    verifikasiAll();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected boolean verifikasiAll() throws ExecutionException, InterruptedException {
        boolean a= false;
        int x;
        x=0;
        if(verifikasiUsername(usernameForm.getText().toString())){x++;}
        if(verifikasiPartial(passForm.getText().toString(),R.id.warningPass,"tidak boleh kosong",5)){x++;}
        if(verifikasiPartial(alamatForm.getText().toString(),R.id.warningAddrs,"tidak boleh kosong",7)){x++;}
        if(verifikasiPartial(noHapeForm.getText().toString(),R.id.warningPhoneCity,"tidak boleh kosong",10)){x++;}
        if(verifikasiPartial(namaForm.getText().toString(),R.id.warningFname,"tidak boleh kosong",3)){x++;}
        if(verifikasiPartial(emailForm.getText().toString(),R.id.warningEmail,"tidak boleh kosong",5)){x++;}
        if(kotaAsal.equalsIgnoreCase("Pilih Kota")==false){x++;}

        System.out.println(x);
        if(x==7){
            a= true;
            setorkanData();
        }
        return a;
    }
    protected boolean verifikasiPartial(String s, int id, String pesan, int l){
        boolean a = false;
        if(s.length()>0 && s.length()>l){
            a = true;
            TextView tvw = (TextView) findViewById(id);
            tvw.setText("");
        }else{
            TextView tvw = (TextView) findViewById(id);
            tvw.setText(pesan);
        }
        return a;
    }
    protected boolean verifikasiUsername(String usn) throws ExecutionException, InterruptedException {
        boolean a= false;
        requestHandler rh = new requestHandler();
        String link = "http://147.139.129.198/pawfel/cekusername.php?username="+usn;
        getSinkronClass sync = new getSinkronClass(link);
        String vusn = sync.execute().get();
        System.out.println("nilaimu adalaha vusn = "+vusn);
        for(int x=0;x<vusn.length();x++){
            int asciiValue = vusn.charAt(x);
            System.out.println(asciiValue);
        }
        if(usn.length()>0 && vusn.charAt(0)==48){
            a= true;
            TextView huancuk = (TextView) findViewById(R.id.warningUsn);
            String ppp = "";
            huancuk.setText(ppp);
        }else if(vusn.charAt(0)==49){
            TextView huancuk = (TextView) findViewById(R.id.warningUsn);
            String ppp = "Username sudah dipakai";
            huancuk.setText(ppp);
        }else{
            TextView huancuk = (TextView) findViewById(R.id.warningUsn);
            String ppp = "tidak boleh kosong";
            huancuk.setText(ppp);
        }
        return a;
    }
    protected void setorkanData(){

        class AddData extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PendaftaranUser.this,"We're setuping your paw...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(PendaftaranUser.this,s,Toast.LENGTH_LONG).show();


                splashScreen.spedit.putString("loggedUser",usernameForm.getText().toString());
                splashScreen.spedit.putInt("onSession",1);
                splashScreen.spedit.putString("emailUser",emailForm.getText().toString());
                splashScreen.spedit.putString("namalengkap",namaForm.getText().toString());
                splashScreen.spedit.putString("alamat",alamatForm.getText().toString());
                splashScreen.spedit.putString("nohp",noHapeForm.getText().toString());
                splashScreen.spedit.putString("kota",kotaAsal);
                splashScreen.spedit.putInt("status",1);
                splashScreen.spedit.commit();

                startActivity(new Intent(PendaftaranUser.this,Main3Activity.class));
                finish();
            }

            @Override
            protected String doInBackground(Void... voids) {
                String secured = splashScreen.MD5(passForm.getText().toString());
                HashMap<String,String> p = new HashMap<>();
                p.put("uname",usernameForm.getText().toString());
                p.put("pass",secured);
                p.put("email",emailForm.getText().toString());
                p.put("fullname",namaForm.getText().toString());
                p.put("alamat",alamatForm.getText().toString());
                p.put("hp",noHapeForm.getText().toString());
                p.put("kota",kotaAsal);

                HashMap<String,String> hm = new HashMap<>();
                hm.put("username",usernameForm.getText().toString());
                hm.put("password",passForm.getText().toString());
                hm.put("email",emailForm.getText().toString());
                requestHandler rh = new requestHandler();
                rh.sendPostRequest("http://147.139.129.198/f/adduserpawcorner.php",hm);
                String res = rh.sendPostRequest("http://147.139.129.198/pawfel/addmember.php",p);

                return res;
            }

        }

        AddData addData = new AddData();
        addData.execute();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PendaftaranUser.this,MainActivity.class));
        finish();
    }
}
