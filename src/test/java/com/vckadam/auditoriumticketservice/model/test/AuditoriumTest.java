package com.vckadam.auditoriumticketservice.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

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
     *  return by getUpdateMaxConsEmptySeats after seat "A#2" is held. */
    public static final int UPDATED_COLUMN_SIZE = 4;

    /** constant holds number of rows. */
    public static final int ROW_SIZE = 3;

    /** constant holds index of row. */
    public static final int ROWIND = 3;
    /** auditorium holds object of Auditorium class. */
    private Auditorium auditorium;

    /** Method creates object of Auditorium class
     *  before every test case.
     */
    @Before
    public void runOnceBeforeTestMethod() {
        auditorium = new Auditorium(AuditoriumTest.ROW_SIZE,
            AuditoriumTest.COLUMN_SIZE);
    }

    /** Method test createKeyForSeats() method in Auditorium class.
     *  If rowId is valid, method returns valid key.
     */
    @Test
    public void testCreateKeyForSeats1() {
       //Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('A', 1);
       assertEquals(actualOutput, "A#1");
    }

    /** Method test createKeyForSeats() method in Auditorium class.
     *  If rowId is not valid, method return empty string.
     */
    @Test
    public void testCreateKeyForSeats2() {
       //Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('Z', 1);
       assertEquals(actualOutput, "");
    }

    /** Method test createKeyForSeats() method in Auditorium class.
     *  If colId is not Valid, method return empty string.
     */
    @Test
    public void testCreateKeyForSeats3() {
       //Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('B', -1);
       assertEquals(actualOutput, "");
    }

    /** Method test createKeyForSeats() method in Auditorium class.
     *  If rowId is not Valid, method return empty string.
     */
    @Test
    public void testCreateKeyForSeats4() {
       //Auditorium auditorium = new Auditorium(1, 2);
       String actualOutput = auditorium.createKeyForSeats('D', 2);
       assertEquals(actualOutput, "");
    }

    /** Method test generateRowIndex
     *  method in Auditorium class.
     *  For valid rowId, method return valid output.
     */
    @Test
    public void testGenerateRowIndex1() {
       //Auditorium auditorium = new Auditorium(2, 2);
       int actualOutput = auditorium.generateRowIndex('B');
       assertEquals(actualOutput, 1);
    }

    /** Method test generateRowIndex
     *  method in Auditorium class.
     *  If rowId is not valid, method returns -1.
     */
    @Test
    public void testGenerateRowIndex2() {
       //Auditorium auditorium = new Auditorium(1, 2);
       int actualOutput = auditorium.generateRowIndex('Z');
       assertEquals(actualOutput, -1);
    }

    /** Method test generateRowId
     *  method in Auditorium class.
     *  If rowId is valid, method returns valid rowId.
     */
    @Test
    public void testGenerateRowId1() {
       char actualOutput = auditorium.generateRowId(1);
       assertEquals(actualOutput, 'B');
    }

    /** Method test generateRowId
     *  method in Auditorium class.
     *  If rowId is valid, method returns valid rowId.
     */
    @Test
    public void testGenerateRowId2() {
       final int invalidRowId = 25;
       char actualOutput = auditorium.generateRowId(invalidRowId);
       assertEquals(actualOutput, '\0');
    }

    /** Method test getUpdateMaxConsEmptySeats()
     *  method in Auditorium class.
     *  If all the seats are open, method returns
     *  total seats in the row.
     */
    @Test
    public void testGetUpdateMaxConsEmptySeats1() {
       //Auditorium auditorium = new Auditorium(1, AuditoriumTest.COLUMN_SIZE);
       int actualOutput = auditorium.getUpdateMaxConsEmptySeats('A');
       assertEquals(actualOutput, AuditoriumTest.COLUMN_SIZE);
    }

    /** Method test getUpdateMaxConsEmptySeats()
     *  method in Auditorium class.
     *  If one of the seat is held, method returns new
     *  value of maxConsEmptySeats.
     */
    @Test
    public void testGetUpdateMaxConsEmptySeats2() {
       //Auditorium auditorium = new Auditorium(1, AuditoriumTest.COLUMN_SIZE);
       auditorium.getSeats()[0][2].setSeatType(SeatType.HELD);
       int actualOutput = auditorium.getUpdateMaxConsEmptySeats('A');
       assertEquals(actualOutput, AuditoriumTest.UPDATED_COLUMN_SIZE);
    }

    /** Method test getUpdateMaxConsEmptySeats()
     *  method in Auditorium class.
     *  If rowId is not proper, it will return -1.
     */
    @Test
    public void testGetUpdateMaxConsEmptySeats3() {
       //Auditorium auditorium = new Auditorium(1, AuditoriumTest.COLUMN_SIZE);
       int actualOutput = auditorium.getUpdateMaxConsEmptySeats('a');
       assertEquals(actualOutput, -1);
    }

    /** Method test updateCollection()
     *  method in Auditorium class.
     *  It tests value of MaxConsecutiveEmptySeatsInRow object &
     *  is updated with new value.
     *  And, Array maxConsecutiveEmptySeatsInRow is sorted in the
     *  descending order of maxConsEmptySeats
     */
    @Test
    public void testUpdateCollection1() {
       //Auditorium auditorium = new Auditorium(2, AuditoriumTest.COLUMN_SIZE);
       auditorium.getSeats()[0][2].setSeatType(SeatType.HELD);
       int newValue = auditorium.getUpdateMaxConsEmptySeats('A');
       assertEquals(newValue, AuditoriumTest.UPDATED_COLUMN_SIZE);
       auditorium.updateCollection('A', newValue);
       assertEquals(auditorium.getMaxConsecutiveEmptySeatsRowInd()[0]
           .getMaxConsEmptySeats(), AuditoriumTest.UPDATED_COLUMN_SIZE);
       assertEquals(auditorium
           .getMaxConsecutiveEmptySeatsInRow()[2].getRowId(), 0);
    }

    /** Method test updateCollection()
     *  method in Auditorium class.
     *  It tests value of MaxConsecutiveEmptySeatsInRow object &
     *  is updated with new value.
     *  And, Array maxConsecutiveEmptySeatsInRow is sorted in the
     *  descending order of maxConsEmptySeats.
     *  It also tests that if two objects have same maxConsEmptySeats values,
     *  Array is sorted those object in the ascending order of rowId
     */
    @Test
    public void testUpdateCollection2() {
       /*Auditorium auditorium = new Auditorium(AuditoriumTest.ROW_SIZE,
           AuditoriumTest.COLUMN_SIZE);*/
       auditorium.getSeats()[0][2].setSeatType(SeatType.HELD);
       int newValue = auditorium.getUpdateMaxConsEmptySeats('A');
       assertEquals(newValue, AuditoriumTest.UPDATED_COLUMN_SIZE);
       auditorium.updateCollection('A', newValue);
       assertEquals(auditorium.getMaxConsecutiveEmptySeatsRowInd()[0]
           .getMaxConsEmptySeats(), AuditoriumTest.UPDATED_COLUMN_SIZE);
       assertEquals(auditorium
           .getMaxConsecutiveEmptySeatsInRow()[0].getRowId(), 1);
       assertEquals(auditorium
           .getMaxConsecutiveEmptySeatsInRow()[2].getRowId(), 0);
    }

    /** Method tests maxConsecutiveEmptySeatsInRow
     *  method in Auditorium class.
     */
    @Test
    public void testMaxConsecutiveEmptySeatsInRow() {
       //Auditorium auditorium = new Auditorium(2, AuditoriumTest.COLUMN_SIZE);
       auditorium.setMaxConsecutiveEmptySeatsInRow('B',
           AuditoriumTest.UPDATED_COLUMN_SIZE);
       assertEquals(auditorium
               .getMaxConsecutiveEmptySeatsInRow()[1].getMaxConsEmptySeats(),
                   AuditoriumTest.UPDATED_COLUMN_SIZE);
    }

    /** Method tests selectStartColumn
     *  method in Auditorium class.
     */
    @Test
    public void testSelectStartColumn1() {
       auditorium.getSeats()[0][0].setSeatType(SeatType.HELD);
       auditorium.getSeats()[0][1].setSeatType(SeatType.RESERVED);
       auditorium.getSeats()[0][AuditoriumTest.ROWIND].
           setSeatType(SeatType.HELD);
       int actualOutput = auditorium.selectStartColumn(0, 2);
       final int expectedOutput = 4;
       assertEquals(actualOutput, expectedOutput);
    }

    /** Method tests selectStartColumn
     *  method in Auditorium class.
     */
    @Test
    public void testSelectStartColumn2() {
       auditorium.getSeats()[0][0].setSeatType(SeatType.HELD);
       auditorium.getSeats()[0][1].setSeatType(SeatType.RESERVED);
       auditorium.getSeats()[0][AuditoriumTest.ROWIND].
           setSeatType(SeatType.HELD);
       int actualOutput = auditorium.selectStartColumn(0, 1);
       final int expectedOutput = 2;
       assertEquals(actualOutput, expectedOutput);
    }
}
