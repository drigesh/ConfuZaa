package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mailid, password;
    Button login, signin;
    private FirebaseAuth firebaseAuth;
//  CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mailid = findViewById(R.id.MailId_main);
        password =  findViewById(R.id.Password_main);
        login =  findViewById(R.id.LogIn);
        signin =  findViewById(R.id.SignIn);
//      remember=findViewById(R.id.remember);
        firebaseAuth = FirebaseAuth.getInstance();
//        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
//        String checkbox = preferences.getString("remember","");
//        if(checkbox.equals(true)){
//            Intent intent = new Intent(Login.this, MainActivity.class);
//            startActivity(intent);
//        }
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = mailid.getText().toString();
                String pass = password.getText().toString();
                if (!TextUtils.isEmpty(username) && username.contains("@gmail.com") && pass.length() > 6) {
                    firebaseAuth.signInWithEmailAndPassword(username, pass)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(Login.this, "please enter correct details Or Sign Up", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(Login.this, "please enter correct details", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        remember.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(compoundButton.isChecked()){
//                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("remember","true");
//                    editor.apply();
//
//                }
//                else{
//                     SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//                     SharedPreferences.Editor editor = preferences.edit();
//                     editor.putString("remember","false");
//                     editor.apply();
//
//                }
//            }
//        });

    }
    public void signIn (View view)
        {
            Intent intent = new Intent(Login.this, SignIn.class);
            startActivity(intent);
            finish();

        }


}

