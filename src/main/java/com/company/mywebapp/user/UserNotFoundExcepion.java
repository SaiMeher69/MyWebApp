package com.company.mywebapp.user;

public class UserNotFoundExcepion extends Throwable {
    public UserNotFoundExcepion(String message) {
        super(message);
    }
}
