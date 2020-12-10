package com.takeaway.eventservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.takeaway.eventservice.model.EmployeeEvent;

/**
 * @author Naveen Kumashi
 */

public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, Long> {
	Optional<EmployeeEvent> findById(@Param("id") Long id);
}