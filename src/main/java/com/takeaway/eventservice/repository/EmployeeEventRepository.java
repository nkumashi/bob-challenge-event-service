package com.takeaway.eventservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.takeaway.eventservice.model.EmployeeEvent;

/**
 * @author Naveen Kumashi
 */

public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, Long> {
	Optional<EmployeeEvent> findById(@Param("id") Long id);
	List<EmployeeEvent> findAllByOrderByEventTimestampAsc();
	Page<EmployeeEvent> findAllByOrderByEventTimestampAsc(Pageable page);
}