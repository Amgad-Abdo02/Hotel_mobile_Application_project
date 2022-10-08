package project.ex.hotelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainPage extends AppCompatActivity implements View.OnClickListener{
TextView user,card1name,card2name,card3name,card4name; ImageView logout;
LinearLayout view;
CardView card1,card2,card3,card4;
Handler delay = new Handler();
androidx.constraintlayout.widget.ConstraintLayout main;
Animation disappear,appear;
SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullscreen();
        setContentView(R.layout.activity_main_page);
        preference = getSharedPreferences("mine",MODE_PRIVATE);
        findviews();
        Intent i = getIntent();
        if(i.getBooleanExtra("first visit",true))
        Welcome();
        else
            view.setVisibility(View.GONE);

        logout.setOnClickListener(this);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainPage.this, InfoActivity.class);
            switch (v.getId()) {
                case(R.id.card1):
                i.putExtra("hotel name", card1name.getText().toString());
                startActivity(i);
                break;
                case(R.id.card2):
                    i.putExtra("hotel name", card2name.getText().toString());
                    startActivity(i);
                    break;
                case(R.id.card3):
                    i.putExtra("hotel name", card3name.getText().toString());
                    startActivity(i);
                    break;
                case(R.id.card4):
                    i.putExtra("hotel name", card4name.getText().toString());
                    startActivity(i);
                    break;

                case(R.id.logout):
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putBoolean("kept logged", false);
                    editor.commit();
                    startActivity(new Intent(MainPage.this, Signin.class));
                    overridePendingTransition(R.anim.appear,R.anim.disappear);
                    break;
            }
    }
    private void fullscreen(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        this.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        );
    }
    private void findviews(){
        card1name = findViewById(R.id.card1name);
        card3name = findViewById(R.id.card3name);
        card2name = findViewById(R.id.card2name);
        card4name = findViewById(R.id.card4name);
        user = findViewById(R.id.usertxt);
        main = findViewById(R.id.main);
        card1 = findViewById(R.id.card1);
        card3 = findViewById(R.id.card3);
        card2 = findViewById(R.id.card2);
        card4 = findViewById(R.id.card4);
        logout = findViewById(R.id.logout);
        view = findViewById(R.id.welcomelayer);
        disappear = AnimationUtils.loadAnimation(this, R.anim.disappear);
        appear = AnimationUtils.loadAnimation(this,R.anim.appear);
    }
    private void Welcome(){
        main.setVisibility(View.GONE);
        String str = preference.getString("name","") ;
        str = str + " !";
        user.setText(str);

        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                main.setAnimation(appear);
                view.setAnimation(disappear);

                delay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        main.setVisibility(View.VISIBLE);
                        view.setVisibility(View.GONE);
                    }
                },900);
            }
        },800);
    }


}