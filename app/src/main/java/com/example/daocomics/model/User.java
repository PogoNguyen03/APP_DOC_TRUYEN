package com.example.daocomics.model;

import java.io.Serializable;

public class User implements Serializable {

    String Name;
    String UsName;
    String PhoneNb;
    String Email;

    public String getUsName() {
        return UsName;
    }

    public void setUsName(String usName) {
        UsName = usName;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public String getPhoneNb() {
        return PhoneNb;
    }

    public void setPhoneNb(String phoneNb) {
        PhoneNb = phoneNb;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public User() {
    }

    public User(String name, String usName, String phoneNb, String email) {
        this.UsName = usName;
        this.Name = name;
        this.PhoneNb = phoneNb;
        this.Email = email;
    }
}
