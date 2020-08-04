package com.readitcheck.readitcheck.data;

import com.readitcheck.readitcheck.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}
