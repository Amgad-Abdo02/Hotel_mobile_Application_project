package project.ex.hotelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

EditText name,pass,confirmpass,email;
TextView login;
CheckBox age,terms;
LinearLayout laytext;
Animation go_down,appear,go_up;
    androidx.appcompat.widget.AppCompatButton register;
    SharedPreferences preference;
final Handler delay = new Handler();
FirebaseDatabase root;
DatabaseReference refrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = getSharedPreferences("mine",MODE_PRIVATE);
        fullscreen();
        setContentView(R.layout.activity_register);
        findViews();
        starter_look();
        register.setOnClickListener(this);
        login.setOnClickListener(this); }

           @Override
           public void onClick(View v) {
               Intent i;
                switch(v.getId()){

                    case(R.id.login):
                         i = new Intent(Register.this, Signin.class);
                        i.putExtra("returned",true);
               startActivity(i);
               overridePendingTransition(R.anim.appear,R.anim.disappear);
               break;
                    case(R.id.register):
                        Register();
                        SharedPreferences.Editor editor = preference.edit();
                        if(name.getText().toString().equals(""))
                            editor.putString("name","user");
                        else
                            editor.putString("name",name.getText().toString());
                        editor.commit();
                         i = new Intent(Register.this,Sounds.class);
                        i.putExtra("turn",1);

                        startActivity(new Intent(Register.this,MainPage.class));
                        overridePendingTransition(R.anim.appear,R.anim.disappear);
                        startService(i);
                        break;
           }}


         {


    }
    private void findViews(){
        name = findViewById(R.id.Username);
        pass = findViewById(R.id.pass);
        confirmpass = findViewById(R.id.repass);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        terms = findViewById(R.id.terms);
        laytext = findViewById(R.id.txtlayout);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        go_down = AnimationUtils.loadAnimation(this,R.anim.go_down);
        appear = AnimationUtils.loadAnimation(this,R.anim.appear);
        go_up = AnimationUtils.loadAnimation(this, R.anim.go_updeep);
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
    private void starter_look(){
        name.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        confirmpass.setVisibility(View.GONE);
        pass.setVisibility(View.GONE);
        age.setVisibility(View.GONE);
        terms.setVisibility(View.GONE);
        delay.postDelayed(new Runnable() {
            @Override
            public void run() {
                laytext.setAnimation(go_down);
                register.setAnimation(go_up);
                pass.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                confirmpass.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                age.setVisibility(View.VISIBLE);
                terms.setVisibility(View.VISIBLE);

                pass.setAnimation(appear);
                email.setAnimation(appear);
                name.setAnimation(appear);
                age.setAnimation(appear);
                confirmpass.setAnimation(appear);
                terms.setAnimation(appear);
            }
        },800);
    }
    private void Register(){
        root = FirebaseDatabase.getInstance();
        refrence = root.getReference("User");
        String Username = name.getText().toString();
        String E_mail= email.getText().toString();
        String Pass = pass.getText().toString();
        Account_model model = new Account_model(Username,E_mail,Pass);
        refrence.child(Username).setValue(model);

    }

}