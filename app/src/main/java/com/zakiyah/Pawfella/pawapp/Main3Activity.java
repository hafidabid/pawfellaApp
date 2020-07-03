package com.zakiyah.Pawfella.pawapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zakiyah.Pawfella.pprojekkk.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class Main3Activity extends AppCompatActivity {
    Toolbar tool;
    AppBarLayout apbar;
    ImageButton[] menubutton;
    ImageButton profilerButton;
    TextView topteks;
    BottomNavigationView bottomnav;
    CarouselView cv;
    int[] carouselImage = {R.drawable.pawoffer1, R.drawable.pawoffer2};
    FrameLayout[] fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_view);
        topteks = findViewById(R.id.textView3);
        topteks.setText("Hi "+splashScreen.sp.getString("namalengkap","ERR NO USN DETECTED"));
        tool= findViewById(R.id.toolbar);
        tool.setTitleTextAppearance(this,R.style.Visbyfont);

        menubutton = new ImageButton[]{
                findViewById(R.id.askVetButton),
                findViewById(R.id.pawClinicButton),
                findViewById(R.id.pawStoreButton),
                findViewById(R.id.pawHotelButton),
                findViewById(R.id.pawSalonButton),
                findViewById(R.id.pawAdoptButton),
                findViewById(R.id.pawCornerButton),
                findViewById(R.id.emergencyButton)
        };

        fl = new FrameLayout[]{
          findViewById(R.id.news1),
          findViewById(R.id.news2),
          findViewById(R.id.news3),
          findViewById(R.id.news4),
        };

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(Main3Activity.this,R.color.splash_color));
        }

        //carrousel
        cv = findViewById(R.id.carouselView);
        cv.setPageCount(carouselImage.length);
        cv.setImageListener(imageListener);

        //end of carrousel

        bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setSelectedItemId(R.id.main_menu);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.message_menu){
                    startActivity(new Intent(Main3Activity.this,message.class));
                    finish();
                }else if(menuItem.getItemId()==R.id.main_menu){
                    //nothing was here
                }else if(menuItem.getItemId()==R.id.profile_menu){
                    startActivity(new Intent(Main3Activity.this,Main4Activity.class));
                    finish();
                }
                return false;
            }
        });

        for(int asd =0;asd<8;asd++){
            buttondo(asd);
            if(asd<4){
                pawtoday(asd);
            }
        }


        setSupportActionBar(tool);
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("");
        tv.setTextSize(2);
        tv.setTextColor(Color.parseColor("#000000"));
        Typeface tf = ResourcesCompat.getFont(this,R.font.visbycfbold);
        tv.setTypeface(tf);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);

        apbar =  findViewById(R.id.appbar);

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(carouselImage[position]);
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    };

    private void pawtoday(final int a) {
        final int index = a;
        final String[] link = new String[]{
                "https://www.pethealthnetwork.com/all-pet-health/what-do-we-know-about-covid-19-and-pets",
                "https://www.pethealthnetwork.com/cat-health/cat-surgery-a-z/thyroid-tumor-surgery-cats",
                "https://www.pethealthnetwork.com/dog-health/dog-diseases-conditions-a-z/5-reasons-test-your-dog-diabetes",
                "http://www.pethealthnetwork.com/cat-health/cat-checkups-preventive-care/how-old-your-cat-people-years",
        };
        if(a<4){
            fl[a].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(link[index]));
                    startActivity(i);
                }
            });
        }
    }

    private void buttondo (int a){
        if(a<8){
            if(a==6){
                menubutton[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Main3Activity.this,pawcorner.class));
                        finish();
                    }
                });
            }else if(a==7){
                menubutton[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Main3Activity.this,Main5Activity.class));
                        finish();
                    }
                });
            }else if(a==0){
                menubutton[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Main3Activity.this,askvet.class));
                        finish();
                    }
                });
            }
            else {
                menubutton[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogkamingsun comingsoonDialog = new dialogkamingsun();
                        comingsoonDialog.judul="Coming Soon Peeps !";
                        comingsoonDialog.pesan="this feature is on construction right now, stay update  for further notification";
                        comingsoonDialog.show(getSupportFragmentManager(),"example Dialog");
                    }
                });
            }
        }
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

