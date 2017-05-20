package com.vckadam.auditoriumticketservice.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SeatHold {
	private String seatHoldId, customerEmail;
	private Date timeStamp;
	private Set<String> seatIds;
	
	public SeatHold(String seatHoldId, String customerEmail, Date timeStamp){
		this.seatHoldId = seatHoldId;
		this.customerEmail = customerEmail;
		this.timeStamp = timeStamp;
		seatIds = new HashSet<String>();
	}
	public String getSeatHoldId() {
		return seatHoldId;
	}
	public void setSeatHoldId(String seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Set<String> getSeatIds() {
		return seatIds;
	}
	public void addSeatIds(String seatId) {
		seatIds.add(seatId);
	}
	public void removeSeatIds(String seatId) {
		seatIds.remove(seatId);
	}
}
