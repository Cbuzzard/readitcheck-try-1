package com.readitcheck.readitcheck.data;

import com.readitcheck.readitcheck.models.Submission;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class SubmissionRepositoryTest {

    @Autowired
    private SubmissionRepository repository;

    public static Submission testSubmission;

    @BeforeAll
    public static void init() {
        testSubmission = new Submission("TestTitle", "TestUser", "http://www.google.com", "q1", "a1", "q2", "a2");
    }

    @BeforeEach
    public void setup() {
        repository.save(testSubmission);
    }

    @Test
    public void shouldFindNothingIfTableIsEmpty() {
        repository.deleteAll();
        Iterable<Submission> submissions = repository.findAll();
        assertThat(submissions).isEmpty();
    }

    @Test
    public void saveCreatesNewSubmission() {
        Submission submission = new Submission("NewTitle", "NewUser", "http://www.google.com", "q1", "a1", "q2", "a2");
        Submission savedSubmission = repository.save(submission);
        assertThat(savedSubmission).isEqualTo(savedSubmission);
    }

    @Test
    public void findAllShouldHaveOneAfterOneEntry() {
        List<Submission> submissions = repository.findAll();
        assertThat(submissions).hasSize(1);
    }

    @Test
    public void findByTitleAndAuthorShouldFindSubmission() {
        Submission submission = repository.findFirstByTitleIgnoreCaseAndAuthorIgnoreCase("testTitle","testUser").orElseThrow();
        assertThat(submission).hasFieldOrPropertyWithValue("title", "TestTitle");
        assertThat(submission).hasFieldOrPropertyWithValue("author", "TestUser");
    }

    @Test
    public void shouldDeleteSubmission() {
        List<Submission> submissions = repository.findAll();
        submissions.forEach(submission -> repository.deleteById(submission.getId()));
        submissions = repository.findAll();
        assertThat(submissions).hasSize(0);
    }


}
