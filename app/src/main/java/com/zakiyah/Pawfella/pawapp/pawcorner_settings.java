package com.zakiyah.Pawfella.pawapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zakiyah.Pawfella.pprojekkk.R;

public class pawcorner_settings extends AppCompatActivity {
    Button bck, regist,settg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pawcorner_settings);

        bck = findViewById(R.id.button14);
        regist = findViewById(R.id.button15);
        settg = findViewById(R.id.button16);

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pawcorner_settings.this, Main4Activity.class));
                finish();
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://147.139.129.198/f/ucp.php?mode=register"));
                startActivity(i);
            }
        });

        settg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://147.139.129.198/f/ucp.php"));
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(pawcorner_settings.this,Main4Activity.class));
        finish();
    }
}
