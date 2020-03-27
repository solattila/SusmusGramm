package com.solattila.susmusgramm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignIn extends AppCompatActivity {

    private EditText edtMail;
    private EditText edtPassword;
    private Button btnSignUp;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setTitle("Bejelentkezés");

        edtMail = findViewById(R.id.edtMail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              ParseUser.logInInBackground(edtMail.getText().toString(),
                      edtPassword.getText().toString(), new LogInCallback() {
                          @Override
                          public void done(ParseUser user, ParseException e) {
                              if (user != null && e == null){
                                  FancyToast.makeText(SignIn.this, user.get("username") + " sikeres bejelentkezés",
                                          FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

                                  Intent intent = new Intent(SignIn.this, Hellcome.class);
                                  startActivity(intent);


                              }else {
                                  FancyToast.makeText(SignIn.this, e.getMessage(),
                                          FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                              }
                          }
                      });


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}
