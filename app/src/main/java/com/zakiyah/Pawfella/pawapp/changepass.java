package com.zakiyah.Pawfella.pawapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class changepass extends AppCompatActivity {
    BottomNavigationView bottomnav;
    EditText n1,n2,o;
    Button appli;
    TextView warn;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        toolbar= findViewById(R.id.toolbar2);
        warn = findViewById(R.id.textView24);
        n1 = findViewById(R.id.editText4);
        n2 = findViewById(R.id.editText5);
        o = findViewById(R.id.editText6);
        appli = findViewById(R.id.button9);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((n1.getText().toString().equals(n2.getText().toString()))==false){
                    warn.setText("new password mismatch!");
                }else {
                        String lynk = "http://147.139.129.198/pawfel/getLoginInfo.php?username="+splashScreen.sp.getString("loggedUser","asuuuuuu")+"&password="+splashScreen.MD5(o.getText().toString());
                        getSinkronClass gsc = new getSinkronClass(lynk);
                    try {
                        String h = gsc.execute().get();
                        if(h.charAt(0)==48){
                            warn.setText("wrong old password!");
                        }else{
                            setordata();
                            dialogsukses dialogberhasil = new dialogsukses();
                            dialogberhasil.judul="password has changed !";
                            dialogberhasil.pesan="your password has been successfully changed";
                            dialogberhasil.show(getSupportFragmentManager(),"example Dialog");
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void setordata(){
        class nanana extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(changepass.this,"Changing your password...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... voids) {
                String secured = splashScreen.MD5(o.getText().toString());
                String lebihaman = splashScreen.MD5(n1.getText().toString());
                HashMap<String,String> hm = new HashMap<>();
                hm.put("newpass",lebihaman);
                hm.put("username",splashScreen.sp.getString("loggedUser","asuuuuuu"));
                hm.put("password",secured);
                requestHandler rh = new requestHandler();
                String sss = rh.sendPostRequest("http://147.139.129.198/pawfel/changePass.php",hm);
                return sss;
            }
        }
        nanana na = new nanana();
        na.execute();
    }
    @Override
    public boolean onSupportNavigateUp(){
        Main4Activity m = new Main4Activity();
        startActivity(new Intent(changepass.this,m.getClass()));
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(changepass.this,Main4Activity.class));
        finish();
    }
}
