package com.readitcheck.readitcheck.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commenter {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private int submissionId;

    public Commenter(String username, int submissionId) {
        this.username = username;
        this.submissionId = submissionId;
    }

    public Commenter() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }
}
