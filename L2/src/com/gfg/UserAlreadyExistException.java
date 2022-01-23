package com.gfg;

public class UserAlreadyExistException extends Exception{

    private String username;

    public UserAlreadyExistException(String s) {
        super(s);
    }

}
