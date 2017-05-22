package com.vckadam.auditoriumticketservice.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vckadam.auditoriumticketservice.model.Auditorium;

/**
 * AuditoriumTest class contains test cases for the methods
 * of Auditorium class.
 */
public class AuditoriumTest {

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

}
