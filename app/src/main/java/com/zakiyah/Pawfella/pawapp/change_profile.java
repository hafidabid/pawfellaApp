package com.zakiyah.Pawfella.pawapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class change_profile extends AppCompatActivity {
    ImageButton backToMenu;
    EditText newmail,newphone,newaddr;
    Spinner kotaSpin;
    EditText passwd;
    Button appli;
    String citiedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
        newmail = findViewById(R.id.email_editor);
        newphone = findViewById(R.id.phone_editor);
        newaddr = findViewById(R.id.addr_editor);
        kotaSpin = findViewById(R.id.cityedit);
        passwd = findViewById(R.id.password_confirm);
        newmail.setText(splashScreen.sp.getString("emailUser",""));
        newphone.setText(splashScreen.sp.getString("nohp",""));
        newaddr.setText(splashScreen.sp.getString("alamat","error"));
        appli = findViewById(R.id.button13);

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
                window.setStatusBarColor(ContextCompat.getColor(change_profile.this,R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        appli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogkamingsun comingsoonDialog = new dialogkamingsun();
                if(kotaSpin.getSelectedItem().toString().equals("Pilih Kota")) {
                    comingsoonDialog.judul="Error !";
                    comingsoonDialog.pesan="Pilihlah kota yang benar";
                    comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
                }else{
                    citiedit = kotaSpin.getSelectedItem().toString();
                    if(checkpass(passwd.getText().toString())==false){
                        comingsoonDialog.judul="Error !";
                        comingsoonDialog.pesan="password salah!";
                        comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
                    }else{
                        updatedata(passwd.getText().toString());
                        dialogsukses dialogberhasil = new dialogsukses();
                        dialogberhasil.judul="profile has changed !";
                        dialogberhasil.pesan="your profile has been successfully changed";
                        dialogberhasil.show(getSupportFragmentManager(),"example Dialog");
//                        final Handler handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                startActivity(new Intent(change_profile.this,Main4Activity.class));
//                                finish();
//                            }
//                        },1300);

                    }
                }
            }
        });

        backToMenu = findViewById(R.id.backaskvet);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(change_profile.this, Main4Activity.class));
                finish();
            }
        });
    }

    private boolean checkpass(String s){
        String secured = splashScreen.MD5(s);
        String lynk = "http://147.139.129.198/pawfel/getLoginInfo.php?username="+splashScreen.sp.getString("loggedUser","asuuuuuu")+"&password="+secured;
        getSinkronClass gsc = new getSinkronClass(lynk);
        String h = null;
        try {
            h = gsc.execute().get();
            if(h.charAt(0)==48){
                return false;
            }else{
                return true;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void updatedata(String pass){
        String secured = splashScreen.MD5(pass);
        final String emailbaru;
        final String hpbaru;
        final String alamatbaru;
        final String namaku = splashScreen.sp.getString("namalengkap","error");
        final String kotabaru = citiedit;
        final String lynk = "http://147.139.129.198/pawfel/changeProfile.php?username="+splashScreen.sp.getString("loggedUser","asuuuuuu")+"&password="+secured;

        if(newmail.getText().toString().length()>0){
            emailbaru= newmail.getText().toString();
        }else{emailbaru = splashScreen.sp.getString("emailUser","error");}

        if(newphone.getText().toString().length()>0){
            hpbaru= newphone.getText().toString();
        }else{hpbaru = splashScreen.sp.getString("nohp","error");}

        if(newaddr.getText().toString().length()>0){
            alamatbaru= newaddr.getText().toString();
        }else{alamatbaru = splashScreen.sp.getString("alamat","error");}

        if(emailbaru.equalsIgnoreCase("error") || hpbaru.equalsIgnoreCase("error")||alamatbaru.equalsIgnoreCase("error")){
            dialogkamingsun comingsoonDialog = new dialogkamingsun();
            comingsoonDialog.judul="Error !";
            comingsoonDialog.pesan="something error was happened\n email = "+emailbaru+"\n hp = "+hpbaru+"\n alamat = "+alamatbaru;
            comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
        }else{
            class setoranyuk extends AsyncTask<Void,Void,String>{
                ProgressDialog loading;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    //loading = ProgressDialog.show(change_profile.this,"Changing your profile...","Tunggu...",false,false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    //loading.dismiss();
                }
                @Override
                protected String doInBackground(Void... voids) {
                    HashMap<String,String> hm = new HashMap<>();
                    hm.put("newmail",emailbaru);
                    hm.put("newname",namaku);
                    hm.put("newaddr",alamatbaru);
                    hm.put("newhp",hpbaru);
                    hm.put("newcity",kotabaru);
                    requestHandler rh = new requestHandler();
                    String uwu = rh.sendPostRequest(lynk,hm);
                    return uwu;
                }
            }
            setoranyuk syuk = new setoranyuk();
            syuk.execute();

            splashScreen.spedit.putString("emailUser",emailbaru);
            splashScreen.spedit.putString("alamat",alamatbaru);
            splashScreen.spedit.putString("nohp",hpbaru);
            splashScreen.spedit.putString("kota",kotabaru);
            splashScreen.spedit.commit();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(change_profile.this,Main4Activity.class));
        finish();
    }
}
