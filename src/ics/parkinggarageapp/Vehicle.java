package ics.parkinggarageapp;

import java.util.Objects;

/**
 * A Vehicle has an ID assigned at time of entry to the parking garage and a
 * <code>Ticket</code> which is printed at the same time. With each Vehicle,
 * the variable vehicleCount is incremented and used for the ID.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of Vehicle class</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.01
 * @since 1.8
 */
public class Vehicle {
    private static int vehicleCount;
    private String licensePlate;
    private String vehicleID;
    private Ticket ticket;
    
    /**
     * Constructs a Vehicle and sets its ID to vehicleCount
     */
    public Vehicle(String licensePlate){
        vehicleCount++;
        setVehicleID("V" + vehicleCount);
        setLicensePlate(licensePlate);
    }

    public final static int getVehicleCount() {
        return vehicleCount;
    }

    public final static void setVehicleCount(int vehicleCount) throws IllegalArgumentException {
        if(vehicleCount >= 0){
            Vehicle.vehicleCount = vehicleCount;
        } else {
            throw new IllegalArgumentException("Invalid vehicle count");
        }
    }

    public final String getVehicleID() {
        return vehicleID;
    }

    public final void setVehicleID(String vehicleID) throws IllegalArgumentException {
        if(vehicleID != null && vehicleID.charAt(0) == 'V'){
            this.vehicleID = vehicleID;
        } else {
            throw new IllegalArgumentException("Invalid vehicle ID");
        }
    }
    
    public final Ticket getTicket() {
        return ticket;
    }

    public final void setTicket(Ticket ticket) throws IllegalArgumentException {
        if(ticket != null){
            this.ticket = ticket;
        } else {
            throw new IllegalArgumentException("Null ticket");
        }
    }

    public final String getLicensePlate() {
        return licensePlate;
    }

    public final void setLicensePlate(String licensePlate) {
        if(licensePlate != null && licensePlate.length() > 0 && licensePlate.length() <= 7){
            this.licensePlate = licensePlate;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid license plate");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.vehicleID);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (!Objects.equals(this.vehicleID, other.vehicleID)) {
            return false;
        }
        return true;
    }

    /**
     * Returns the vehicle ID as a <code>String</code>
     * 
     * @return the vehicle ID as a <code>String</code>
     */
    @Override
    public final String toString() {
        return "Vehicle ID: " + vehicleID + " | License Plate: " + licensePlate;
    }
}