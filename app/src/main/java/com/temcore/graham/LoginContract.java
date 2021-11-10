package com.temcore.graham;

public interface LoginContract {
    interface View {
        String getLogin();

        String getPassword();

        void showText(String text);
    }

    interface Presenter {
        void onLoginBtnClick();

        void onDestroy();

    }

    interface Model {
        String login(String login, String password);
    }
}
