package com.example.firstapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity  {
    private EditText mailid,password;
    private PinView pinView;
    private Button submit,go;
    private TextView Enterotp,resend;
    private FirebaseAuth firebaseAuth ;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Sign In");
        setContentView(R.layout.activity_sign_in);
        mailid= findViewById(R.id.mailId_signIn);
        password= findViewById(R.id.password_signIn);
        pinView = findViewById(R.id.pinView);
        submit = findViewById(R.id.submit);
        go= findViewById(R.id.go);
        Enterotp=findViewById(R.id.EnterOTP);
        progressBar=findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {

                    public void onClick (View view){
                        String username = mailid.getText().toString();
                        String pass = password.getText().toString();
                            if (!TextUtils.isEmpty(username) && username.contains("@gmail.com") && pass.length() > 6 )
                            {
                                     go.setVisibility(View.VISIBLE);
                                     Enterotp.setVisibility(View.VISIBLE);
                                     pinView.setVisibility(View.VISIBLE);
                             }
                            else {
                                Toast.makeText(SignIn.this, "please enter correct details", Toast.LENGTH_SHORT).show();
                            }
                      }
                });
        go.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                      String otp = pinView.getText().toString();
                      String username = mailid.getText().toString();
                      String pass = password.getText().toString();
                      if(otp.equals("3456"))
                      {     progressBar.setVisibility(View.VISIBLE);
                           firebaseAuth.createUserWithEmailAndPassword(username, pass)
                              .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                                     @Override
                                     public void onComplete(@NonNull Task<AuthResult> task) {
                                         if (task.isSuccessful()) {
                                             pinView.setLineColor(Color.GREEN);
                                             Enterotp.setText("OTP Verified");
                                             Enterotp.setTextColor(Color.GREEN);
                                             Intent intent = new Intent(SignIn.this,Login.class);
                                             startActivity(intent);
                                             finish();

                                         }
                                         else {
                                                Toast.makeText(SignIn.this, "Failed Please try again", Toast.LENGTH_SHORT).show();
                                         }
                                     }
                              });


                      }
                      else{
                          pinView.setLineColor(Color.RED);
                          Enterotp.setText("!Incorrect OTP");
                          Enterotp.setTextColor(Color.RED);
                      }


            }
        });
    }



}
