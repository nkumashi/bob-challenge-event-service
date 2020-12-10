package com.takeaway.eventservice.exception;

import lombok.Data;

/**
 * @author Naveen Kumashi
 */

@Data
public class APIError {

    private int status;
    private String title;
    private String description;

    public APIError(int status, String title, String description) {
        this.status = status;
        this.title = title;
        this.description = description;
    }
}