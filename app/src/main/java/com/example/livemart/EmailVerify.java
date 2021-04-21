package com.example.livemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class EmailVerify extends AppCompatActivity {
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);
        mauth=FirebaseAuth.getInstance();

        Button btn=findViewById(R.id.verifyEmail);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Verification email sent", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        Button toMain=findViewById(R.id.continueMain);
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mauth.getCurrentUser().isEmailVerified())
                {
                    startActivity(new Intent(getApplicationContext(),MainDashboard.class));
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please verify Email address", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}