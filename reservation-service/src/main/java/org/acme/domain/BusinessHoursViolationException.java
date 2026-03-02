package org.acme.domain;

public class BusinessHoursViolationException extends RuntimeException {
    public BusinessHoursViolationException(String message) {
        super(message);
    }
}
