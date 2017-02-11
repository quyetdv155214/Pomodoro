package com.example.quyet.podomoro.networks.jsonmodel;

/**
 * Created by quyet on 1/22/2017.
 */

public class RegisterBodyJson {
    private String username;
    private String password;

    public RegisterBodyJson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterBodyJson{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
