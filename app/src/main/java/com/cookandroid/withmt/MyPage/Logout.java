package com.cookandroid.withmt.MyPage;

public class Logout {

    private String userId;

    private String passwd;

    public String getUserId ()
    {
        return userId;
    }

    public String getPasswd ()
    {
        return passwd;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public void setPasswd (String passwd)
    {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "ClassPojo [passwd = " + passwd + ", userId = " + userId + "]";
        }
}
