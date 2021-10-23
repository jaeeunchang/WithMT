package com.cookandroid.withmt.Login;

import android.content.SharedPreferences;
import android.util.Log;

public class LoginPresenter {
    LoginView loginView;
    LoginModel loginModel;

    boolean result;

    public LoginPresenter(LoginView view){
        this.loginView = view;
        this.loginModel = new LoginModel();
    }

    public void isLogin(String id, String pw) {
        if(loginModel.checkLogin(id,pw)){
            loginView.goToMain();
        }
        else {
            loginView.setEditBg();
        }
    }
}
