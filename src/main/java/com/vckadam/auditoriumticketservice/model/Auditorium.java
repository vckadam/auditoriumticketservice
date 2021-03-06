package com.vckadam.auditoriumticketservice.model;

import java.util.ArrayList;
import java.util.List;

import com.vckadam.auditoriumticketservice.enumerator.SeatType;

/**
 * Auditorium serves as a central place for all actions inside auditorium.
 */
public class Auditorium {

    /** numberOfRows holds total rows in the Auditorium.
     *  numberofColumns holds total number of seats in a single row.
     */
    private final int numberOfRows, numberOfColumns;

    /** availableSeats holds total available seats in Auditorium,
     *  which are not held or reserved.
     *  maxConsecutiveEmptySeats holds maximum consecutive empty seats
     *  available in the all the rows.
     */
    private int availableSeats, maxConsecutiveEmptySeats;

    /** each element of maxConsecutiveEmptySeatsInRow array holds maximum
     * consecutive empty seats for each row.
     */
    private MaxConsecutiveEmptySeatsInRow[] maxConsecutiveEmptySeatsInRow;

    /** seats 2-D array holds Seat objects. */
    private Seat[][] seats;

    /** Constructor of Auditorium Class.
     *  @param rowNum holds number of rows in Auditorium.
     *  @param columnNum hold number of columns in Auditorium.
     */
    public Auditorium(final int rowNum, final int columnNum) {
        this.numberOfRows = rowNum;
        this.numberOfColumns = columnNum;
        this.availableSeats = rowNum * columnNum;
        this.maxConsecutiveEmptySeats = columnNum;
        this.maxConsecutiveEmptySeatsInRow = new
                MaxConsecutiveEmptySeatsInRow[rowNum];
        this.seats = new Seat[rowNum][columnNum];
        for (int i = 0; i < rowNum; i++) {
            this.maxConsecutiveEmptySeatsInRow[i] =
                new MaxConsecutiveEmptySeatsInRow(i, columnNum);
            for (int j = 0; j < columnNum; j++) {
                char rowId = (char) (i + 'A');
                Seat seat = new Seat(rowId, j);
                this.seats[i][j] = seat;
            }
        }
    }

    /** Getter method for maxConsecutiveEmptySeatsInRow.
     * @return array MaxConsecutiveEmptySeatsInRow class
     */
    public MaxConsecutiveEmptySeatsInRow[] getMaxConsecutiveEmptySeatsInRow() {
        return maxConsecutiveEmptySeatsInRow;
    }

    /** Getter method for numberOfRows.
     * @return number of rows in the auditorium.
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /** Getter method for numberOfColumns.
     * @return number of seats in a single row.
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /** Getter method for availableSeats.
     * @return available seats in the Auditorium.
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /** Setter method for availableSeats.
     *  @param availSeats holds available seats in the Auditorium.
     */
    public void setAvailableSeats(final int availSeats) {
        this.availableSeats = availSeats;
    }

    /**Getter method for maxConsecutiveEmptySeats.
     * @return maximum consecutive empty seats in Auditorium.
     */
    public int getMaxConsecutiveEmptySeats() {
        return maxConsecutiveEmptySeats;
    }

    /**Setter method for maxConsecutiveEmptySeats.
     * @param maxConsEmptySeats holds maximum consecutive empty
     * seats in Auditorium.
     */
    public void setMaxConsecutiveEmptySeats(final int maxConsEmptySeats) {
        this.maxConsecutiveEmptySeats = maxConsEmptySeats;
    }

    /**Getter method for seats.
     * @return object of map seats.
     */
    public Seat[][] getSeats() {
        return seats;
    }

    /** Method update maxConsEmptySeats variable for
     * MaxConConsecutiveEmptySeatsInRow object for given rowInd.
     * @param rowId holds rowId for maxConsecutiveEmptySeatsInRow.
     * @param rowVal holds new value for the maxConsEmptySeats.
     */
    public void setMaxConsecutiveEmptySeatsInRow(final char rowId,
        final int rowVal) {
        final int rowInd = rowId - 'A';
        if (rowInd >= 0 && rowInd < maxConsecutiveEmptySeatsInRow.length) {
            this.maxConsecutiveEmptySeatsInRow[rowInd].
                setMaxConsEmptySeats(rowVal);
        }
    }


    /** Methods count updated maxConsEmptySeats for the row
     *  with given rowId.
     * @param rowId holds row identifier.
     * @return updated maximum value of maximum consecutive empty seats.
     */
    public int getUpdateMaxConsEmptySeatsInRow(final char rowId) {
        if (rowId < 'A' || rowId >= (char) ('A' + this.numberOfRows)) {
            return -1;
        }
        int currentCount = 0, currentMax = 0;
        for (int i = 0; i < this.numberOfColumns; i++) {
            Seat seat = seats[this.generateRowIndex(rowId)][i];
            if (seat.getSeatType() == SeatType.OPEN) {
                currentCount++;
            } else {
                currentMax = Math.max(currentMax, currentCount);
                currentCount = 0;
            }
        }
        currentMax = Math.max(currentMax, currentCount);
        return currentMax;
    }

    /** Method updates maxConsecutiveEmptySeats. */
    public void updateMaxConsecutiveEmptySeats() {
        int maxSeats = 0;
        for (int i = 0; i < this.maxConsecutiveEmptySeatsInRow.length; i++) {
            maxSeats = Math.max(maxSeats, this.maxConsecutiveEmptySeatsInRow[i]
                .getMaxConsEmptySeats());
        }
        this.maxConsecutiveEmptySeats = maxSeats;
    }

    /** Method updates maxConsecutiveEmptySeatsInRow.
     *  @param rowId hold row identifier
     *  @param newValue holds new updated value for maxConsEmptySeat
     */
    public void updateCollection(final char rowId, final int newValue) {
        MaxConsecutiveEmptySeatsInRow obj =
                this.maxConsecutiveEmptySeatsInRow[rowId - 'A'];
        obj.setMaxConsEmptySeats(newValue);
    }

   /** Method finds starting column number which is starting point of
    *  consecutive empty seats in the given row and given number of seats.
    *
    * @param rowInd holds index for the row.
    * @param numberOfSeats holds number of empty seats to find.
    * @return a column number which is starting point of found seats.
    */
    public int selectStartColumn(final int rowInd, final int numberOfSeats) {
        int start = 0;
        for (int i = start; i < this.numberOfColumns; i++) {
            if (seats[rowInd][i].getSeatType() != SeatType.OPEN) {
                start = i + 1;
            } else {
                if (i - start + 1 == numberOfSeats) {
                    return start;
                }
            }
        }
        return start;
    }

    /** Method finds given number of seats in single row.
     *
     *  @param numberOfSeats holds number of seats to find in single row.
     *  @return list of all the found seats.
     */
    public List<Seat> findSeatsInSingleRow(final int numberOfSeats) {
        List<Seat> foundSeats = new ArrayList<Seat>();
        MaxConsecutiveEmptySeatsInRow selected = null;
        for (int i = 0; i < this.maxConsecutiveEmptySeatsInRow.length; i++) {
            if (this.maxConsecutiveEmptySeatsInRow[i].getMaxConsEmptySeats()
                >= numberOfSeats) {
                selected = this.maxConsecutiveEmptySeatsInRow[i];
                break;
            }
        }
        final int selectedColumn = this.
            selectStartColumn(selected.getRowId(), numberOfSeats);
        for (int i = selectedColumn; i < selectedColumn + numberOfSeats; i++) {
            seats[selected.getRowId()][i].setSeatType(SeatType.HELD);
            foundSeats.add(seats[selected.getRowId()][i]);
        }
        this.updateCollection(this.generateRowId(selected.getRowId()),
            this.getUpdateMaxConsEmptySeatsInRow(
                this.generateRowId(selected.getRowId())));
        this.updateMaxConsecutiveEmptySeats();
        return foundSeats;
    }

    /** Method finds given number of seats as close as possible
     *  to the Auditorium's stage. It also tries to find all the seats together.
     *  If it is not possible to find all the seats together, it breaks
     *  the number of seats into half and try to find half seats together.
     * @param numberOfSeats holds number of seats to find.
     * @return list of best seats.
     */
    public List<Seat> findBestSeats(final int numberOfSeats) {
        if (numberOfSeats <= this.maxConsecutiveEmptySeats) {
            return this.findSeatsInSingleRow(numberOfSeats);
        } else {
            List<Seat> foundSeats = new ArrayList<Seat>();
            int halfNumberOfSeats = numberOfSeats / 2;
            if (numberOfSeats % 2 != 0) {
                foundSeats.addAll(
                    this.findBestSeats(halfNumberOfSeats + 1));
            } else {
                foundSeats.addAll(this.findBestSeats(halfNumberOfSeats));
            }
            foundSeats.addAll(this.findBestSeats(halfNumberOfSeats));
            return foundSeats;
        }
    }

    /** Method convert rowId to row index.
     *
     * @param rowId holds row Identifier
     * @return Generated row index for rowId
     */
    public int generateRowIndex(final char rowId) {
        if (rowId < 'A' || rowId >= (char) ('A' + this.numberOfRows)) {
            return -1;
        }
        return rowId - 'A';
    }

    /** Method convert rowInd to rowId.
    *
    * @param rowInd holds index of the row.
    * @return Generated rowId for the row.
    */
    public char generateRowId(final int rowInd) {
        if (rowInd < 0 || rowInd >= this.numberOfRows) {
            return '\0';
        }
        return (char) (rowInd + 'A');
    }
}
