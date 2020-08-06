package com.readitcheck.readitcheck.controllers;

import com.readitcheck.readitcheck.data.SubmissionRepository;
import com.readitcheck.readitcheck.models.Submission;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("submission")
public class SubmissionController {

    private final SubmissionRepository repository;

    public SubmissionController(SubmissionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Submission> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Submission one(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    Submission newSubmission(@RequestBody Submission submission) {
        return repository.save(submission);
    }

}
