package com.takeaway.eventservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Naveen Kumashi
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")
public class EmployeeEvent implements Serializable {
	private static final long serialVersionUID = 5175998636055429389L;

	@Id
	@Column(name = "id_event")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Event name must not be blank")
	@Column(name = "event_name")
	private String name = "";
	
	@NotNull(message = "Event timestamp must not be blank")
	@Column(name = "event_timestamp")
	private LocalDateTime eventTimestamp;
	
	public EmployeeEvent(String name) {
		this.name = name;
	}
	
	@Override
    public boolean equals(Object e) {
        if (this == e) {
        	return true;
        }
        if (!(e instanceof EmployeeEvent )) {
        	return false;
        }
        return id != null && id.equals(((EmployeeEvent) e).getId());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    @Override
    public String toString() {
        return "Event details: Event name: " + this.name;
    }
}

