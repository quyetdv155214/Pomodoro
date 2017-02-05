package com.example.quyet.podomoro.settings;

/**
 * Created by quyet on 1/14/2017.
 */

public class LoginCredentials {

    private String username;
    private String password;
    private String accessToken;

    public LoginCredentials(String username, String password, String accessToken) {
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
    }

//    public LoginCredentials(String password, String username) {
//        this.password = password;
//        this.username = username;
//    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
                "accessToken='" + accessToken + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
