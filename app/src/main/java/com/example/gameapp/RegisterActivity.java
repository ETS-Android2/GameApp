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
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = "RegisterActivity";

    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private TextView tvRegistrationStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvRegistrationStatus = findViewById(R.id.tvRegistrationStatus);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "btnLogin: Login Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                registerUser(username, password, confirmPassword);
            }
        });

        tvRegistrationStatus.setVisibility(View.GONE);


    }

    private void registerUser(String username, String password, String confirmPassword) {
        Log.i(TAG, String.format("loginUser: attempting login with %s", username));
        tvRegistrationStatus.setVisibility(View.GONE);

        if (!password.equals(confirmPassword)) {
            // passwords don't match
            tvRegistrationStatus.setText(R.string.RegisterPasswordsDontMatch);
            tvRegistrationStatus.setVisibility(View.VISIBLE);
            return;
        }

        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    tvRegistrationStatus.setVisibility(View.GONE);
                    // Hooray! Let them use the app now.
//                    Toast.makeText(LoginActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, String.format("registerUser: registerUser success with %s", username));
                    loginUser(username, password);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
//                    Toast.makeText(LoginActivity.this, "Issue with registering", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "registerUser: Issue with registering", e);
                    tvRegistrationStatus.setText(e.getMessage());
                    tvRegistrationStatus.setVisibility(View.VISIBLE);
                    return;
                }
            }
        });
    }

    void loginUser(String username, String password) {
        Log.i(TAG, String.format("loginUser: attempting login with %s", username));

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
//                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "loginUser: Issue with login", e);
                    return;
                }

                goMainActivity();
                Toast.makeText(RegisterActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                Log.i(TAG, String.format("loginUser: login success with %s", username));
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}