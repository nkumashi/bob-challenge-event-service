package com.takeaway.eventservice.service;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

import com.takeaway.eventservice.hateos.model.EmployeeEventModel;
import com.takeaway.eventservice.model.EmployeeEvent;
import com.takeaway.eventservice.util.ResponseWrapper;

public interface EmployeeEventService {
	/**
	 * Saves the given employee event object to the database
	 * 
	 * @param event
	 * @return The employee event object saved
	 */
	public ResponseWrapper<EmployeeEventModel> postEmployeeEvent(EmployeeEvent event);
	
	/**
	 * Get all employee events
	 * 
	 * @return List of all employee events found in the database
	 */
	public ResponseWrapper<CollectionModel<EmployeeEventModel>> getAllEmployeeEvents();
	
	/**
	 * Get all employee events by provided page criteria
	 * 
	 * @return List of all employee events by page found in the database
	 */
	public ResponseWrapper<CollectionModel<EmployeeEventModel>> getPagedEmployeeEvents(Pageable page);
	
	/**
	 * Get all employee events by provided limit
	 * 
	 * @return List of all employee events by limit found in the database
	 */
	public ResponseWrapper<CollectionModel<EmployeeEventModel>> getEmployeeEventsByLimit(int limit);
	
	/**
	 * Get employee employee event by it's id
	 * 
	 * @param eventId
	 * @return Employee event object if found else error
	 */
	public ResponseWrapper<EmployeeEventModel> getEmployeeEventById(Long eventId);
	
	/**
	 * Update an employee event by it's id
	 * 
	 * @param id
	 * @param event
	 * @return Updated employee event object
	 */
	public ResponseWrapper<EmployeeEventModel> putEmployeeEvent(EmployeeEvent event);
	
	/**
	 * Delete an employee event
	 * 
	 * @param employee event object to be deleted
	 */
	public ResponseWrapper<Object> deleteEmployeeEventById(Long eventId);
}
