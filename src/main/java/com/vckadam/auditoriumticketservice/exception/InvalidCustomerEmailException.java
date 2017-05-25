package com.vckadam.auditoriumticketservice.exception;

/** class serves as exception when customer email used for
 *  holding seats doesn't match with email used for reservation.
 */
public class InvalidCustomerEmailException extends RuntimeException {
    /**
     * Serial number for the exception.
     */
    private static final long serialVersionUID = 910335327840813423L;

    /** Constructor for the class. */
    public InvalidCustomerEmailException() {
        super();
    }

    /** Constructor with error message.
     * @param msg holds the error message.
     */
    public InvalidCustomerEmailException(final String msg) {
        super(msg);
    }
}
