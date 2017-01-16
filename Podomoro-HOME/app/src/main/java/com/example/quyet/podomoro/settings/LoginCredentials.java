package com.example.quyet.podomoro.settings;

/**
 * Created by quyet on 1/14/2017.
 */

public class LoginCredentials {

    private String username;
    private String password;

    public LoginCredentials(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
