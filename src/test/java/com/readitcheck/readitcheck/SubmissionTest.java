package com.readitcheck.readitcheck;

import com.readitcheck.readitcheck.models.Submission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class SubmissionTest {

    private Validator validator;
    private Submission submission = new Submission();

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void emptyTitleShouldBeInvalid() {
        submission.setTitle("");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void testTitleShouldBeValid() {
        submission.setTitle("test");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void emptyAuthorShouldBeInvalid() {
        submission.setAuthor("");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(8);
    }

    @Test
    public void smallAuthorLengthShouldBeInvalid() {
        submission.setAuthor("is");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void largeAuthorLengthShouldBeInvalid() {
        submission.setAuthor("ThisUsernameIsOverTwentyCharactersLong");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void testAuthorLengthShouldBeValid() {
        submission.setAuthor("test");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void properHttpLinkShouldBeValid() {
        submission.setLinkToArticle("http://www.google.com");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void properHttpsLinkShouldBeValid() {
        submission.setLinkToArticle("https://www.google.com");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void otherDotEndingLinksShouldBeValid() {
        submission.setLinkToArticle("http://www.google.org");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void justDotEndingLinksShouldBeInvalid() {
        submission.setLinkToArticle("http://www.google.");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void noHttpLinkShouldBeInvalid() {
        submission.setLinkToArticle("www.google.com");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void noWwwLinkShouldBeInvalid() {
        submission.setLinkToArticle("http://google.com");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void noHttpOrWwwLinkShouldBeInvalid() {
        submission.setLinkToArticle("google.com");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void noDotLinkShouldBeInvalid() {
        submission.setLinkToArticle("http://www.google");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void emptyQuestionOneShouldBeInvalid() {
        submission.setQuestionOne("");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void testQuestionOneShouldBeValid() {
        submission.setQuestionOne("test");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void emptyQuestionTwoShouldBeInvalid() {
        submission.setQuestionTwo("");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void testQuestionTwoShouldBeValid() {
        submission.setQuestionTwo("test");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void emptyAnswerOneShouldBeInvalid() {
        submission.setAnswerOne("");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void testAnswerOneShouldBeValid() {
        submission.setAnswerOne("test");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

    @Test
    public void emptyAnswerTwoShouldBeInvalid() {
        submission.setAnswerTwo("");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(7);
    }

    @Test
    public void testAnswerTwoShouldBeValid() {
        submission.setAnswerTwo("test");
        Set<ConstraintViolation<Submission>> violations = validator.validate(submission);
        assertThat(violations.size()).isEqualTo(6);
    }

}
