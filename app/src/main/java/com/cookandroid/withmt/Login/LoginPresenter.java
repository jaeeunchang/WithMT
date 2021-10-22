package com.cookandroid.withmt.Login;

public class LoginPresenter {
    LoginView loginView;
    LoginModel loginModel;

    public LoginPresenter(LoginView view){
        this.loginView = view;
        this.loginModel = new LoginModel();
    }

    public void isLogin(String id, String pw) {
        if (loginModel.checkLogin(id, pw)){
            loginView.goToMain();
        }
        else {
            loginView.setEditBg();
        }
    }
}
