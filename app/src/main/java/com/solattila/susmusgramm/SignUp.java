package com.solattila.susmusgramm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText edtName;
    private EditText edtMail;
    private EditText edtPassword;
    private Button btnSignUp;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Regisztráció");

        edtMail = findViewById(R.id.edtMail);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser appUser = new ParseUser();
                appUser.setEmail(edtMail.getText().toString());
                appUser.setUsername(edtName.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null){

                            FancyToast.makeText(SignUp.this, "Sikeres Regisztráció",
                                    FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

                            Intent intent = new Intent(SignUp.this, Hellcome.class);
                            startActivity(intent);

                        }else {
                            FancyToast.makeText(SignUp.this, e.getMessage(),
                                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                        }

                    }
                });

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

    }
}
