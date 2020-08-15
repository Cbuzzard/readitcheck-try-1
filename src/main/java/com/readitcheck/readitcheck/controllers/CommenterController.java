package com.readitcheck.readitcheck.controllers;

import com.readitcheck.readitcheck.controllers.exceptions.CommenterNotFoundException;
import com.readitcheck.readitcheck.data.CommenterRepository;
import com.readitcheck.readitcheck.models.Commenter;
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

    private final CommenterRepository repository;
    private final CommenterModelAssembler assembler;

    public CommenterController(CommenterRepository repository, CommenterModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("commenter")
    public CollectionModel<EntityModel<Commenter>> all() {

        List<EntityModel<Commenter>> commenters = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(commenters,
                linkTo(methodOn(CommenterController.class).all()).withSelfRel());
    }

    @GetMapping(value = "commenter", params = {"submissionId"})
    public CollectionModel<EntityModel<Commenter>> bySubmissionId(@RequestParam Integer submissionId) {

        List<EntityModel<Commenter>> commenters = repository.findBySubmissionId(submissionId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(commenters,
                linkTo(methodOn(CommenterController.class).all()).withSelfRel());
    }

    @GetMapping("commenter/{id}")
    public EntityModel<Commenter> one(@PathVariable Integer id) {
        Commenter commenter = repository.findById(id).orElseThrow(() -> new CommenterNotFoundException(id));
        return assembler.toModel(commenter);
    }

    @PostMapping("commenter")
    public void newCommenter(@RequestBody Commenter commenter) {
        repository.save(commenter);
    }

}
