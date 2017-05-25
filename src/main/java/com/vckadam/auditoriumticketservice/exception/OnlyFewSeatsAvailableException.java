package com.vckadam.auditoriumticketservice.exception;

/** class serves as exception when fewer seats are available
 *  than needed seats.
 */
public class OnlyFewSeatsAvailableException extends RuntimeException {

    /**
     * Serial number for the exception.
     */
    private static final long serialVersionUID = 910335327840813421L;

    /** Constructor for the class. */
    public OnlyFewSeatsAvailableException() {
        super();
    }

    /** Constructor with error message.
     * @param msg holds the error message.
     */
    public OnlyFewSeatsAvailableException(final String msg) {
        super(msg);
    }
}
