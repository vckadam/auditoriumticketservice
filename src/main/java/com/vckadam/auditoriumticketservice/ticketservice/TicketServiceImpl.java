package com.vckadam.auditoriumticketservice.ticketservice;

import com.vckadam.auditoriumticketservice.model.SeatHold;

/**
 * SeatHold serves as a central place for providing ticket service
 * functionalities.
 */
public class TicketServiceImpl implements TicketService {

    /**
     * The number of seats in the auditorium that are neither held nor reserved.
     * @return the number of tickets available in the auditorium.
     */
    public int numSeatsAvailable() {
        return 0;
    }

    /**
     * Find and hold the best available seats for a customer.
     * @param numSeats the number of seats to find and hold
     * @param customerEmail unique identifier for the customer
     * @return a SeatHold object identifying the specific seats and related
     * information.
     */
    public SeatHold findAndHoldSeats(final int numSeats,
                                     final String customerEmail) {
        return null;
    }

    /**
     * Commit seats held for a specific customer.
     * @param seatHoldId the seat hold identifier
     * @param customerEmail the email address of the customer to which the
     * seat hold is assigned
     * @return a reservation confirmation code
     */
    public String reserveSeats(final int seatHoldId,
                               final String customerEmail) {
        return null;
    }

}
