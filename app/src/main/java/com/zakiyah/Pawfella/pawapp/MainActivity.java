package com.zakiyah.Pawfella.pawapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zakiyah.Pawfella.pprojekkk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    TextView uname,forgetpass;
    TextView pwd;
    String urlgo;
    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        Button tombolregist = findViewById(R.id.tombolRegister);
        tombolregist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this,PendaftaranUser.class));
                startActivity(new Intent(MainActivity.this,PendaftaranUser.class));
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

        Button tombolLogin = findViewById(R.id.tombolLogin);
        tombolLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = findViewById(R.id.editText);
                pwd= findViewById(R.id.editText2);
                try {
                    if(verfiyakun(uname.getText().toString(),pwd.getText().toString())){
                        startActivity(new Intent(MainActivity.this,Main3Activity.class));
                        finish();
                    }else {
                        dialogkamingsun comingsoonDialog = new dialogkamingsun();
                        comingsoonDialog.judul="Error !";
                        comingsoonDialog.pesan="please recheck your usn and password";
                        comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        forgetpass = findViewById(R.id.textView6);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogkamingsun comingsoonDialog = new dialogkamingsun();
                comingsoonDialog.judul="Lupa Password";
                comingsoonDialog.pesan="silahkan hubungi admin kami untuk reset password";
                comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
            }
        });

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
        }
    }

    public void masukApp (View v){

    }

    private boolean verfiyakun(String usn,String pass) throws ExecutionException, InterruptedException {
        String s = getjson(usn,pass);
        System.out.println(s);
        if(s.equalsIgnoreCase("0")){
            return false;
        }else{
            try {
                JSONObject j= new JSONObject(s);
                JSONArray jarr = j.getJSONArray("result");
                JSONObject j2 = jarr.getJSONObject(0);
                String username = j2.getString("username");
                String email = j2.getString("email");
                String namalengkap = j2.getString("fullname");
                String alamat = j2.getString("alamat");
                String noHP = j2.getString("nomorHP");
                String kota = j2.getString("kota");
                String status = j2.getString("status");

                splashScreen.spedit.putString("loggedUser",username);
                splashScreen.spedit.putInt("onSession",1);
                splashScreen.spedit.putString("emailUser",email);
                splashScreen.spedit.putString("namalengkap",namalengkap);
                splashScreen.spedit.putString("alamat",alamat);
                splashScreen.spedit.putString("nohp",noHP);
                splashScreen.spedit.putString("kota",kota);
                if(status.equalsIgnoreCase("1")||status.equalsIgnoreCase("0")||status.equalsIgnoreCase("2")){
                    splashScreen.spedit.putInt("status",Integer.parseInt(status));
                }
                splashScreen.spedit.commit();
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

        }

    }

    protected String getjson(String usn,String pass) throws ExecutionException, InterruptedException {
        String securedpass = splashScreen.MD5(pass);
        urlgo = "http://147.139.129.198/pawfel/getLoginInfo.php?username="+usn+"&password="+securedpass;
        class datalogin extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            public String setring;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Loggining In","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... voids) {
                requestHandler rh  = new requestHandler();
               setring = rh.sendGetRequest(urlgo);
                return setring;
            }
        }
        datalogin dl = new datalogin();
        String uwu = dl.execute().get();
        return uwu;
    }

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
