package com.readitcheck.readitcheck.controllers;

import com.readitcheck.readitcheck.controllers.exceptions.CommenterNotFoundException;
import com.readitcheck.readitcheck.controllers.exceptions.SubmissionNotFoundException;
import com.readitcheck.readitcheck.data.CommenterRepository;
import com.readitcheck.readitcheck.data.SubmissionRepository;
import com.readitcheck.readitcheck.models.Commenter;
import com.readitcheck.readitcheck.models.CreateCommenterRequest;
import com.readitcheck.readitcheck.models.Submission;
import com.readitcheck.readitcheck.models.assembler.CommenterModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController()
public class CommenterController {

    private final CommenterRepository commenterRepository;
    private final SubmissionRepository submissionRepository;
    private final CommenterModelAssembler assembler;

    public CommenterController(CommenterRepository commenterRepository, SubmissionRepository submissionRepository, CommenterModelAssembler assembler) {
        this.commenterRepository = commenterRepository;
        this.submissionRepository = submissionRepository;
        this.assembler = assembler;
    }

    @GetMapping("commenter")
    public CollectionModel<EntityModel<Commenter>> all() {

        List<EntityModel<Commenter>> commenters = commenterRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(commenters,
                linkTo(methodOn(CommenterController.class).all()).withSelfRel());
    }

    @GetMapping(value = "commenter", params = {"submissionId"})
    public CollectionModel<EntityModel<Commenter>> bySubmissionId(@RequestParam Integer submissionId) {

        List<EntityModel<Commenter>> commenters = commenterRepository.findBySubmissionId(submissionId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(commenters,
                linkTo(methodOn(CommenterController.class).all()).withSelfRel());
    }

    @GetMapping("commenter/{id}")
    public EntityModel<Commenter> one(@PathVariable Integer id) {
        Commenter commenter = commenterRepository.findById(id).orElseThrow(() -> new CommenterNotFoundException(id));
        return assembler.toModel(commenter);
    }

    @PostMapping("commenter")
    public void newCommenter(@RequestBody CreateCommenterRequest commenterRequest) {
        Commenter commenter = new Commenter();
        commenter.setUsername(commenterRequest.getUsername());
        Submission submission = submissionRepository.findById(commenterRequest.getSubmission_id()).orElseThrow(
                () -> new SubmissionNotFoundException(commenterRequest.getSubmission_id())
        );
        commenter.setSubmission(submission);
        commenterRepository.save(commenter);
    }

}
