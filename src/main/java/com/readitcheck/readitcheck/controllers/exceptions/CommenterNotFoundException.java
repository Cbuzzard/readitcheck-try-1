package com.readitcheck.readitcheck.controllers.exceptions;

public class CommenterNotFoundException extends RuntimeException {

    CommenterNotFoundException(Integer id) {
        super("Could not find commenter " + id);
    }

}
