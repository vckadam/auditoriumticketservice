package com.vckadam.auditoriumticketservice.ticketservice;

import com.vckadam.auditoriumticketservice.model.SeatHold;

/**
 * TicketService serves as a structure for TicketServiceImpl.
 */
public interface TicketService {

    /**
     * The number of seats in the auditorium that are neither held nor reserved.
     * @return the number of tickets available in the auditorium.
     */
    int numSeatsAvailable();
   /**
    * Find and hold the best available seats for a customer.
    * @param numSeats the number of seats to find and hold
    * @param customerEmail unique identifier for the customer
    * @return a SeatHold object identifying the specific seats and related
    * information.
    */

    SeatHold findAndHoldSeats(int numSeats, String customerEmail);
    /**
     * Commit seats held for a specific customer.
     * @param seatHoldId the seat hold identifier
     * @param customerEmail the email address of the customer to which the
     * seat hold is assigned
     * @return a reservation confirmation code
     */

    String reserveSeats(int seatHoldId, String customerEmail);
}
