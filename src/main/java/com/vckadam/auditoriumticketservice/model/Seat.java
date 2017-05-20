package com.vckadam.auditoriumticketservice.model;

public class Seat {
	private char rowId;
	private int columnId;
	private boolean held, conform;
	
	public Seat(char rowId, int columnId) {
		this.rowId = rowId;
		this.columnId = columnId;
		held = conform = false;
	}
	public char getRowId() {
		return rowId;
	}
	public void setRowId(char rowId) {
		this.rowId = rowId;
	}
	public int getColumnId() {
		return columnId;
	}
	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}
	public boolean isHeld() {
		return held;
	}
	public void setHeld(boolean held) {
		this.held = held;
	}
	public boolean isConform() {
		return conform;
	}
	public void setConform(boolean conform) {
		this.conform = conform;
	}
	
}
