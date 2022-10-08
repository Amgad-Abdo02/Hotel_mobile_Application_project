package project.ex.hotelapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.Set;

public class Signin extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    androidx.appcompat.widget.AppCompatButton login,login2;
    TextView register; LinearLayout laytext; EditText pass,email;
    Animation go_up,go_down,appear,disappear,goup;
    CheckBox logged;
    SharedPreferences preferences;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullscreen();
        setContentView(R.layout.activity_signin);
        preferences = getSharedPreferences("mine",MODE_PRIVATE);
        findviews();
        Intent i = getIntent();
        if(i.getBooleanExtra("returned",false))
        {  starter_look();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                entrance();
            }
        },800);
            }
        else
            starter_look();
        kept_logged();
        login2.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        logged.setOnCheckedChangeListener(this);

    }
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case(R.id.login2):
            pass.setVisibility(View.GONE);
            email.setVisibility(View.GONE);
            login2.setVisibility(View.GONE);
            logged.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            login.setClickable(false);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences.Editor editor = preferences.edit();
                            Intent i = new Intent(Signin.this, Sounds.class);
                            i.putExtra("turn",1);
                            editor.putString("name","user");
                            editor.commit();
                            startActivity(new Intent(Signin.this, MainPage.class));
                            overridePendingTransition(R.anim.appear,R.anim.disappear);
                            startService(i);
                        }
                    },400);
                    break;
            case(R.id.login):
                entrance();
                break;
            case(R.id.register):
                startActivity(new Intent(Signin.this, Register.class));
                overridePendingTransition(R.anim.appear,R.anim.disappear);
                break;
        }

    logged.setOnCheckedChangeListener(this);}
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView == logged) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("kept logged", isChecked);
                editor.commit();

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


        );
    }
    private void findviews(){
        logged = findViewById(R.id.checkBox);
        login = findViewById(R.id.login);
        login2 = findViewById(R.id.login2);
        register = findViewById(R.id.register);
        laytext = findViewById(R.id.txtlayout);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.email);
        go_up = AnimationUtils.loadAnimation(this,R.anim.go_up);
        go_down = AnimationUtils.loadAnimation(this,R.anim.go_down);
        appear = AnimationUtils.loadAnimation(this,R.anim.appear);
        disappear = AnimationUtils.loadAnimation(this,R.anim.disappear);
        goup = AnimationUtils.loadAnimation(this,R.anim.go_up2);
    }
    private void starter_look(){
        login2.setVisibility(View.GONE);
        pass.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        logged.setVisibility(View.GONE);
    }
    private void entrance(){
        login.setAnimation(go_up);
        laytext.setAnimation(go_down);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pass.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                logged.setVisibility(View.VISIBLE);
                pass.setAnimation(appear);
                email.setAnimation(appear);
                logged.setAnimation(appear);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        login.setVisibility(View.GONE);
                        login2.setVisibility(View.VISIBLE);
                    }
                },800);

            }
        },100);
    }
    private void kept_logged(){
        if(preferences.getBoolean("kept logged",false))
        { SharedPreferences.Editor editor = preferences.edit();
            Intent i = new Intent(Signin.this, Sounds.class);
            i.putExtra("turn",1);
            editor.putString("name","user");
            editor.commit();
            startActivity(new Intent(Signin.this, MainPage.class));
            overridePendingTransition(R.anim.appear,R.anim.disappear);
            startService(i);}
    }
    /*private void IsUser(){
        final  String Entered_email = email.getText().toString();
        final String Entered_pass = pass.getText().toString();
        DatabaseReference refrence = FirebaseDatabase.getInstance().getReference("User");
        Query checkUser = refrence.orderByChild("e-mail").equalTo(Entered_email);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                  email.setError(null);


                 String passwordFromDB = snapshot.child(Entered_email).child("password").getValue(String.class);
                 if(passwordFromDB.equals(Entered_pass)){
                     String NameFromDB = snapshot.child(Entered_email).child("name").getValue(String.class);
                     String EmailFromDB = snapshot.child(Entered_email).child("e_name").getValue(String.class);

                 }else{
                     pass.setError("Wrong password");
                     pass.requestFocus();
                 }
                }else{
                  email.setError("Email is not registered ");
                  email.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/


}