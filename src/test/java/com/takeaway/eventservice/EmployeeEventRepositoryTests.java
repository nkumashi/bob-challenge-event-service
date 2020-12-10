package com.takeaway.eventservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.takeaway.eventservice.model.EmployeeEvent;
import com.takeaway.eventservice.repository.EmployeeEventRepository;

/**
 * @author Naveen Kumashi
 */

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class EmployeeEventRepositoryTests {
	@Autowired 
	private EmployeeEventRepository employeeEventRepository;
	
	@Test
	void contextLoads() {
		assertThat(employeeEventRepository).isNotNull();		
	}
	
	@Test
	public void should_find_no_events_if_repository_is_empty() {
		employeeEventRepository.deleteAll();
		Iterable<EmployeeEvent> events = employeeEventRepository.findAll();

		assertThat(events).isEmpty();
	}
	
	@Test
	public void should_find_events() {
		EmployeeEvent event = new EmployeeEvent("Dummy");
		event.setEventTimestamp(LocalDateTime.now());
		EmployeeEvent addedEvent = employeeEventRepository.save(event);
		assertThat(addedEvent).hasFieldOrPropertyWithValue("name", "Dummy");				
		
		Iterable<EmployeeEvent> events = employeeEventRepository.findAll();
		assertThat(events).isNotEmpty();
	}
	
	@Test
	public void should_find_event_by_id() {
		EmployeeEvent event = new EmployeeEvent("Dummy");
		event.setEventTimestamp(LocalDateTime.now());
		EmployeeEvent addedEvent = employeeEventRepository.save(event);
		assertThat(addedEvent).hasFieldOrPropertyWithValue("name", "Dummy");				
		
		Optional<EmployeeEvent> optional = employeeEventRepository.findById(addedEvent.getId());
		assertTrue(optional.isPresent());
	}
	
	@Test
	public void should_create_a_event() {
		EmployeeEvent event = new EmployeeEvent("Dummy");
		event.setEventTimestamp(LocalDateTime.now());
		EmployeeEvent addedEvent = employeeEventRepository.save(event);
		assertThat(addedEvent).hasFieldOrPropertyWithValue("name", "Dummy");
		assertNotNull(addedEvent.getId());		
	}
	
	@Test
	public void should_update_an_event() {
		EmployeeEvent event = new EmployeeEvent("Dummy");
		event.setEventTimestamp(LocalDateTime.now());
		EmployeeEvent addedEvent = employeeEventRepository.save(event);
		assertThat(addedEvent).hasFieldOrPropertyWithValue("name", "Dummy");	
		
		addedEvent.setName("Latest name");
		EmployeeEvent updatedDepartment = employeeEventRepository.save(addedEvent);
		assertThat(updatedDepartment).hasFieldOrPropertyWithValue("name", "Latest name");
	}
	
	@Test
	public void should_delete_an_event() {
		EmployeeEvent event = new EmployeeEvent("Dummy");
		event.setEventTimestamp(LocalDateTime.now());
		EmployeeEvent addedEvent = employeeEventRepository.save(event);
		assertThat(addedEvent).hasFieldOrPropertyWithValue("name", "Dummy");				
		
		employeeEventRepository.delete(addedEvent);
		Optional<EmployeeEvent> optional = employeeEventRepository.findById(event.getId());
		assertTrue(optional.isPresent() == false);
	}
}
