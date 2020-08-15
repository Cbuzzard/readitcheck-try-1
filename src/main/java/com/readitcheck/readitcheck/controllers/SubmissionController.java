package com.readitcheck.readitcheck.controllers;

import com.readitcheck.readitcheck.controllers.exceptions.SubmissionNotFoundException;
import com.readitcheck.readitcheck.data.SubmissionRepository;
import com.readitcheck.readitcheck.models.Submission;
import com.readitcheck.readitcheck.models.assembler.SubmissionModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
public class SubmissionController {

    private final SubmissionRepository repository;
    private final SubmissionModelAssembler assembler;

    public SubmissionController(SubmissionRepository repository, SubmissionModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("submission")
    public CollectionModel<EntityModel<Submission>> all() {

        List<EntityModel<Submission>> submissions = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(submissions,
                linkTo(methodOn(SubmissionController.class).all()).withSelfRel());
    }

    @GetMapping(value = "submission", params = {"title", "author"})
    public EntityModel<Submission> byTitleAndAuthor(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        Submission submission = repository.findFirstByTitleIgnoreCaseAndAuthorIgnoreCase(title, author);
        return assembler.toModel(submission);
    }

    @GetMapping("submission/{id}")
    public EntityModel<Submission> one(@PathVariable Integer id) {
        Submission submission = repository.findById(id).orElseThrow(() -> new SubmissionNotFoundException(id));
        return assembler.toModel(submission);
    }

    @PostMapping("submission")
    public Submission newSubmission(@RequestBody Submission submission) {
        return repository.save(submission);
    }

}
