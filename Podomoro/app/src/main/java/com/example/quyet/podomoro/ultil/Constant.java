package com.example.quyet.podomoro.ultil;

/**
 * Created by quyet on 2/11/2017.
 */

public class Constant {
    public static final int LENGTH_OF_USERNAME = 4;
    public static final int LENGTH_OF_PASSwOED = 4;
    public static final String USERNAME_REGEX = "[^_A-Za-z0-9]";
    public static final String USERNAME_EMPTY_ERROR ="Enter user name !";
    public static final String USERNAME_TOO_SHORT_ERROR ="Username at less "+ LENGTH_OF_USERNAME +" character !";
    public static final String PASS_EMPTY_ERROR="Enter user password !";
    public static final String PASS_TOO_SHORT_ERROR="Password too short. At less "+ LENGTH_OF_PASSwOED + " character !";
    public static final String HAVE_SPECIAL_CHARACTER_ERROR ="Username cannot contain special  !@#$%&*()_+=|<>?{}\\~- ";
    public static final String LOGIN_SUCCESS_MESS ="Logged";
    public static final String LOGIN_WRONG_ACCOUNT_MESS ="Wrong username or password";
    public static final String REGISTER_SUCCESS_MESS ="Registered";
    public static final String REGISTER_ACCOUNT_USED_MESS_LONG ="Register fail, username already used";
    public static final String REGISTER_ACCOUNT_USED_MESS_SHORT ="Username already used";
    public static final String INTERNET_ERROR ="Weak or no internet" ;
    public static final String EXCEPTION_TASK_HAVE_NULL_LOCAL_ID = "Can't delete this task\nThis task have null local id";
}
