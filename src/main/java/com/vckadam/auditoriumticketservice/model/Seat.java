package com.vckadam.auditoriumticketservice.model;

import com.vckadam.auditoriumticketservice.enumerator.SeatType;

/**
 * Seat serves as a place for all actions those are performed on the seat.
 */
public class Seat {

    /**rowNo holds number of the seat. */
    private final String seatNo;

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
        this.seatNo = String.valueOf(rowNum) + "#"
            + String.valueOf(columnNum);
        this.seatType = SeatType.OPEN;
    }

    /**Getter method for seatNo.
     * @return seat number.
     */
    public String getSeatNo() {
       return this.seatNo;
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
