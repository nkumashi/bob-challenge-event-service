package com.takeaway.eventservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeaway.eventservice.hateos.model.EmployeeEventModel;
import com.takeaway.eventservice.util.ResponseWrapper;
import com.takeaway.eventservice.service.EmployeeEventService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Naveen Kumashi
 */

@RestController
@RequestMapping(value = "/api")
@Api(value = "EmployeeEvent")
public class EmployeeEventController {
	private EmployeeEventService employeeEventService;
	
	public EmployeeEventController(
			EmployeeEventService employeeEventService
	) {
		this.employeeEventService = employeeEventService;	
	}
	
	@ApiOperation(value = "Fetch all employee events")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Processed successfully", response = List.class)
	})
	@GetMapping(value = "/events", produces = { "application/hal+json" })
    public ResponseEntity<Object> getAllEmployeeEvents() {
		ResponseWrapper<CollectionModel<EmployeeEventModel>> response = employeeEventService.getAllEmployeeEvents();
        return new ResponseEntity<>(response.getData(),null, HttpStatus.OK);        
    }
	
	@ApiOperation(value = "View employee event information by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Processed successfully", response = EmployeeEventModel.class),
			@ApiResponse(code = 400, message = "Bad request")
	})
	@GetMapping(value = "/event/{eventId}", produces = { "application/hal+json" })
    public ResponseEntity<Object> getEmployeeEventById(@Valid @PathVariable("eventId") Long eventId) {
		ResponseWrapper<EmployeeEventModel> response = employeeEventService.getEmployeeEventById(eventId);

        return (response.getData() != null) ?
                new ResponseEntity<>(response.getData(), null, HttpStatus.OK) :
                new ResponseEntity<>(response.getError(), null, HttpStatus.BAD_REQUEST);
    }
}
