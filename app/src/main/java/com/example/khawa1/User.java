package com.example.khawa1;

public class User{
    private String Name ;
    private String Password ;

    public User(String name, String password){
        Name=name ;
        Password=password ;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
