package com.readitcheck.readitcheck.data;

import com.readitcheck.readitcheck.models.Commenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommenterRepository extends JpaRepository<Commenter, Integer> {

    List<Commenter> findBySubmissionId(Integer submissionId);

}
