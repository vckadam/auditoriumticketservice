package com.vckadam.auditoriumticketservice.ticketservice;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

import com.vckadam.auditoriumticketservice.enumerator.SeatType;
import com.vckadam.auditoriumticketservice.exception.HouseFullException;
import com.vckadam.auditoriumticketservice.exception.InvalidCustomerEmailException;
import com.vckadam.auditoriumticketservice.exception.InvalidSeatHoldIdException;
import com.vckadam.auditoriumticketservice.exception.OnlyFewSeatsAvailableException;
import com.vckadam.auditoriumticketservice.model.Auditorium;
import com.vckadam.auditoriumticketservice.model.Customer;
import com.vckadam.auditoriumticketservice.model.Seat;
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

    /** uniqueSeatHoldId create unique identifier for seatHold objects. */
    private int uniqueSeatHoldId;

    /** constant for the number of row in Auditorium. */
    private static final int ROWNUM = 10;

    /** Constant for the number of seats in a single row. */
    private static final int COLUMNNUM = 15;

    /** Constants for time calculation for isExpired method. */
    private static final int SECONDCONST = 1000, EXPIREDTIME = 120;


    /** Constructor for the TicketServiceImpl. */
    public TicketServiceImpl() {
        auditorium = new Auditorium(TicketServiceImpl.ROWNUM,
                TicketServiceImpl.COLUMNNUM);
        seatHoldQueue = new LinkedList<Integer>();
        customers = new HashMap<String, Customer>();
        seatHolds = new HashMap<Integer, SeatHold>();
        confirmations = new HashMap<String, SeatHold>();
        this.uniqueSeatHoldId = 0;
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
        Set<Character> rowIdSet = new HashSet<Character>();
        while (!seatHoldQueue.isEmpty() && isExpired(seatHoldQueue.peek())) {
            SeatHold seatHold = seatHolds.get(seatHoldQueue.remove());
            if (seatHold.isConfirmed()) {
                continue;
            }
            for (Seat seat : seatHold.getHeldSeats()) {
                //Seat seat = auditorium.getSeats().get(seatId);
                seat.setSeatType(SeatType.OPEN);
                rowIdSet.add(seat.getRowId());
            }
            if (seatHolds.containsKey(seatHold.getSeatHoldId())) {
                this.seatHolds.remove(seatHold.getSeatHoldId());
            }
        }
        for (Character rowId : rowIdSet) {
            auditorium.setMaxConsecutiveEmptySeatsInRow(rowId,
                auditorium.getUpdateMaxConsEmptySeatsInRow(rowId));
        }
        auditorium.updateMaxConsecutiveEmptySeats();
        //Arrays.sort(auditorium.getMaxConsecutiveEmptySeatsInRow());
        auditorium.setAvailableSeats(auditorium.getAvailableSeats()
            - rowIdSet.size());
    }

    /** Method checks that seatHold is expired or not.
     *  @param seatHoldId holds seatHold identifier.
     *  @return true if seatHold object is expired.
     */
    public boolean isExpired(final int seatHoldId) {
        return ((new Date().getTime() - (seatHolds.get(seatHoldId)
            .getTimeStamp().getTime())) / TicketServiceImpl.SECONDCONST)
                > TicketServiceImpl.EXPIREDTIME;
    }

    /**
     * The number of seats in the auditorium that are neither held nor reserved.
     * @return the number of tickets available in the auditorium.
     */
    public int numSeatsAvailable() {
        clearExpiredTicketHolds();
        return auditorium.getAvailableSeats();
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
        if (numSeats <= 0) {
            throw new HouseFullException("House is Full.");
        }
        if (numSeats > auditorium.getAvailableSeats()) {
            String errMsg = "Only " + auditorium.getAvailableSeats() + " seats"
                + " are available.";
            throw new OnlyFewSeatsAvailableException(errMsg);
        }
        clearExpiredTicketHolds();
        if (!customers.containsKey(customerEmail)) {
            Customer customer = new Customer(customerEmail);
            customers.put(customerEmail, customer);
        }
        final Date currentTimeStamp = new Date();
        final List<Seat> bestSeats = auditorium.findBestSeats(numSeats);
        SeatHold seatHold = new SeatHold(++this.uniqueSeatHoldId,
            customerEmail, currentTimeStamp);
        seatHold.setHeldSeats(bestSeats);
        seatHoldQueue.add(this.uniqueSeatHoldId);
        seatHolds.put(this.uniqueSeatHoldId, seatHold);
        auditorium.setAvailableSeats(
            auditorium.getAvailableSeats() - bestSeats.size());
        return seatHold;
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
        if (!seatHolds.containsKey(seatHoldId)) {
            throw new InvalidSeatHoldIdException(
                "SeatHold id is either expired or doesn't exist.");
        }
        SeatHold seatHold = seatHolds.get(seatHoldId);
        if (seatHold.getCustomerEmail() != customerEmail) {
            throw new InvalidCustomerEmailException(
                "Email id doen't match with the records.");
        }
        clearExpiredTicketHolds();
        String confirmationCode = UUID.randomUUID().toString();
        customers.get(customerEmail).getConfirmations().add(confirmationCode);
        for (Seat seat : seatHold.getHeldSeats()) {
            seat.setSeatType(SeatType.RESERVED);
        }
        seatHold.setConfirmed(true);
        return confirmationCode;
    }

}
