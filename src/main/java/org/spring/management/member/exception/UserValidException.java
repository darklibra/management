package org.spring.management.member.exception;

public class UserValidException extends RuntimeException {
    String field;
    public UserValidException(String field, String message) {
        super(message);
        this.field = field;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    
}
