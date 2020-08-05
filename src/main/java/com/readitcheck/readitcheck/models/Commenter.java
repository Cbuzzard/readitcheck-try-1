package com.readitcheck.readitcheck.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Commenter {

    @Id
    @GeneratedValue
    private int id;
    @Size(min = 3, max = 20, message = "Author must be between 3 and 20 characters")
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @Min(1)
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
