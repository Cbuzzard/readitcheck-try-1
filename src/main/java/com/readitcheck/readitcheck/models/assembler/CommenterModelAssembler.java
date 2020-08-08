package com.readitcheck.readitcheck.models.assembler;

import com.readitcheck.readitcheck.controllers.CommenterController;
import com.readitcheck.readitcheck.models.Commenter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommenterModelAssembler implements RepresentationModelAssembler<Commenter, EntityModel<Commenter>> {

    @Override
    public EntityModel<Commenter> toModel(Commenter commenter) {
        return EntityModel.of(commenter,
                linkTo(methodOn(CommenterController.class).one(commenter.getId())).withSelfRel(),
                linkTo(methodOn(CommenterController.class).all()).withRel("commenters"));
    }
}

