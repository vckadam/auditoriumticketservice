package com.vckadam.auditoriumticketservice.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Customer serves as a central place for all actions for customer.
 */
public class Customer {

    /**firstName first name of the customer.
     * lastName last name of the customer.
     */
    private String firstName, lastName;

    /**email address of the customer.*/
    private final String email;

    /**phone number of the customer.*/
    private long phoneNum;

    /**set of all confirmation for the customer.*/
    private Set<String> confirmations;

    /**Constructor for Customer class.
     * @param emailAddress holds email address for customer
     */
    public Customer(final String emailAddress) {
        this.email = emailAddress;
        confirmations = new HashSet<String>();
    }

    /**Getter method for email.
     * @return a email address of customer.
     */
    public String getEmail() {
        return email;
    }

    /**Getter method for firstName.
     * @return the first name of customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**Setter method for firstName.
     * @param fName the first name of customer.
     */
    public void setFirstName(final String fName) {
        this.firstName = fName;
    }

    /**Getter method for lastName.
     * @return last name of customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**Setter method for lastName.
     * @param lName the last name of the customer.
     */
    public void setLastName(final String lName) {
        this.lastName = lName;
    }

    /**Getter method for phoneNum.
     * @return phone number of customer.
     */
    public long getPhoneNum() {
        return phoneNum;
    }

    /**Setter method for phoneNum.
     * @param phoneNumber phone number of the customer.
     */
    public void setPhoneNum(final long phoneNumber) {
        this.phoneNum = phoneNumber;
    }

    /**Getter method for confirmations.
     * @return all confirmation codes for the customer.
     */
    public Set<String> getConfirmations() {
        return confirmations;
    }

    /**Setter method to add new confirmations.
     * @param conformationCode the confirmation code for conformed seats.
     */
    public void addConfirmations(final String conformationCode) {
        this.confirmations.add(conformationCode);
    }
}
