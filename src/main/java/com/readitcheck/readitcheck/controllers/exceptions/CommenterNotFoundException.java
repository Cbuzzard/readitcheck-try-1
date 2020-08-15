package com.readitcheck.readitcheck.controllers.exceptions;

public class CommenterNotFoundException extends RuntimeException {

    public CommenterNotFoundException(Integer id) {
        super("Could not find commenter " + id);
    }

}
