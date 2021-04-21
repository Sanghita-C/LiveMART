package com.example.livemart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private EditText username,passwordText;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private LoginButton fbButton;
    private SignInButton googlebtn;
    private static int SIGN_IN=1;
    private GoogleApiClient googleApiClient;

    CallbackManager callbackManager;

    //    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        username = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        Btn = findViewById(R.id.login);
        fbButton=findViewById(R.id.fb_login);

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });

        //Go to register page
        Button register=(Button) findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent j=new Intent(Login.this,Register.class);
                startActivity(j);
            }
        });

//        Handling facebook login
        callbackManager = CallbackManager.Factory.create();
        fbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(getApplicationContext(),MainDashboard.class));
                finish();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        //Handling google sign in
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        googlebtn=findViewById(R.id.google_login);
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(i,SIGN_IN);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN)
        {
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                startActivity(new Intent(getApplicationContext(),MainDashboard.class));
                finish();
            }
        }
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


    private void loginUserAccount()
    {


        // Take the value of two edit texts in Strings
        String user, password;
        user = username.getText().toString();
        password = passwordText.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Username!",
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

        // signin existing user
        mAuth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!",
                                            Toast.LENGTH_LONG)
                                            .show();


                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(Login.this,
                                            EmailVerify.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Please enter correct username and password",
                                            Toast.LENGTH_LONG)
                                            .show();


                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}