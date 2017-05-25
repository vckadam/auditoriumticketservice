package com.vckadam.auditoriumticketservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    /**list of the seats held by the customer. */
    private List<Seat> heldSeats;

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
        heldSeats = new ArrayList<Seat>();
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
    public List<Seat> getHeldSeats() {
        return this.heldSeats;
    }

    /**Getter method for heldSeats.
     * @param seats holds a set of held seats.
     */
    public void setHeldSeats(final List<Seat> seats) {
         this.heldSeats = seats;
    }

  /*  *//**Method to add a seatId in set of seats.
     * @param seat holds the seat.
     *//*
    public void addSeat(final Seat seat) {
        if (!heldSeats.contains(seat)) {
            heldSeats.add(seat);
        }
    }

    *//**Method to remove a seat in set of seats.
     * @param seat holds identifier for the seat.
     *//*
    public void removeSeat(final String seat) {
        if (heldSeats.contains(seat)) {
            heldSeats.remove(seat);
        }
    }*/
}
