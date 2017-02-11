package com.example.quyet.podomoro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quyet.podomoro.R;
import com.example.quyet.podomoro.networks.jsonmodel.LoginBodyJson;
import com.example.quyet.podomoro.networks.jsonmodel.LoginResponseJson;
import com.example.quyet.podomoro.networks.jsonmodel.RegisterBodyJson;
import com.example.quyet.podomoro.networks.jsonmodel.RegisterResponseJson;
import com.example.quyet.podomoro.networks.services.LoginService;
import com.example.quyet.podomoro.networks.services.RegisterService;
import com.example.quyet.podomoro.settings.LoginCredentials;
import com.example.quyet.podomoro.settings.SharedPrefs;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.toString();
    @BindView(R.id.et_username )EditText etUsername;
    @BindView(R.id.et_password ) EditText etPassword;
    @BindView(R.id.bt_login ) Button btLogin;
    @BindView(R.id.bt_register ) Button btRegister;
    private Retrofit retrofit;
    private String username;
    private String password;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        addListener();
        SharedPrefs.init(this);

    }

    private void addListener() {
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


    /**
     *
     * @param username
     * @param password
     */
    private void sendRegister(String username, String password) {
        // create retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // create service
        RegisterService registerService = retrofit.create(RegisterService.class);

        MediaType jsonType = MediaType.parse("application/json");

        String registerJson = (new Gson().toJson(new RegisterBodyJson(username, password)));

        final RequestBody registerBody = RequestBody.create(jsonType, registerJson);
        // Create Call
        Call<RegisterResponseJson> regisCall =registerService.register(registerBody);

        regisCall.enqueue(new Callback<RegisterResponseJson>() {
            @Override
            public void onResponse(Call<RegisterResponseJson> call, Response<RegisterResponseJson> response) {

//                Toast.makeText(LoginActivity.this, String.format("onResponse code : %s, message : %s",
//                        response.code(), response.message()), Toast.LENGTH_LONG).show();
                RegisterResponseJson registerResponseJson = response.body();
                if (response.code() == 200) {
                    Toast.makeText(LoginActivity.this, "Registed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, String.format(String.format("onResponse,  register success " +
                            "\n token %s", registerResponseJson.getAccessToken())));

                }
                if (response.code() == 400) {
                    Log.d(TAG, String.format(String.format("onResponse,  register fail, username " +
                            "already used , code %s", response.code())));
                    Toast.makeText(LoginActivity.this, "register fail, username " +
                            "                            already used ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseJson> call, Throwable t) {
                Toast.makeText(LoginActivity.this, String.format("onFailure cause : %s, message : %s",
                        t.getCause(), t.getMessage()), Toast.LENGTH_LONG).show();
                Log.d(TAG, String.format(String.format("onFailure,  register fail " +
                        "message : %s ", t.getMessage())));
            }
        });
    }
    private void onLoginSuccess() {
        // put login
        SharedPrefs.getInstance().put(new LoginCredentials(username, password, token));
        //
        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
        //
        gotoTaskActivity();
    }


    private void sendLogin(String username, String password) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // create service
        LoginService loginService = retrofit.create(LoginService.class);
        //data & format
        //format --> mediatype
        //date --. json

        MediaType jsonType = MediaType.parse("application/json");
        String loginJson = (new Gson().toJson(new LoginBodyJson(username, password)));
        final RequestBody loginBody = RequestBody.create(jsonType, loginJson);
        // create call
        Call<LoginResponseJson> loginCall = loginService.login(loginBody);



        loginCall.enqueue(new Callback<LoginResponseJson>() {
            @Override
            public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                LoginResponseJson loginResponseJson = response.body();
                if (loginResponseJson != null) {
                    if (response.code() == 200) {
                        token = loginResponseJson.getAccessToken();
                        onLoginSuccess();
                    }
                } else {
                    Log.d(TAG, "onResponse: Could not parse body");
                    Toast.makeText(LoginActivity.this, "Username or password wrong ! ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                Log.d(TAG, String.format("onFailure: %s", t));
            }
        });
    }

    /**
     *
     */
    private void skipLoginIfPosible() {
        if (SharedPrefs.getInstance().getLoginCredentials().getAccessToken() != null) {
            gotoTaskActivity();
        }
    }

    private void registerAction() {

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();


        if (username.equals("") || username == null) {
            Toast.makeText(this, "username cannot be null", Toast.LENGTH_SHORT).show();
        } else if (password.equals("") || password == null) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        } else {
            sendRegister(username, password);
        }


    }

    private void attemptLogin() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        sendLogin(username, password);

    }

    private void gotoTaskActivity() {
        Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
}
