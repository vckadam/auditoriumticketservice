package com.vckadam.auditoriumticketservice.ticketservice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.vckadam.auditoriumticketservice.model.Auditorium;
import com.vckadam.auditoriumticketservice.model.Customer;
import com.vckadam.auditoriumticketservice.model.SeatHold;

/**
 * SeatHold serves as a central place for providing ticket service
 * functionalities.
 */
public class TicketServiceImpl implements TicketService {

    /** auditorium holds the object of Auditorium Class. */
     private Auditorium auditorium;

    /**Queue of seatHoldId in order to remove all the expired
     * SeatHold objects.
     */
    private Queue<Integer> seatHoldQueue;

    /**Map to store customers
     * key holds email address of customer
     * value holds object of Customer class.
     */
    private Map<String, Customer> customers;

    /**Map to store SeatHold objects
     * key holds seatHoldId
     * value holds object of SeatHold class.
     */
    private Map<Integer, SeatHold> seatHolds;

    /**Map to store all reserved SeatHold objects
     * key holds confirmation string
     * value holds object of SeatHold class.
     */
    private Map<String, SeatHold> confirmations;

    /** constant for the number of row in Auditorium. */
    private static final int ROWNUM = 10;

    /** Constant for the number of seats in a single row. */
    private static final int COLUMNNUM = 15;

    /** Constructor for the TicketServiceImpl. */
    public TicketServiceImpl() {
        auditorium = new Auditorium(TicketServiceImpl.ROWNUM,
                TicketServiceImpl.COLUMNNUM);
        seatHoldQueue = new LinkedList<Integer>();
        customers = new HashMap<String, Customer>();
        seatHolds = new HashMap<Integer, SeatHold>();
        confirmations = new HashMap<String, SeatHold>();
    }

    /** Getter Method for customers.
     *  @return map with key-value pair of email - Customer object
     */
    public Map<String, Customer> getCustomers() {
        return this.customers;
    }

    /** Getter Method for seatHolds.
     *  @return map with key-value pair of seatHoldId - SeatHOld object
     */
    public Map<Integer, SeatHold> getSeatHolds() {
        return this.seatHolds;
    }

    /** Getter Method for confirmations.
     *  @return map with key-value pair of confirmation code - SeatHOld object
     */
    public Map<String, SeatHold> getConfirmations() {
        return this.confirmations;
    }

    /** Getter Method for seatHoldQueue.
     *  @return Queue of seatHoldId
     */
    public Queue<Integer> getSeatHoldQueue() {
        return this.seatHoldQueue;
    }

    /** Method to remove all the expired SeatHold objects. */
    public void clearExpiredTicketHolds() {

    }
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
