package com.takeaway.eventservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.takeaway.challenge.vo.EmployeeEventVO;
import com.takeaway.eventservice.constants.AppConstants;
import com.takeaway.eventservice.model.EmployeeEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Naveen Kumashi
 */

@Slf4j
@Service
public class KafkaConsumerService {
	private EmployeeEventService employeeEventService;
	
	public KafkaConsumerService(EmployeeEventService employeeEventService) {
		this.employeeEventService = employeeEventService;
	}
	
    @KafkaListener(topics = AppConstants.TOPIC_NAME, 
            groupId = AppConstants.GROUP_ID)
    public void consume(@Payload EmployeeEventVO data, @Headers MessageHeaders headers) {
        log.info(String.format("Message recieved -> %s", data.getEventName()));
        log.info(String.format("Message recieved -> %s", data.getEventTimestamp()));
        headers.keySet().forEach(key -> {
            log.info("Message header: value : {}: {}", key, headers.get(key));
        });
                
        EmployeeEvent entity = new EmployeeEvent();
        entity.setName(data.getEventName());
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(data.getEventTimestamp(), formatter);
        entity.setEventTimestamp(dateTime);
        employeeEventService.postEmployeeEvent(entity);
    }
}