package com.readitcheck.readitcheck;

import com.readitcheck.readitcheck.models.Commenter;
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
public class CommenterTest {

    private Validator validator;
    private Commenter commenter = new Commenter();

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void emptyUsernameShouldBeInvalid() {
        commenter.setUsername("");
        Set<ConstraintViolation<Commenter>> violations = validator.validate(commenter);
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test
    public void smallUsernameLengthShouldBeInvalid() {
        commenter.setUsername("is");
        Set<ConstraintViolation<Commenter>> violations = validator.validate(commenter);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void largeUsernameLengthShouldBeInvalid() {
        commenter.setUsername("ThisUsernameIsOverTwentyCharactersLong");
        Set<ConstraintViolation<Commenter>> violations = validator.validate(commenter);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void testUsernameLengthShouldBeValid() {
        commenter.setUsername("test");
        Set<ConstraintViolation<Commenter>> violations = validator.validate(commenter);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void oneSubmissionIdShouldBeValid() {
        commenter.setSubmissionId(1);
        Set<ConstraintViolation<Commenter>> violations = validator.validate(commenter);
        assertThat(violations.size()).isEqualTo(1);
    }

}
