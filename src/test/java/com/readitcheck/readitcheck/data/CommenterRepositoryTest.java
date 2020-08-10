package com.readitcheck.readitcheck.data;

import com.readitcheck.readitcheck.models.Commenter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class CommenterRepositoryTest {

    @Autowired
    private CommenterRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private static Commenter testCommenter;

    @BeforeAll
    public static void init() {
        testCommenter = new Commenter("TestUsername", 1);
    }

    @BeforeEach
    public void setup() {
        repository.save(testCommenter);
    }

    @Test
    public void shouldFindNothingIfTableIsEmpty() {
        repository.deleteAll();
        Iterable<Commenter> afterDeletionCommenters = repository.findAll();
        assertThat(afterDeletionCommenters).isEmpty();
    }

    @Test
    public void saveCreatesCommenterInDatabase() {
        Commenter commenter = new Commenter("TestUsername", 1);
        Commenter savedCommenter = repository.save(commenter);
        assertThat(savedCommenter).isEqualTo(commenter);
    }

    @Test
    public void findAllShouldHaveSizeOneAfterOneEntry() {
        List<Commenter> commenters = repository.findAll();
        assertThat(commenters).hasSize(1);
    }

    @Test
    public void findBySubmissionIdShouldFindCommenter() {
        List<Commenter> commenters = repository.findBySubmissionId(testCommenter.getSubmissionId());
        assertThat(commenters).hasSize(1).contains(testCommenter);
    }

    @Test
    public void shouldDeleteCommenter() {
        List<Commenter> commenters = repository.findAll();
        assertThat(commenters).hasSize(1);
        repository.delete(testCommenter);
        commenters = repository.findAll();
        assertThat(commenters).hasSize(0);
    }

}
