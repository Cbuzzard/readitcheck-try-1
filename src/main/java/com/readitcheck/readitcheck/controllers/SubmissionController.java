package com.readitcheck.readitcheck.controllers;

import com.readitcheck.readitcheck.data.SubmissionRepository;
import com.readitcheck.readitcheck.models.Submission;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class SubmissionController {

    private final SubmissionRepository repository;

    public SubmissionController(SubmissionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("submission")
    public List<Submission> all(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        return title!=null && author!=null ? repository.findByTitleAndAuthorIgnoreCase(title, author) : repository.findAll();
    }

    //TODO fix or else throw

    @GetMapping("submission/{id}")
    public Submission one(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("submission")
    public Submission newSubmission(@RequestBody Submission submission) {
        return repository.save(submission);
    }

}
