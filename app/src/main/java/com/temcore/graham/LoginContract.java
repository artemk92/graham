package com.temcore.graham;

public interface LoginContract {
    interface View {
        String getLogin();

        String getPassword();

        void nextActivity();

    }

    interface Presenter {
        void onLoginBtnClick();

        String getToken();
    }

    interface Model {
        void getCookie(String login, String password);
        String getToken();
    }
}
