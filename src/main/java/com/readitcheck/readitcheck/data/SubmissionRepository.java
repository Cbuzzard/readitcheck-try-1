package com.readitcheck.readitcheck.data;

import com.readitcheck.readitcheck.models.Commenter;
import com.readitcheck.readitcheck.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

    Optional<Submission> findFirstByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);

}
