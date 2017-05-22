package com.vckadam.auditoriumticketservice.model;

/**
 * MaxConsequtiveEmptySeatsInRow serves as a place to store maximum consecutive
 * empty seats in each row.
 */
public class MaxConsecutiveEmptySeatsInRow implements
        Comparable<MaxConsecutiveEmptySeatsInRow> {

    /** rowId holds identifier for the row and
     * maxConsEmptySeats holds maximum consecutive seats available
     * at the given rowId.
     */
    private int rowId, maxConsEmptySeats;

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

    /** Getter method for rowId.
     * @return identifier of the row.
     */
    public int getRowId() {
        return rowId;
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

    /** Method helps to sort maxConsecutiveEmptySeatsInRow array.
     * sort in descending order of maxConsEmptySeats
     * if this value is same for both object, then
     * ascending order of rowId
     * @param obj hold object of MaxConsecutiveEmptySeatsInRow.
     * @return return integer value based on comparison
     */
    public int compareTo(final MaxConsecutiveEmptySeatsInRow obj) {
        if (this.maxConsEmptySeats != obj.maxConsEmptySeats) {
            return obj.maxConsEmptySeats - this.maxConsEmptySeats;
        } else {
            return this.rowId - obj.rowId;
        }
    }
}
