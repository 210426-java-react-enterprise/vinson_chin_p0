package com.revature.vinson_chin_p0.exceptions;

/**
 * Exception thrown when object is not in the right format
 */
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }

}
