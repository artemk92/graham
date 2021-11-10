package com.temcore.graham;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private LoginContract.Model mModel;
    private String login;
    private String password;


    public LoginPresenter(LoginContract.View mView){
        this.mView = mView;
        this.mModel = new LoginModel();
    }

    @Override
    public void onLoginBtnClick(){
        login = mView.getLogin();
        password = mView.getPassword();
        mView.showText(mModel.login(login, password)); 
    }

    @Override
    public void onDestroy() {

    }
}
