/**
 * 
 */
package com.example.demo.model.requests;

/**
 * The Class ErrorResponse.
 *
 * @author utkarsh
 */
public class ErrorResponse {
    
    /** The status. */
    private int status;
    
    /** The message. */
    private String message;
    
    /** The time stamp. */
    private long timeStamp;

    /**
     * Gets the status.
     *
     * @return the status
     */
    public int getStatus() {
	return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status to set
     */
    public void setStatus(int status) {
	this.status = status;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * Sets the message.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * Gets the time stamp.
     *
     * @return the timeStamp
     */
    public long getTimeStamp() {
	return timeStamp;
    }

    /**
     * Sets the time stamp.
     *
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(long timeStamp) {
	this.timeStamp = timeStamp;
    }

}
