package com.readitcheck.readitcheck.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Commenter {



    @Id
    @GeneratedValue
    private int id;
    @Size(min = 3, max = 20, message = "Author must be between 3 and 20 characters")
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "submission_id", referencedColumnName = "id")
    private Submission submission;


    public Commenter(String username) {
        this.username = username;
    }

    public Commenter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
