package com.revature.vinson_chin_p0.exceptions;

/**
 * Exception thrown when data that should be unique is already in the database
 *
 */
public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException(String message) {
        super(message);
    }

}
