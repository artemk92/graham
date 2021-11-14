package com.temcore.graham;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private LoginContract.Model mModel;
    private String login;
    private String password;
    private String token;


    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
        this.mModel = new LoginModel();
    }

    @Override
    public void onLoginBtnClick() {
        login = mView.getLogin();
        password = mView.getPassword();
        mModel.getCookie(login, password);
        token = mModel.getToken();
        mView.nextActivity();
    }

    public String getToken() {
        return token;
    }

}
