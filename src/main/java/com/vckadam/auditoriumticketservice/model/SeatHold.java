package com.vckadam.auditoriumticketservice.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SeatHold serves as a place for holding seats for the customer.
 */
public class SeatHold {

    /**Identifier for each seatHold object.*/
    private final int seatHoldId;

    /**Email address of the customer.*/
    private final String customerEmail;

    /**Time stamp when seats are held by the customer.*/
    private final Date timeStamp;

    /**Identifiers of the seats held by the customer. */
    private Set<Seat> heldSeats;

    /**Constructor for SeatHold class.
     * @param seatHoldNum holds identifier for SeatHold object.
     * @param customerEmailAdd holds email address for customer.
     * @param timeStampOfHeld holds time stamp when seats are held.
     */
    public SeatHold(final int seatHoldNum, final String customerEmailAdd,
                    final Date timeStampOfHeld) {
        this.seatHoldId = seatHoldNum;
        this.customerEmail = customerEmailAdd;
        this.timeStamp = timeStampOfHeld;
        heldSeats = new HashSet<Seat>();
    }

    /**Getter method for seatHoldId.
     * @return a identifier for a SeatHold object.
     */
    public int getSeatHoldId() {
        return seatHoldId;
    }

    /**Getter method for email.
     * @return a email address of customer.
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**Getter method for timeStamp.
     * @return a time stamp when seats are held.
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**Getter method for heldSeats.
     * @return a set of identifiers of the seats held by the customer.
     */
    public Set<Seat> getHeldSeats() {
        return this.heldSeats;
    }

    /**Method to add a seatId in set of seats.
     * @param seat holds the seat.
     */
    public void addSeatIds(final Seat seat) {
        if (!heldSeats.contains(seat)) {
            heldSeats.add(seat);
        }
    }

    /**Method to remove a seat in set of seats.
     * @param seat holds identifier for the seat.
     */
    public void removeSeatIds(final String seat) {
        if (heldSeats.contains(seat)) {
            heldSeats.remove(seat);
        }
    }
}
