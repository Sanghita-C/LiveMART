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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText username, passwordTextView,retypePassword;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Set dropdown menu for type
        Spinner spinner = (Spinner) findViewById(R.id.type_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        mAuth = FirebaseAuth.getInstance();
        // initialising all views through id defined above
        username = findViewById(R.id.username);
        passwordTextView = findViewById(R.id.Password);
        retypePassword=findViewById(R.id.retype_password);
        Btn = findViewById(R.id.signUp);
        progressbar = findViewById(R.id.progressbar);

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

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String user, password,retype;
        user = username.getText().toString();
        password = passwordTextView.getText().toString();
        retype=retypePassword.getText().toString();

        // Validations for input username and password
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Username!!",
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
                .createUserWithEmailAndPassword(user, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);

                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(Register.this,
                                    Login.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}