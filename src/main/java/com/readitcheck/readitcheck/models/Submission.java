package com.readitcheck.readitcheck.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @Size(min = 3, max = 20, message = "Author must be between 3 and 20 characters")
    @NotEmpty(message = "Username cannot be empty")
    private String author;
    @Pattern(regexp = "https?:\\/\\/(www\\.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)", message = "Link needs to be in https://www format")
    @NotEmpty(message = "Link cannot be empty")
    private String linkToArticle;
    @NotEmpty(message = "Question 1 cannot be empty")
    private String questionOne;
    @NotEmpty(message = "Answer 1 cannot be empty")
    private String answerOne;
    @NotEmpty(message = "Question 2 cannot be empty")
    private String questionTwo;
    @NotEmpty(message = "Answer 2 cannot be empty")
    private String answerTwo;
    @OneToMany(mappedBy = "submission")
    private List<Commenter> commenters;

    public Submission(String title, String author, String linkToArticle, String questionOne, String answerOne, String questionTwo, String answerTwo) {
        this.title = title;
        this.author = author;
        this.linkToArticle = linkToArticle;
        this.questionOne = questionOne;
        this.answerOne = answerOne;
        this.questionTwo = questionTwo;
        this.answerTwo = answerTwo;
    }

    public Submission() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLinkToArticle() {
        return linkToArticle;
    }

    public void setLinkToArticle(String linkToArticle) {
        this.linkToArticle = linkToArticle;
    }

    public String getQuestionOne() {
        return questionOne;
    }

    public void setQuestionOne(String questionOne) {
        this.questionOne = questionOne;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getQuestionTwo() {
        return questionTwo;
    }

    public void setQuestionTwo(String questionTwo) {
        this.questionTwo = questionTwo;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }
}
