package com.takeaway.eventservice.hateos.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.takeaway.eventservice.controller.EmployeeEventController;
import com.takeaway.eventservice.hateos.model.EmployeeEventModel;
import com.takeaway.eventservice.model.EmployeeEvent;

/**
 * @author Naveen Kumashi
 */

@Component
public class EmployeeEventModelAssembler extends RepresentationModelAssemblerSupport<EmployeeEvent, EmployeeEventModel> {
	public EmployeeEventModelAssembler() {
        super(EmployeeEventModelAssembler.class, EmployeeEventModel.class);
    }

	@Override
	public EmployeeEventModel toModel(EmployeeEvent entity) {
		EmployeeEventModel eventModel = instantiateModel(entity);
		eventModel.add(linkTo(
                methodOn(EmployeeEventController.class)
                .getEmployeeEventById(entity.getId()))
                .withSelfRel());
							       
		eventModel.setEventId(entity.getId());
		eventModel.setEventName(entity.getName());		

        return eventModel;
	}
	
	@Override
    public CollectionModel<EmployeeEventModel> toCollectionModel(Iterable<? extends EmployeeEvent> entities) {
        CollectionModel<EmployeeEventModel> eventModels = super.toCollectionModel(entities);         
        eventModels.add(linkTo(methodOn(EmployeeEventController.class).getAllEmployeeEvents()).withSelfRel());         
        return eventModels;
    }
}
