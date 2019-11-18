package com.example.clickfiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //UI components declaration
    private EditText edtLoginUpEmail, edtLoginPassword;
    private Button btnLoginbtn, btnSignUpbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtLoginUpEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginbtn = findViewById(R.id.btnLoginbtn);
        btnSignUpbtn = findViewById(R.id.btnSignUpbtn);
        btnLoginbtn.setOnClickListener(this);
        btnSignUpbtn.setOnClickListener(this);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLoginbtn);
                }

                return false;
            }
        });

        if (ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLoginbtn:
                ParseUser.logInInBackground(edtLoginUpEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e==null){
                            FancyToast.makeText(LoginActivity.this,user.getUsername()+" has logged in successfully!", Toast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                            transactionToSocialMediaActivity();
                        } else {
                            FancyToast.makeText(LoginActivity.this," Invalid Username/password", Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        }
                    }
                });
                break;
            case R.id.btnSignUpbtn:
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                break;
        }

    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void transactionToSocialMediaActivity(){
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
