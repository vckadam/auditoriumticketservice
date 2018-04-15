package com.vckadam.auditoriumticketservice.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;

import org.junit.Test;

import com.vckadam.auditoriumticketservice.enumerator.SeatType;
import com.vckadam.auditoriumticketservice.model.Auditorium;
import com.vckadam.auditoriumticketservice.model.Seat;



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


    /** Method test generateRowIndex
     *  method in Auditorium class.
     *  For valid rowId, method return valid output.
     */
    @Test
    public void testGenerateRowIndex1() {
       int actualOutput = auditorium.generateRowIndex('B');
       assertEquals(actualOutput, 1);
    }

    /** Method test generateRowIndex
     *  method in Auditorium class.
     *  If rowId is not valid, method returns -1.
     */
    @Test
    public void testGenerateRowIndex2() {
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
       int actualOutput = auditorium.getUpdateMaxConsEmptySeatsInRow('A');
       assertEquals(actualOutput, AuditoriumTest.COLUMN_SIZE);
    }

    /** Method test getUpdateMaxConsEmptySeats()
     *  method in Auditorium class.
     *  If one of the seat is held, method returns new
     *  value of maxConsEmptySeats.
     */
    @Test
    public void testGetUpdateMaxConsEmptySeats2() {
       auditorium.getSeats()[0][2].setSeatType(SeatType.HELD);
       int actualOutput = auditorium.getUpdateMaxConsEmptySeatsInRow('A');
       assertEquals(actualOutput, AuditoriumTest.UPDATED_COLUMN_SIZE);
    }

    /** Method test getUpdateMaxConsEmptySeats()
     *  method in Auditorium class.
     *  If rowId is not proper, it will return -1.
     */
    @Test
    public void testGetUpdateMaxConsEmptySeats3() {
       int actualOutput = auditorium.getUpdateMaxConsEmptySeatsInRow('a');
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
       auditorium.getSeats()[0][2].setSeatType(SeatType.HELD);
       int newValue = auditorium.getUpdateMaxConsEmptySeatsInRow('A');
       assertEquals(newValue, AuditoriumTest.UPDATED_COLUMN_SIZE);
       auditorium.updateCollection('A', newValue);
       assertEquals(auditorium.getMaxConsecutiveEmptySeatsInRow()[0]
           .getMaxConsEmptySeats(), newValue);
    }


    /** Method tests maxConsecutiveEmptySeatsInRow
     *  method in Auditorium class.
     */
    @Test
    public void testMaxConsecutiveEmptySeatsInRow() {
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

    /** Method tests updateMaxConsecutiveEmptySeats
    *  method in Auditorium class.
    */
   @Test
   public void testUpdateMaxConsecutiveEmptySeats() {
       assertEquals(auditorium.getMaxConsecutiveEmptySeats(),
           auditorium.getNumberOfColumns());
       for (int i = 0; i < auditorium.getNumberOfRows(); i++) {
           if (i % 2 == 0) {
               auditorium.getMaxConsecutiveEmptySeatsInRow()[i]
                       .setMaxConsEmptySeats(2);
           } else {
               auditorium.getMaxConsecutiveEmptySeatsInRow()[i]
                       .setMaxConsEmptySeats(0);
           }
       }
       auditorium.updateMaxConsecutiveEmptySeats();
       assertEquals(2, auditorium.getMaxConsecutiveEmptySeats());
   }

   /** Method tests findSeatsInSingleRow
    *  method in Auditorium class.
    *  If auditorium is empty, method will select
    *  first two seats.
    */
   @Test
   public void testFindSeatsInSingleRow1() {
       List<Seat> seats = auditorium.findSeatsInSingleRow(2);
       assertEquals("A#0", seats.get(0).getSeatNo());
       assertEquals("A#1", seats.get(1).getSeatNo());
   }

   /** Method tests findSeatsInSingleRow
    *  method in Auditorium class.
    *  Though auditorium has few empty seats in first two rows,
    *  method will try to find all the seats together.
    */
   @Test
   public void testFindSeatsInSingleRow2() {
       final int numberOfSeats = 3;
       auditorium.updateCollection('A', 2);
       auditorium.updateCollection('B', 2);
       List<Seat> seats = auditorium.findSeatsInSingleRow(
           numberOfSeats);
       assertEquals("C#0", seats.get(0).getSeatNo());
       assertEquals("C#1", seats.get(1).getSeatNo());
       assertEquals("C#2", seats.get(2).getSeatNo());
   }

   /** Method tests findSeatsInSingleRow
    *  method in Auditorium class.
    *  If auditorium has few empty seats in first two rows,
    *  method will try to fill the empty seat fragments first.
    */
   @Test
   public void testFindSeatsInSingleRow3() {
       final int rowLimit = 5, numberOfSeats = 3;
       for (int i = 0; i < rowLimit; i++) {
           auditorium.getSeats()[0][i].setSeatType(SeatType.HELD);
       }
       auditorium.updateCollection('A', 2);
       auditorium.updateCollection('B', 2);
       List<Seat> seats = auditorium.findSeatsInSingleRow(
           numberOfSeats);
       assertEquals("C#0", seats.get(0).getSeatNo());
       assertEquals("C#1", seats.get(1).getSeatNo());
       assertEquals("C#2", seats.get(2).getSeatNo());
       List<Seat> newSeats = auditorium.findSeatsInSingleRow(
               1);
       assertEquals("A#5", newSeats.get(0).getSeatNo());
   }

   /** Method tests findBestSeats
    *  method in Auditorium class.
    *  If auditorium doen't have consecutive empty seats
    *  in single row,
    *  then method split the seats in two rows.
    */
   @Test
   public void testFindBestSeats() {
       final int rowLimit = 5, numberOfSeats = 3;
       for (int i = 0; i < rowLimit; i++) {
           auditorium.getSeats()[0][i].setSeatType(SeatType.HELD);
           auditorium.getSeats()[1][i].setSeatType(SeatType.RESERVED);
       }
       auditorium.setMaxConsecutiveEmptySeats(2);
       auditorium.updateCollection('A', 2);
       auditorium.updateCollection('B', 2);
       List<Seat> seats = auditorium.findBestSeats(
           numberOfSeats);
       assertEquals("A#5", seats.get(0).getSeatNo());
       assertEquals("A#6", seats.get(1).getSeatNo());
       assertEquals("B#5", seats.get(2).getSeatNo());
   }

}
