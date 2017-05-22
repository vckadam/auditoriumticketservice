package com.vckadam.auditoriumticketservice.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /** seats map holds Seat objects.
     *  key of map holds (rowId of Seat)#(columnId of Seat) i.e. A#5
     *  value of map holds Seat object for a key.
     */
    private Map<String, Seat> seats;

    /** Constructor of Auditorium Class.
     *  @param rowNum holds number of rows in Auditorium.
     *  @param columnNum hold number of columns in Auditorium.
     */
    public Auditorium(final int rowNum, final int columnNum) {
        this.numberOfRows = rowNum;
        this.numberOfColumns = columnNum;
        this.setAvailableSeats(rowNum * columnNum);
        this.setMaxConsecutiveEmptySeats(columnNum);
        this.maxConsecutiveEmptySeatsInRow = new
                MaxConsecutiveEmptySeatsInRow[rowNum];
        this.seats = new HashMap<String, Seat>();

        for (int i = 0; i < rowNum; i++) {
            this.maxConsecutiveEmptySeatsInRow[i] =
                new MaxConsecutiveEmptySeatsInRow(i, columnNum);
            for (int j = 0; j < columnNum; j++) {
                char rowId = (char) (i + 'A');
                String key = Character.toString(rowId) + "#"
                + Integer.toString(j);
                Seat seat = new Seat(rowId, j);
                this.seats.put(key, seat);
            }
        }
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

    /** Method finds given number of seats as close as possible
     *  to the Auditorium. It also tries to find all the seats together.
     *  If it is not possible to find all the seats together, it breaks
     *  the number of seats into half and try to find half seats together.
     * @param numSeats holds number of seats to find.
     * @return set of best seats.
     */
    public Set<Seat> findBestSeats(final int numSeats) {
        return new HashSet<Seat>();
    }

    
}
