package com.maxvalue.codefolks.firebasesampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maxvalue.codefolks.firebasesampleapp.authentication.EmailPasswordActivity;
import com.maxvalue.codefolks.firebasesampleapp.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private EditText edtUsername, edtPassword;
    private Button btnSignup;
    private User userCheckModels;
    private String CheckinID;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initialize();
        firebaseAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(this);
    }

    private void initialize() {


        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignup = findViewById(R.id.btnSignup);
        progressBar = findViewById(R.id.progressOne);
        findViewById(R.id.link_login).setOnClickListener(this);
    }

    public void addCustomer(User user) {

        EmailPasswordActivity epassword = new EmailPasswordActivity();
        epassword.createAccount(user.getUserName(), user.getUserPassword());
        progressBar.setVisibility(View.GONE);
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
        if (v.getId() == R.id.btnSignup) {
            progressBar.setVisibility(v.VISIBLE);
            if (validateData()) {
                userCheckModels = new User(edtUsername.getText().toString(), edtPassword.getText().toString());

                addCustomer(userCheckModels);
            } else {

            }
        } else if (v.getId() == R.id.link_login) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {

        }
    }
}
