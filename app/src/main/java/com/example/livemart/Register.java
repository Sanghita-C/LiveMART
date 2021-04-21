package com.example.livemart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText username, passwordTextView,retypePassword,email,entity;
    private Button Btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        // initialising all views through id defined above
        username = findViewById(R.id.username);
        passwordTextView = findViewById(R.id.Password);
        retypePassword=findViewById(R.id.retype_password);
        email=findViewById(R.id.email);
//        entity=findViewById(R.id.type_spinner);
        Btn = findViewById(R.id.signUp);

        // Set on Click Listener on Registration button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });


    }

    private void registerNewUser()
    {


        // Take the value of two edit texts in Strings
        String user, password,retype,Email,type_spinner;
        user = username.getText().toString();
        password = passwordTextView.getText().toString();
        retype=retypePassword.getText().toString();
        Email=email.getText().toString();

        // Validations for input username and password
        if (user.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Username!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (Email.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password) || password.length()<6) {
            Toast.makeText(getApplicationContext(),
                    "Password should be greater than 5 characters!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (!password.equals(retype)) {
            Toast.makeText(getApplicationContext(),
                    "Please retype password again,Check if CAPS LOCK is turned on!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(Email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();


                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(Register.this,
                                    Login.class);
                            startActivity(intent);
                            finish();
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();

                        }
                    }
                });
    }

}