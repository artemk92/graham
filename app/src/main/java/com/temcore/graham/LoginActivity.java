package com.temcore.graham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    private EditText login;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onLoginBtnClick();
            }
        });

    }

    @Override
    public void nextActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("token", mPresenter.getToken());
        startActivity(intent);
        finish();
    }

    @Override
    public String getLogin(){
        return login.getText().toString();
    }

    @Override
    public String getPassword(){
        return password.getText().toString();
    }

}