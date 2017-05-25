package com.vckadam.auditoriumticketservice.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.vckadam.auditoriumticketservice.enumerator.SeatType;
import com.vckadam.auditoriumticketservice.model.SeatHold;
import com.vckadam.auditoriumticketservice.ticketservice.TicketServiceImpl;

/**
 * OverallTest class contains test cases for overall
 * functionality of the system.
 */
public class OverallTest {
    /** ticketServiceImpl holds object of TicketServiceImpl class.*/
    private TicketServiceImpl ticketServiceImpl;

    /** Method creates object of Auditorium class
     *  before every test case.
     */
    @Before
    public void runOnceBeforeTestMethod() {
        ticketServiceImpl = new TicketServiceImpl();
    }

    /** Method test overall behavior of System.
     */
    @Test
    public void testOverallTest() {
        final int totalCapacity = 10 * 15,
            numberOfRows = 10, numberOfColumn = 15,
                seat1 = 28, seat2 = 30,
                    remaining1 = 122, remaining2 = 30;
        int seatHoldId = 2;
        assertEquals(totalCapacity, ticketServiceImpl.getAuditorium()
            .getAvailableSeats());
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                assertEquals(SeatType.OPEN,
                    ticketServiceImpl.getAuditorium().getSeats()[i][j]
                        .getSeatType());
            }
        }

        ticketServiceImpl.findAndHoldSeats(seat1, "abc@asu.edu");
        ticketServiceImpl.reserveSeats(1, "abc@asu.edu");
        assertEquals(remaining1, ticketServiceImpl.getAuditorium()
            .getAvailableSeats());

        SeatHold seatHold1 = ticketServiceImpl.
            findAndHoldSeats(1, "bcd@asu.edu");
        assertEquals("A#14", seatHold1.getHeldSeats().get(0).getSeatNo());
        ticketServiceImpl.reserveSeats(2, "bcd@asu.edu");
        assertEquals(remaining1 - 1, ticketServiceImpl.getAuditorium()
            .getAvailableSeats());

        SeatHold seatHold2 = ticketServiceImpl.
                findAndHoldSeats(1, "cde@asu.edu");
        assertEquals("B#14", seatHold2.getHeldSeats().get(0).getSeatNo());
        ticketServiceImpl.reserveSeats(++seatHoldId, "cde@asu.edu");
        assertEquals(remaining1 - 2, ticketServiceImpl.getAuditorium()
            .getAvailableSeats());

        ticketServiceImpl.findAndHoldSeats(seat2, "def@asu.edu");
        ticketServiceImpl.reserveSeats(++seatHoldId, "def@asu.edu");

        ticketServiceImpl.findAndHoldSeats(seat2, "fgh@asu.edu");
        ticketServiceImpl.reserveSeats(++seatHoldId, "fgh@asu.edu");

        ticketServiceImpl.findAndHoldSeats(seat2, "hig@asu.edu");
        ticketServiceImpl.reserveSeats(++seatHoldId, "hig@asu.edu");
        assertEquals(remaining2, ticketServiceImpl.getAuditorium()
                .getAvailableSeats());

        ticketServiceImpl.findAndHoldSeats(seat2 - 1, "hig@asu.edu");
        ticketServiceImpl.reserveSeats(++seatHoldId, "hig@asu.edu");
        assertEquals(1, ticketServiceImpl.getAuditorium()
                .getAvailableSeats());

        SeatHold seatHold3 = ticketServiceImpl.
                findAndHoldSeats(1, "cde@asu.edu");
        assertEquals("J#14", seatHold3.getHeldSeats().get(0).getSeatNo());
        ticketServiceImpl.reserveSeats(++seatHoldId, "cde@asu.edu");
        assertEquals(0, ticketServiceImpl.getAuditorium()
            .getAvailableSeats());

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                assertEquals(SeatType.RESERVED,
                    ticketServiceImpl.getAuditorium().getSeats()[i][j]
                        .getSeatType());
            }
        }
    }
}
