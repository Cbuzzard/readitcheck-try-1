package com.readitcheck.readitcheck.models;

import com.readitcheck.readitcheck.data.CommenterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
public class CommenterControllerTest {

    @Autowired
    private CommenterRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreationOfCommenter() {
        Commenter commenter = new Commenter("corwinnnn", 1);
        Commenter savedCommenter = repository.save(commenter);
        assertThat(savedCommenter).isNotEqualTo(null);
    }

}
