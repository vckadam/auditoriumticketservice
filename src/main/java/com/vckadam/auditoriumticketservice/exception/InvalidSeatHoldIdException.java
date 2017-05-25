package com.vckadam.auditoriumticketservice.exception;

/** class serves as exception seatHoldId is not present
 *  in the system.
 */
public class InvalidSeatHoldIdException extends RuntimeException {
    /**
     * Serial number for the exception.
     */
    private static final long serialVersionUID = 910335327840813422L;

    /** Constructor for the class. */
    public InvalidSeatHoldIdException() {
        super();
    }

    /** Constructor with error message.
     * @param msg holds the error message.
     */
    public InvalidSeatHoldIdException(final String msg) {
        super(msg);
    }
}
