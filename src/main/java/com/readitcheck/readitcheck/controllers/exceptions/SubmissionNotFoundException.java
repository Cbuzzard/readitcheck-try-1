package com.readitcheck.readitcheck.controllers.exceptions;

public class SubmissionNotFoundException extends RuntimeException{

    public SubmissionNotFoundException(Integer id) {
        super("Could not find submission " + id);
    }

}
