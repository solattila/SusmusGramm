package com.solattila.susmusgramm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    signUp();

                }
                return false;
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();


            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        if (ParseUser.getCurrentUser() != null){
            transitionToSocialMedia();
        }

    }

    private void signUp(){

        if (edtMail.getText().toString().equals("") ||
                edtName.getText().toString().equals("") ||
                edtPassword.getText().toString().equals("")) {
            FancyToast.makeText(SignUp.this, "Email, felhasználónév vagy jelszó hiányzik.",
                    FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
        } else {

            ParseUser appUser = new ParseUser();
            appUser.setEmail(edtMail.getText().toString());
            appUser.setUsername(edtName.getText().toString());
            appUser.setPassword(edtPassword.getText().toString());

            final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
            progressDialog.setMessage("Regisztráció " + edtName.getText().toString());
            progressDialog.show();

            appUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(SignUp.this, "Sikeres Regisztráció",
                                FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

//                        Intent intent = new Intent(SignUp.this, Hellcome.class);
//                        startActivity(intent);
                            transitionToSocialMedia();

                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(),
                                FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                    }

                    progressDialog.dismiss();

                }
            });

        }

    }

    public void layoutTapped(View view){

        try {



        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);}
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void transitionToSocialMedia(){
        Intent intent = new Intent(SignUp.this, SocialMediaAct.class);
        startActivity(intent);
    }

}
