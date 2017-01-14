package com.example.quyet.podomoro.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quyet.podomoro.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) this.findViewById(R.id.et_username); 
        etPassword = (EditText) this.findViewById(R.id.et_password);
        btLogin = (Button) this.findViewById(R.id.bt_login);
        btRegister = (Button) this.findViewById(R.id.bt_register);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAction();
            }
        });

    }

    private void registerAction() {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (username.equals("admin"))   {
            Toast.makeText(this, "username already exist, chosse other !", Toast.LENGTH_SHORT).show();
        }
        else if(username.equals("") || username == null){
            Toast.makeText(this, "username cannot be null", Toast.LENGTH_SHORT).show();
        }else if(password.equals("") || password == null)
        {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "register success !", Toast.LENGTH_SHORT).show();
        }



    }

    private void attemptLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (username.equals("admin") && password.equals("admin"))
        {
            //notification
            Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Wrong Username or password", Toast.LENGTH_SHORT).show();
        }



    }
}
