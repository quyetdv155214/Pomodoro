package com.example.quyet.podomoro.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by quyet on 2/24/2017.
 */

public class DeleteResponseJSon {


    @SerializedName("message")
    public String message;
    @SerializedName("code")
    public int code;

    public DeleteResponseJSon(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
