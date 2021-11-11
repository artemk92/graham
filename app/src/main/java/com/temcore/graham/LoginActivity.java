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
    private TextView test;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter(this);

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        test = (TextView) findViewById(R.id.test);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onLoginBtnClick();
            }
        });

    }

    @Override
    public String getLogin(){
        String l = login.toString();
        return l;
    }

    @Override
    public String getPassword(){
        String p = password.toString();
        return p;
    }

    @Override
    public void showText(String text) {
        this.text = text;
        test.setText(text);
    }
}