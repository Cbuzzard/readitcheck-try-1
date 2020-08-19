package com.readitcheck.readitcheck.models;

public class CreateCommenterRequest {

    String username;
    int submission_id;

    public CreateCommenterRequest(String username, int submission_id) {
        this.username = username;
        this.submission_id = submission_id;
    }

    public CreateCommenterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(int submission_id) {
        this.submission_id = submission_id;
    }
}
