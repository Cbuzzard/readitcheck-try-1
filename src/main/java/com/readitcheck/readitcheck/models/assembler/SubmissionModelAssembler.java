package com.readitcheck.readitcheck.models.assembler;

import com.readitcheck.readitcheck.controllers.SubmissionController;
import com.readitcheck.readitcheck.models.Submission;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubmissionModelAssembler implements RepresentationModelAssembler<Submission, EntityModel<Submission>> {

    @Override
    public EntityModel<Submission> toModel(Submission submission) {
        return EntityModel.of(submission,
                linkTo(methodOn(SubmissionController.class).one(submission.getId())).withSelfRel(),
                linkTo(methodOn(SubmissionController.class).all()).withRel("submissions"));
    }
}
