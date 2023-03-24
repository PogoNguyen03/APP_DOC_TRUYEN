package com.example.app_truyen_tranh.model;
import java.io.Serializable;


public class User implements Serializable {
    private String LogName;
    private String LogPass;
    private String ConfirmPass;
    private String CusEmail;
    private String CusPhone;
    private int sex;

        public User(String logName, String logPass, String confirmPass, String cusEmail, String cusPhone, int sex) {
            this.LogName = logName;
            this.LogPass = logPass;
            this.ConfirmPass = confirmPass;
            this.CusEmail = cusEmail;
            this.CusPhone = cusPhone;
            this.sex = sex;
        }
    public String getLogName() {
        return LogName;
    }
    public void setLogName(String logName) {
        LogName = logName;
    }

    public String getLogPass() {
        return LogPass;
    }
    public void setLogPass(String logPass) {
        LogPass = logPass;
    }

    public String getConfirmPass() {
        return ConfirmPass;
    }
    public void setConfirmPass(String confirmPass) {
        ConfirmPass = confirmPass;
    }

    public String getCusEmail() {
        return CusEmail;
    }
    public void setCusEmail(String cusEmail) {
        CusEmail = cusEmail;
    }

    public String getCusPhone() {
        return CusPhone;
    }
    public void setCusPhone(String cusPhone) {
        CusPhone = cusPhone;
    }

    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
}
