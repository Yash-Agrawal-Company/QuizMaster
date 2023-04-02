package com.yashagrawal.quizmaster;

public class UserHelperClass {
    String Full_Name, User_Name, Email_id, Phone_no, Password;

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getEmail_id() {
        return Email_id;
    }

    public void setEmail_id(String email_id) {
        Email_id = email_id;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserHelperClass(String full_Name, String user_Name, String email_id, String phone_no, String password) {
        Full_Name = full_Name;
        User_Name = user_Name;
        Email_id = email_id;
        Phone_no = phone_no;
        Password = password;

    }
}
