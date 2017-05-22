package com.vckadam.auditoriumticketservice.model;

/**
 * MaxConsequtiveEmptySeatsInRow serves as a place to store maximum consecutive
 * empty seats in each row.
 */
public class MaxConsecutiveEmptySeatsInRow {

    /** rowId holds identifier for the row and
     * maxConsEmptySeats holds maximum consecutive seats available
     * at the given rowId.
     */
    private int rowId, maxConsEmptySeats;

    /** Getter method for rowId.
     * @return identifier of the row.
     */
    public int getRowId() {
        return rowId;
    }

    /** Constructor for MaxConsecutiveEmptySeatsInRow.
     *  @param rowIdentifier holds identifier for the row.
     *  @param maxConsecutiveEmptySeats holds maximum consecutive
     *  empty seats in the row.
     */
    public MaxConsecutiveEmptySeatsInRow(final int rowIdentifier,
                                         final int maxConsecutiveEmptySeats) {
        this.rowId = rowIdentifier;
        this.maxConsEmptySeats = maxConsecutiveEmptySeats;
    }

    /** Setter method for rowId.
     *
     * @param rowNum holds identifier of the row.
     */
    public void setRowId(final int rowNum) {
        this.rowId = rowNum;
    }

    /** Getter method for maxConsEmptySeats.
     * @return maximum consecutive seats for the row.
     */
    public int getMaxConsEmptySeats() {
        return maxConsEmptySeats;
    }

    /** Setter method for maxConsEmptySeats.
     * @param maxConsecutiveEmptySeats holds maximum consecutive empty
     * seats in the row.
     */
    public void setMaxConsEmptySeats(final int maxConsecutiveEmptySeats) {
        this.maxConsEmptySeats = maxConsecutiveEmptySeats;
    }
}
