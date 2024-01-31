package com.moh.mfl.response;

import java.util.List;

/**
 *
 * @author Francis Chulu
 * @version 1.0
 */
public class AuthResponse {

    private Boolean success;
    private String message;
    private Object data;
    private Object errors;

    public AuthResponse(Boolean success, String message, Object data, Object errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the errors
     */
    public Object getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
