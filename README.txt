******************************************************************
About: 

Auditorium ticket service provider with following functions.

1. Find number of seats available in Auditorium.

2. Find and Hold best available seats.

3. Reserve and commit seats held by the customer.

******************************************************************
Environment:

JDK 1.8
Apache Maven 3.5

Build & Test command: mvn clean install

******************************************************************
Assumptions:

1. Every row has same number of seats.

2. Tickets can be held up till 120 seconds. 

3. One active user at a time.

4. System finds best possible seats using following approach.
   - As a user, I would like to seat with my family, So system tries to find
   all the seats together. If it's not possible, System divides the number of 
   seats value in half and continue finding process. It repeats this process
   until all the seats are allocated.
   - As a user, I would I like to seat as close as possible to the stage, so 
   system tries to find closest seats which are available in the same row.

