package ics.parkinggarageapp;

import edu.wctc.advjava.ics.dateutilities.DateUtilities;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * The Ticket is printed by an <code>EntryStation</code> before a vehicle parks.
 * It contains a vehicle ID and entry time. A random amount of time parked is
 * generated and added to the entry time to create an exit time that can be used
 * by the <code>ParkingFeeCalculator</code> when the vehicle exits. Date and time
 * are handled with a custom <code>DateUtilities</code> API based on <b>Java 8</b>.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of Ticket class</li>
 * <li>2017-11-10: Implement custom DateUtilities API</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * <li>2017-12-12: Create and implement PrintMedia interface</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.03
 * @since 1.8
 */
public class Ticket implements PrintMedia {
    private static int ticketNum;
    private Vehicle vehicle;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double timeParked;
    private DateUtilities util;
    
    /**
     * Constructor for a Ticket that is printed for the <code>Vehicle</code> at
     * time of entry to the <code>ParkingGarage</code>
     * 
     * @param vehicle a valid <code>Vehicle</code> that is not null
     */
    public Ticket(Vehicle vehicle){
        setUtil(new DateUtilities());
        ticketNum++;
        setEntryTime(util.now());
        generateExitTime();
        setVehicle(vehicle);
        setTimeParked(calcTimeParked());
    }

    /**
     * Formats a <code>Ticket</code> to prepare for printing
     * 
     * @return a <code>Ticket</code> as a <code>String</code>
     */
    @Override
    public final String formatForPrinting() {
        String s = "";
        s += "\n******************************************";
        s += "\nTicket #: " + ticketNum;
        s += "\nVehicle ID: " + vehicle.getVehicleID();
        s += "\n";
        s += "\nEntry Time: " + util.format(entryTime, "MM-dd-yyyy hh:mm");
        s += "\n";
        s += "\nPLACE THIS TICKET ON DASHBOARD";
        s += "\n******************************************";
        return s;
    }
    
    /**
     * Generates a random amount of time to better simulate vehicles parked in
     * a garage for varying, reasonable amounts of time
     * 
     * @return a random <code>LocalDateTime</code> that is after the entry time
     */
    private final void generateExitTime(){
        double randomHours = Math.random() * 23;
        double randomMinutes = Math.random() * 59;
        double addMins = (randomHours * 60) + randomMinutes;
        setExitTime(entryTime.plusMinutes((long)addMins));
    }
    
    /**
     * Uses the custom <code>DateUtilities</code> API to calculate hours between
     * entry time and exit time
     * 
     * @return a <code>double</code> that is the difference in hours
     */
    private final double calcTimeParked(){
        Map<String, String> timeHrMin = util.findHoursAndMinutesBetween(entryTime, exitTime);
        double hrs = Double.parseDouble(timeHrMin.get("hours"));
        double mins = Double.parseDouble(timeHrMin.get("minutes"));
        return hrs + (mins/60);
    }

    public final static int getTicketNum(){
        return ticketNum;
    }

    public final static void setTicketNum(int ticketNum) throws IllegalArgumentException {
        if(ticketNum >= 0){
            Ticket.ticketNum = ticketNum;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid ticket number");
        }
    }

    public final Vehicle getVehicle(){
        return vehicle;
    }

    public final void setVehicle(Vehicle vehicle) throws IllegalArgumentException {
        if(vehicle != null){
            this.vehicle = vehicle;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid vehicle");
        }
    }

    public final LocalDateTime getEntryTime() {
        return entryTime;
    }

    public final void setEntryTime(LocalDateTime entryTime) throws IllegalArgumentException {
        if(entryTime == null){
            throw new IllegalArgumentException("Entry time must be a valid LocalDateTime and cannot be null");
        }
        this.entryTime = entryTime;
    }

    public final LocalDateTime getExitTime() {
        return exitTime;
    }

    public final void setExitTime(LocalDateTime exitTime) throws IllegalArgumentException {
        if(exitTime == null){
            throw new IllegalArgumentException("Exit time must be a valid LocalDateTime and cannot be null");
        }
        this.exitTime = exitTime;
    }

    public final DateUtilities getUtil() {
        return util;
    }

    public final void setUtil(DateUtilities util) throws IllegalArgumentException {
        if(util == null){
            throw new IllegalArgumentException("Date utility cannot be null");
        }
        this.util = util;
    }

    public final double getTimeParked(){
        return timeParked;
    }

    public final void setTimeParked(double timeParked) throws IllegalArgumentException {
        if(timeParked < 0.0){
            throw new IllegalArgumentException("Time parked must be at least 0.0");
        }
        this.timeParked = timeParked;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.vehicle);
        hash = 53 * hash + Objects.hashCode(this.entryTime);
        hash = 53 * hash + Objects.hashCode(this.exitTime);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.timeParked) ^ (Double.doubleToLongBits(this.timeParked) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.util);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (Double.doubleToLongBits(this.timeParked) != Double.doubleToLongBits(other.timeParked)) {
            return false;
        }
        if (!Objects.equals(this.vehicle, other.vehicle)) {
            return false;
        }
        if (!Objects.equals(this.entryTime, other.entryTime)) {
            return false;
        }
        if (!Objects.equals(this.exitTime, other.exitTime)) {
            return false;
        }
        if (!Objects.equals(this.util, other.util)) {
            return false;
        }
        return true;
    }    
    
    /**
     * Prints a <code>Ticket</code> to the console to simulate a printed ticket
     * that a driver would put on the dash when entering a parking garage
     * 
     * @return a <code>Ticket</code> as a <code>String</code>
     */
    @Override
    public final String toString(){
        return formatForPrinting();
    }
}