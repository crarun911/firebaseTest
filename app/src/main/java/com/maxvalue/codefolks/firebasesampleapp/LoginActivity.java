package com.maxvalue.codefolks.firebasesampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maxvalue.codefolks.firebasesampleapp.CustomerHome;

import com.maxvalue.codefolks.firebasesampleapp.MainActivity;
import com.maxvalue.codefolks.firebasesampleapp.R;
import com.maxvalue.codefolks.firebasesampleapp.authentication.EmailPasswordActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView tvlinkLogin;
    FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        mAuth = FirebaseAuth.getInstance();
    }

    private void initialize() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        findViewById(R.id.link_login).setOnClickListener(this);
        findViewById(R.id.btnLogin).setOnClickListener(this);

    }

    private boolean validateData() {
        String email = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty()) {
            edtUsername.setError("Enter valid email");
            edtUsername.setFocusable(true);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtUsername.setError("Enter valid email");
            edtUsername.setFocusable(true);
            return false;
        } else if (password.isEmpty()) {
            edtPassword.setError("Enter valid password");
            edtPassword.setFocusable(true);
            return false;
        } else if (password.length() <= 6) {
            edtPassword.setError("Minimum charcter should be 6");
            edtPassword.setFocusable(true);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                break;
            case R.id.link_login:
                break;
        }
        if (v.getId() == R.id.btnLogin) {
            EmailPasswordActivity emailPasswordActivity = new EmailPasswordActivity();
            emailPasswordActivity.signIn(edtUsername.getText().toString(), edtPassword.getText().toString());
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Toast.makeText(this, "current user" + currentUser.getDisplayName(), Toast.LENGTH_SHORT).show();

//            Intent i =new Intent(this, CustomerHome.class);
//            startActivity(i);
//            finish();
        } else if (v.getId() == R.id.link_login) {

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();

        } else {

        }

    }
}
