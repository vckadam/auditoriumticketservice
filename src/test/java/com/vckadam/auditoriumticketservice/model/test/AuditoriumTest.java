package com.vckadam.auditoriumticketservice.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vckadam.auditoriumticketservice.model.Auditorium;
import com.vckadam.auditoriumticketservice.model.SeatType;

/**
 * AuditoriumTest class contains test cases for the methods
 * of Auditorium class.
 */
public class AuditoriumTest {

    /** constant holds number of seats in a row. */
    public static final int COLUMN_SIZE = 7;
    
    /** constant holds number of consecutive seats after
     * return by getUpdateMaxConsEmptySeats after seat "A#2" is held */
    public static final int UPDATED_COLUMN_SIZE = 4;

    /** constant holds number of rows. */
    public static final int ROW_SIZE = 7;

    /** Method test createKeyForSeats() method in Auditorium class.*/
    @Test
    public void testCreateKeyForSeats1() {
       Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('A', 1);
       assertEquals(actualOutput, "A#1");
    }

    /** Method test createKeyForSeats() method in Auditorium class.*/
    @Test
    public void testCreateKeyForSeats2() {
       Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('C', 1);
       assertEquals(actualOutput, "");
    }

    /** Method test createKeyForSeats() method in Auditorium class.*/
    @Test
    public void testCreateKeyForSeats3() {
       Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('B', -1);
       assertEquals(actualOutput, "");
    }

    /** Method test createKeyForSeats() method in Auditorium class.*/
    @Test
    public void testCreateKeyForSeats4() {
       Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('B', 2);
       assertEquals(actualOutput, "");
    }

    /** Method test createKeyFormaxConsEmptySeatsObjs()
     *  method in Auditorium class.
     */
    @Test
    public void testCreateKeyFormaxConsEmptySeatsObjs1() {
       Auditorium auditorium = new Auditorium(2, 2);
       int actualOutput = auditorium.createKeyFormaxConsEmptySeatsObjs('B');
       assertEquals(actualOutput, 1);
    }

    /** Method test createKeyFormaxConsEmptySeatsObjs()
     *  method in Auditorium class.
     */
    @Test
    public void testCreateKeyFormaxConsEmptySeatsObjs2() {
       Auditorium auditorium = new Auditorium(1, 2);
       int actualOutput = auditorium.createKeyFormaxConsEmptySeatsObjs('C');
       assertEquals(actualOutput, -1);
    }

    /** Method test getUpdateMaxConsEmptySeats()
     *  method in Auditorium class.
     */
    @Test
    public void testGetUpdateMaxConsEmptySeats1() {
       Auditorium auditorium = new Auditorium(1, AuditoriumTest.COLUMN_SIZE);
       int actualOutput = auditorium.getUpdateMaxConsEmptySeats('A');
       assertEquals(actualOutput, AuditoriumTest.COLUMN_SIZE);
    }

    /** Method test getUpdateMaxConsEmptySeats()
     *  method in Auditorium class.
     */
    @Test
    public void testGetUpdateMaxConsEmptySeats2() {
       Auditorium auditorium = new Auditorium(1, AuditoriumTest.COLUMN_SIZE);
       auditorium.getSeats().get("A#2").setSeatType(SeatType.HELD);
       int actualOutput = auditorium.getUpdateMaxConsEmptySeats('A');
       assertEquals(actualOutput, AuditoriumTest.UPDATED_COLUMN_SIZE);
    }
}
