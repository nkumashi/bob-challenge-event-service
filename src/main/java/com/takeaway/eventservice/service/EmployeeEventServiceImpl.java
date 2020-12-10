package com.takeaway.eventservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.takeaway.eventservice.exception.APIError;
import com.takeaway.eventservice.hateos.assembler.EmployeeEventModelAssembler;
import com.takeaway.eventservice.hateos.model.EmployeeEventModel;
import com.takeaway.eventservice.model.EmployeeEvent;
import com.takeaway.eventservice.repository.EmployeeEventRepository;
import com.takeaway.eventservice.util.ResponseWrapper;

/**
 * @author Naveen Kumashi
 */

@Service
public class EmployeeEventServiceImpl implements EmployeeEventService {
	private EmployeeEventRepository employeeEventRepository;
    private EntityManager entityManager;
    private EmployeeEventModelAssembler employeeEventModelAssembler;
    
    public EmployeeEventServiceImpl(
			EmployeeEventRepository employeeEventRepository,
			EntityManager entityManager,
			EmployeeEventModelAssembler employeeEventModelAssembler
	) {
		this.employeeEventRepository = employeeEventRepository;
		this.entityManager = entityManager;
		this.employeeEventModelAssembler = employeeEventModelAssembler;
	}
    
	/**
	 * Saves the given employee event object to the database
	 * 
	 * @param event
	 * @return The employee event object saved
	 */
	public ResponseWrapper<EmployeeEventModel> postEmployeeEvent(EmployeeEvent event) {																		
		EmployeeEvent addedEvent = employeeEventRepository.save(event);
		return new ResponseWrapper<>(employeeEventModelAssembler.toModel(addedEvent));		
	}
	
	/**
	 * Get all employee events
	 * 
	 * @return List of all employee events found in the database
	 */
	public ResponseWrapper<CollectionModel<EmployeeEventModel>> getAllEmployeeEvents() {
		List<EmployeeEvent> eventList = employeeEventRepository.findAll();
        return new ResponseWrapper<>(employeeEventModelAssembler.toCollectionModel(eventList));
	}
	
	/**
	 * Get all employee events by provided page criteria
	 * 
	 * @return List of all employee events by page found in the database
	 */
	public ResponseWrapper<CollectionModel<EmployeeEventModel>> getPagedEmployeeEvents(Pageable page) {
		List<EmployeeEvent> eventList = new ArrayList<EmployeeEvent>();
		Page<EmployeeEvent> pages = employeeEventRepository.findAll(page);
		eventList = pages.getContent();
        return new ResponseWrapper<>(employeeEventModelAssembler.toCollectionModel(eventList));
	}
	
	/**
	 * Get all employee events by provided limit
	 * 
	 * @return List of all employee events by limit found in the database
	 */
	public ResponseWrapper<CollectionModel<EmployeeEventModel>> getEmployeeEventsByLimit(int limit) {
		List<EmployeeEvent> eventList = entityManager.createQuery("SELECT e FROM EmployeeEvent e ORDER BY e.eventTimestamp",
				EmployeeEvent.class).setMaxResults(limit).getResultList();
        return new ResponseWrapper<>(employeeEventModelAssembler.toCollectionModel(eventList));
	}
	
	/**
	 * Get employee employee event by it's id
	 * 
	 * @param eventId
	 * @return Employee event object if found else error
	 */
	public ResponseWrapper<EmployeeEventModel> getEmployeeEventById(Long eventId) {
		Optional<EmployeeEvent> optional = employeeEventRepository.findById(eventId);
		if(!optional.isPresent()) {
			return new ResponseWrapper<>(new APIError(0, "Error", "Employee Event with ID: " + eventId + " does not exist."));
		}
		return new ResponseWrapper<>(employeeEventModelAssembler.toModel(optional.get()));
	}

	/**
	 * Update an employee event by it's id
	 * 
	 * @param id
	 * @param event
	 * @return Updated employee event object
	 */
	public ResponseWrapper<EmployeeEventModel> putEmployeeEvent(EmployeeEvent event) {
		if(!employeeEventRepository.findById(event.getId()).isPresent()) {
            return new ResponseWrapper<>(new APIError(0, "Error", "Employee event with ID: " + event.getId() + " does not exist"));
        }		
		
        EmployeeEvent addedEvent = employeeEventRepository.save(event);
        return new ResponseWrapper<>(employeeEventModelAssembler.toModel(addedEvent));
	}
	
	/**
	 * Delete an employee event
	 * 
	 * @param employee event object to be deleted
	 */
	public ResponseWrapper<Object> deleteEmployeeEventById(Long eventId) {
		if(!employeeEventRepository.findById(eventId).isPresent()) {
            return new ResponseWrapper<>(new APIError(0, "Error", "Employee event with Id " + eventId + " does not exist"));
        }
		employeeEventRepository.deleteById(eventId);

        if(employeeEventRepository.findById(eventId).isPresent()) {
            return new ResponseWrapper<>(new APIError(0, "Error", "Employee event with Id " + eventId + " was not deleted"));
        }
        return new ResponseWrapper<>("Employee with Id " + eventId + " was deleted");
	}
}
