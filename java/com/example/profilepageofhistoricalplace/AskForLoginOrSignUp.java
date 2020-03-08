package com.example.profilepageofhistoricalplace;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AskForLoginOrSignUp extends AppCompatActivity {

    private Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_login_or_sign_up);


        b1=(Button)findViewById(R.id.main_join_now_btn);
        b2=(Button)findViewById(R.id.main_login_btn);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AskForLoginOrSignUp.this,Login.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AskForLoginOrSignUp.this,SignUp.class);
                startActivity(i);
            }
        });



    }
}
