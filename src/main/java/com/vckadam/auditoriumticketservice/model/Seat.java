package com.vckadam.auditoriumticketservice.model;

/**
 * Seat serves as a place for all actions those are performed on the seat.
 */
public class Seat {

    /**row id for the seat.*/
    private final char rowId;

    /**column id for the seat.*/
    private final int columnId;

    /**type for the seat including held, conformed and open.*/
    private SeatType seatType;

    /**Constructor for Seat class.
     * @param rowNum holds row id for the seat.
     * @param columnNum holds column id for the seat.
     */
    public Seat(final char rowNum, final int columnNum) {
        this.rowId = rowNum;
        this.columnId = columnNum;
        this.seatType = SeatType.OPEN;
    }

    /**Getter method for seatType.
     * @return type of the seat.
     */
    public SeatType getSeatType() {
       return seatType;
    }

    /**Setter method for seatType.
     * @param seatTypeVal type of the seat.
     */
    public void setSeatType(final SeatType seatTypeVal) {
       this.seatType = seatTypeVal;
    }

    /**Getter method for rowId.
     * @return row id of the seat.
     */
    public char getRowId() {
       return rowId;
    }

    /**Getter method for columnId.
     * @return a column id of the seat.
     */
    public int getColumnId() {
       return columnId;
    }
}
