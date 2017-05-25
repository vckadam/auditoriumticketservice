package com.vckadam.auditoriumticketservice.exception;

/** class serves as exception requested number of seats
 *  is invalid.
 */
public class InvalidRequstedNumberOfSeatsException extends
    RuntimeException {
    /**
     * Serial number for the exception.
     */
    private static final long serialVersionUID = 910335327840813422L;

    /** Constructor for the class. */
    public InvalidRequstedNumberOfSeatsException() {
        super();
    }

    /** Constructor with error message.
     * @param msg holds the error message.
     */
    public InvalidRequstedNumberOfSeatsException(final String msg) {
        super(msg);
    }
}
