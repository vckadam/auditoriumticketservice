package com.vckadam.auditoriumticketservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.auditoriumticketservice.enumerator.SeatType;
import com.vckadam.auditoriumticketservice.exception.HouseFullException;
import com.vckadam.auditoriumticketservice.exception.InvalidCustomerEmailException;
import com.vckadam.auditoriumticketservice.exception.InvalidRequstedNumberOfSeatsException;
import com.vckadam.auditoriumticketservice.exception.InvalidSeatHoldIdException;
import com.vckadam.auditoriumticketservice.exception.OnlyFewSeatsAvailableException;
import com.vckadam.auditoriumticketservice.model.Seat;
import com.vckadam.auditoriumticketservice.model.SeatHold;
import com.vckadam.auditoriumticketservice.ticketservice.TicketServiceImpl;

/**
 * TicketServiceImplTest class contains test cases for the methods
 * of TicketServiceImplTest class.
 */
public class TicketServiceImplTest {

    /** ticketServiceImpl holds object of TicketServiceImpl class.*/
    private TicketServiceImpl ticketServiceImpl;

    /** Method creates object of Auditorium class
     *  before every test case.
     */
    @Before
    public void runOnceBeforeTestMethod() {
        ticketServiceImpl = new TicketServiceImpl();
    }

    /** Method tests isExpired
     *  method in TicketServiceImpl class.
     *  If SeatHold is expired return true.
     */
    @Test
    public void testisExpired1() {
        final int year = 2013, day = 9;
        Calendar cal = Calendar.getInstance();
        cal.set(year, Calendar.JANUARY, day);
        Date date = cal.getTime();
        SeatHold seatHold = new SeatHold(1, "abc@gmail.com", date);
        ticketServiceImpl.getSeatHolds().put(1, seatHold);
        assertTrue(ticketServiceImpl.isExpired(1));
    }

    /** Method test isExpired
     *  method in TicketServiceImpl class.
     *  If SeatHold is not expired return false.
     */
    @Test
    public void testisExpired2() {
        Date date = new Date();
        SeatHold seatHold = new SeatHold(1, "abc@gmail.com", date);
        ticketServiceImpl.getSeatHolds().put(1, seatHold);
        assertFalse(ticketServiceImpl.isExpired(1));
    }

    /** Method test clearExpiredTicketHolds
     *  method in TicketServiceImpl class.
     *  If SeatHold is expired than free the
     *  seats which are held for that SeatHold object.
     *  If seatHold is reserved than keep it reserved
     *  even if the its timeStamp over expired time.
     *  If SeatHold is held and it's not yet expired,
     *  then keep it as Held.
     */
    @Test
    public void testClearExpiredTicketHolds() {
        final int year = 2013, day = 9;
        Calendar cal = Calendar.getInstance();
        cal.set(year, Calendar.JANUARY, day);
        Date date = cal.getTime();
        SeatHold seatHold1 = new SeatHold(1, "abc@gmail.com", date);
        Seat seat1 = ticketServiceImpl.getAuditorium().getSeats()[0][1];
        seat1.setSeatType(SeatType.HELD);
        seatHold1.setHeldSeats(new ArrayList<Seat>(Arrays.asList(seat1)));

        SeatHold seatHold2 = new SeatHold(2, "abc@gmail.com", date);
        Seat seat2 = ticketServiceImpl.getAuditorium().getSeats()[0][2];
        seatHold2.setConfirmed(true);
        seat2.setSeatType(SeatType.RESERVED);
        seatHold2.setHeldSeats(new ArrayList<Seat>(Arrays.asList(seat2)));

        final int colId = 3;
        final int seatHoldId = 3;
        SeatHold seatHold3 = new SeatHold(seatHoldId,
            "abc@gmail.com", new Date());
        Seat seat3 = ticketServiceImpl.getAuditorium().getSeats()[0][colId];
        seat3.setSeatType(SeatType.HELD);
        seatHold3.setHeldSeats(new ArrayList<Seat>(Arrays.asList(seat3)));

        this.ticketServiceImpl.getSeatHoldQueue().addAll(
            Arrays.asList(1, 2, seatHoldId));
        this.ticketServiceImpl.getSeatHolds().put(1, seatHold1);
        this.ticketServiceImpl.getSeatHolds().put(2, seatHold2);
        this.ticketServiceImpl.getSeatHolds().put(seatHoldId, seatHold3);

        this.ticketServiceImpl.clearExpiredTicketHolds();

        assertEquals(SeatType.OPEN, seat1.getSeatType());
        assertEquals(SeatType.RESERVED, seat2.getSeatType());
        assertEquals(SeatType.HELD, seat3.getSeatType());
    }

    /** Method test numSeatsAvailable
     *  method in TicketServiceImpl class.
     *  If all seats are free, return capacity of auditorium.
     */
    @Test
    public void testNumSeatsAvailable() {
        final int capacity = 10 * 15;
        assertEquals(capacity, ticketServiceImpl.numSeatsAvailable());
        ticketServiceImpl.findAndHoldSeats(2,
            "abc@gmail.com");
        assertEquals(capacity - 2, ticketServiceImpl.numSeatsAvailable());
    }

    /** Method test findAndHoldSeats
     *  method in TicketServiceImpl class.
     *  If seats are available, returns seatHold object.
     */
    @Test
    public void testFindAndHoldSeats1() {
        SeatHold seatHold = ticketServiceImpl.findAndHoldSeats(2,
            "abc@asu.edu");
        for (Seat seat : seatHold.getHeldSeats()) {
           assertTrue(SeatType.HELD == seat.getSeatType());
        }
        assertEquals("A#0", seatHold.getHeldSeats().get(0).getSeatNo());
        assertEquals("A#1", seatHold.getHeldSeats().get(1).getSeatNo());
        assertEquals("abc@asu.edu", seatHold.getCustomerEmail());
    }

    /** Method test findAndHoldSeats
     *  method in TicketServiceImpl class.
     *  Throws exception when house is full.
     */
    @Test(expected = HouseFullException.class)
    public void testFindAndHoldSeats2() {
        ticketServiceImpl.getAuditorium().setAvailableSeats(0);
        ticketServiceImpl.findAndHoldSeats(2, "abc@gmail.com");
    }

    /** Method test findAndHoldSeats
     *  method in TicketServiceImpl class.
     *  Throws exception when requested seats are grater
     *  than available seats.
     */
    @Test(expected = OnlyFewSeatsAvailableException.class)
    public void testFindAndHoldSeats3() {
        ticketServiceImpl.getAuditorium().setAvailableSeats(1);
        ticketServiceImpl.findAndHoldSeats(2, "abc@gmail.com");
    }

    /** Method test findAndHoldSeats
     *  method in TicketServiceImpl class.
     *  Throws exception when number of requested seats
     *  is invalid.
     */
    @Test(expected = InvalidRequstedNumberOfSeatsException.class)
    public void testFindAndHoldSeats4() {
        ticketServiceImpl.findAndHoldSeats(-1, "abc@gmail.com");
    }

    /** Method test reserveSeats
     *  method in TicketServiceImpl class.
     *  Throws exception when seatHoldId
     *  is invalid.
     */
    @Test(expected = InvalidSeatHoldIdException.class)
    public void testReserveSeats1() {
        ticketServiceImpl.reserveSeats(-1, "abc@gmail.com");
    }

    /** Method test reserveSeats
     *  method in TicketServiceImpl class.
     *  Throws exception when email addresses
     *  are different for same seatHold.
     */
    @Test(expected = InvalidCustomerEmailException.class)
    public void testReserveSeats2() {
        ticketServiceImpl.findAndHoldSeats(2, "abc@asu.com");
        ticketServiceImpl.reserveSeats(1, "abc@gmail.com");
    }

    /** Method test reserveSeats
     *  method in TicketServiceImpl class.
     *  Method reserves seats if they are held.
     */
    @Test
    public void testReserveSeats3() {
        SeatHold seatHold = ticketServiceImpl
            .findAndHoldSeats(2, "abc@asu.com");
        assertEquals(SeatType.HELD,
            seatHold.getHeldSeats().get(0).getSeatType());
        ticketServiceImpl.reserveSeats(1, "abc@asu.com");
        assertEquals(SeatType.RESERVED,
                seatHold.getHeldSeats().get(0).getSeatType());
    }
}
