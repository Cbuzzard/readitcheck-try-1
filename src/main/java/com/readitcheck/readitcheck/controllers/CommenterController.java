package com.readitcheck.readitcheck.controllers;

import com.readitcheck.readitcheck.data.CommenterRepository;
import com.readitcheck.readitcheck.models.Commenter;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
public class CommenterController {

    private final CommenterRepository repository;

    public CommenterController(CommenterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("commenter")
    public List<Commenter> all(@RequestParam(required = false) Integer submissionId) {
        return submissionId != null ? repository.findBySubmissionId(submissionId) : repository.findAll();
    }

    //TODO fix or else throw

    @GetMapping("commenter/{id}")
    public Commenter one(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("commenter")
    public void newCommenter(@RequestBody Commenter commenter) {
        repository.save(commenter);
    }

}
