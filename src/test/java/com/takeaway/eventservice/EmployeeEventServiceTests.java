package com.takeaway.eventservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.takeaway.eventservice.hateos.assembler.EmployeeEventModelAssembler;
import com.takeaway.eventservice.hateos.model.EmployeeEventModel;
import com.takeaway.eventservice.model.EmployeeEvent;
import com.takeaway.eventservice.repository.EmployeeEventRepository;
import com.takeaway.eventservice.service.EmployeeEventServiceImpl;
import com.takeaway.eventservice.util.ResponseWrapper;

/**
 * @author Naveen Kumashi
 */

@ExtendWith(MockitoExtension.class)
public class EmployeeEventServiceTests {		
	@Mock
	private EmployeeEventModelAssembler employeeEventModelAssembler;
	
	@Mock 
	private EmployeeEventRepository employeeEventRepository;		
	
	@Mock
	private EmployeeEventModel mockedEmployeeEventModel;
	
	@Mock
    private EmployeeEvent mockedEmployeeEvent;
	
	@InjectMocks
    private EmployeeEventServiceImpl employeeEventService;
	
	@Test
	public void should_create_an_event() {				        
		employeeEventService.postEmployeeEvent(mockedEmployeeEvent);
        verify(employeeEventRepository).save(any());
        verify(employeeEventModelAssembler, times(1)).toModel(any());	
	}
	
	@Test
	public void should_fetch_all_events() {
		List<EmployeeEvent> eventList = new ArrayList<EmployeeEvent>();
		eventList.add(mockedEmployeeEvent);
		when(employeeEventRepository.findAll()).thenReturn(eventList);
		
		employeeEventService.getAllEmployeeEvents();
		verify(employeeEventRepository).findAll();
		verify(employeeEventModelAssembler, times(1)).toCollectionModel(anyList());	
	}
	
	@Test
	public void should_fetch_an_event_by_id() {		
		when(employeeEventRepository.findById(any())).thenReturn(Optional.of(mockedEmployeeEvent));
		when(employeeEventModelAssembler.toModel(any())).thenReturn(mockedEmployeeEventModel);
		
		ResponseWrapper<EmployeeEventModel> response = employeeEventService.getEmployeeEventById(mockedEmployeeEvent.getId());
        assertNotNull(response.getData());
        assertNull(response.getError());
        assertEquals(mockedEmployeeEventModel, response.getData());
	}
	
	@Test
	public void should_update_an_event_by_id() {
        when(employeeEventRepository.findById(anyLong())).thenReturn(Optional.of(mockedEmployeeEvent));
        
        employeeEventService.putEmployeeEvent(mockedEmployeeEvent);
        verify(employeeEventRepository).save(any());
        verify(employeeEventModelAssembler, times(1)).toModel(any());	 
	}
}
