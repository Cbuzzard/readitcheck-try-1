package com.readitcheck.readitcheck.controllers.exceptions;


public class SubmissionNotFoundException extends RuntimeException{

    public SubmissionNotFoundException(Integer id) {
        super("Could not find submission " + id);
    }

    public SubmissionNotFoundException(String title, String author) {
        super("Could not find submission with title: " + title + ", author: " + author + ".");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
