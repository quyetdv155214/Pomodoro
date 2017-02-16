package com.example.quyet.podomoro.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.quyet.podomoro.ultil.Cons;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    ProgressDialog myDialog;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.textInputUsername)
    TextInputLayout textInputUsername;
    @BindView(R.id.textInputPassword)
    TextInputLayout textInputPassword;
    @BindView(R.id.iv_techkid)
    ImageView iv_techkid;
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
        setupUI();
        SharedPrefs.init(this);
        etUsername.requestFocus();

        skipLoginIfPossible();
//

    }

    private void setupUI() {
        myDialog =new ProgressDialog(this);
        myDialog.setMessage("Loading...");
        myDialog.setCancelable(false);
        myDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

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
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                username = etUsername.getText().toString();
                if(checkUsername())
                {
                    textInputUsername.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                password = etPassword.getText().toString();
                if (checkPassword()){
                    textInputPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    /**
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
        Call<RegisterResponseJson> regisCall = registerService.register(registerBody);

        regisCall.enqueue(new Callback<RegisterResponseJson>() {
            @Override
            public void onResponse(Call<RegisterResponseJson> call, Response<RegisterResponseJson> response) {
                myDialog.dismiss();
                if (response.code() == 200){
                    Toast.makeText(LoginActivity.this, Cons.REGISTER_SUCCESS_MESS, Toast.LENGTH_SHORT).show();
                }
                if (response.code() == 400) {
                    textInputUsername.setError(Cons.REGISTER_ACCOUNT_USED_MESS_SHORT);
                    Toast.makeText(LoginActivity.this, Cons.REGISTER_ACCOUNT_USED_MESS_LONG, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseJson> call, Throwable t) {
                myDialog.dismiss();
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
        Toast.makeText(this, Cons.LOGIN_SUCCESS_MESS, Toast.LENGTH_SHORT).show();
        //
        gotoTaskActivity();
    }


    private void sendLogin(final String username, final String password) {
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

                myDialog.dismiss();
                LoginResponseJson loginResponseJson = response.body();
                if (loginResponseJson != null) {
                    if (response.code() == 200) {
                        token = loginResponseJson.getAccessToken();
                        SharedPrefs.getInstance().put(new LoginCredentials(username,password, token));
                        Toast.makeText(LoginActivity.this,Cons.LOGIN_SUCCESS_MESS, Toast.LENGTH_SHORT).show();

                        onLoginSuccess();
                    }
                } else {
                    Log.d(TAG, "onResponse: Could not parse body");
                    Toast.makeText(LoginActivity.this,Cons.LOGIN_WRONG_ACCOUNT_MESS, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                myDialog.dismiss();
                Log.d(TAG, String.format("onFailure: %s", t));
            }
        });
    }

    /**
     *
     */
    private void skipLoginIfPossible() {
        if (SharedPrefs.getInstance().getLoginCredentials().getAccessToken() != null) {
            gotoTaskActivity();
        }
    }

    private void registerAction() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        if (checkUsername() & checkPassword()) {
            myDialog.show();
            sendRegister(username, password);

        }


    }

    private void attemptLogin() {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        if (checkUsername() & checkPassword()) {
            myDialog.show();
            sendLogin(username, password);

        } else {

        }
    }

    private boolean checkPassword() {
        // TODO: 2/11/2017 Check general password
        if (password.isEmpty()) {
            textInputPassword.setError(Cons.PASS_EMPTY_ERROR);
            return false;
        }
        if (password.length() < Cons.LENGTH_OF_PASSwOED) {
            textInputPassword.setError(Cons.PASS_TOO_SHORT_ERROR);
            return false;
        }
        return true;

    }
    private boolean checkUsername() {
        // check general username
        if (username.isEmpty()) {
            textInputUsername.setError(Cons.USERNAME_EMPTY_ERROR);
            return false;
        }
        if (username.length() < Cons.LENGTH_OF_USERNAME) {
            textInputUsername.setError(Cons.USERNAME_TOO_SHORT_ERROR);
            return false;
        }
        Pattern p = Pattern.compile(Cons.USERNAME_REGEX);
        Matcher m = p.matcher(username);
        // boolean b = m.matches();
        if (m.find()) {
            textInputUsername.setError(Cons.HAVE_SPECIAL_CHARACTER_ERROR);
            return false;
        }

        return true;
    }


    private void gotoTaskActivity() {
        Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
}
