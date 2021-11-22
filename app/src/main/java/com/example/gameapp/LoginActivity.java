package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnLoginToRegister;
    private TextView tvLoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginToRegister = findViewById(R.id.btnLoginToRegister);
        tvLoginStatus = findViewById(R.id.tvLoginStatus);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "btnLogin: Login Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btnLoginToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "btnLoginToRegister: Register Button");
                goRegisterActivity();
            }
        });

        tvLoginStatus.setVisibility(View.GONE);


    }

    void loginUser(String username, String password) {
        Log.i(TAG, String.format("loginUser: attempting login with %s", username));
        tvLoginStatus.setVisibility(View.GONE);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
//                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                    tvLoginStatus.setText(R.string.loginError);
                    tvLoginStatus.setVisibility(View.VISIBLE);
                    Log.e(TAG, "loginUser: Issue with login", e);
                    return;
                }
                tvLoginStatus.setVisibility(View.GONE);
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, String.format("loginUser: login success with %s", username));
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goRegisterActivity() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }
}