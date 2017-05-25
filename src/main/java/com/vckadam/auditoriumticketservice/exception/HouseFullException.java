package com.vckadam.auditoriumticketservice.exception;

/** class serves as exception when house is full. */
public class HouseFullException extends RuntimeException {

    /**
     * Serial number for the exception.
     */
    private static final long serialVersionUID = 910335327840813420L;

    /** Constructor for the class. */
    public HouseFullException() {
        super();
    }

    /** Constructor with error message.
     * @param msg holds the error message.
     */
    public HouseFullException(final String msg) {
        super(msg);
    }
}
