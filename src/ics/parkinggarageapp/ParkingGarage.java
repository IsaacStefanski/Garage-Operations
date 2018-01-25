package ics.parkinggarageapp;

import java.util.Objects;

/**
 * A ParkingGarage consists of a name, address, and parking spaces. It also has
 * methods to keep track of full spaces and available spaces.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of ParkingGarage class</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.01
 * @since 1.8
 */
public class ParkingGarage {
    private String garageName;
    private String garageAddress;
    private int totalParkingSpaces;
    private int fullParkingSpaces;
    private int emptyParkingSpaces;

    /**
     * Constructor to create a ParkingGarage object
     * 
     * @param garageName a valid <code>String</code> that is not null with the
     * name of the garage
     * @param garageAddress a valid <code>String</code> that is not null with the
     * address for the garage
     * @param totalParkingSpaces an <code>int</code> that is the number of total
     * parking spaces in the garage
     */
    public ParkingGarage(String garageName, String garageAddress, int totalParkingSpaces) {
        setGarageName(garageName);
        setGarageAddress(garageAddress);
        setTotalParkingSpaces(totalParkingSpaces);
        setFullParkingSpaces(0);
        setEmptyParkingSpaces(totalParkingSpaces);
    }
    
    /**
     * Adds one to the amount of full parking spaces and removes one from the
     * amount of empty parking spaces
     */
    public final void fillParkingSpace(){
        fullParkingSpaces++;
        emptyParkingSpaces--;
    }
    
    /**
     * Removes one from the amount of full parking spaces and adds one to the
     * amount of empty parking spaces
     */
    public final void leaveParkingSpace(){
        fullParkingSpaces--;
        emptyParkingSpaces++;
    }

    public final String getGarageName() {
        return garageName;
    }

    public final void setGarageName(String garageName) throws IllegalArgumentException {
        if(garageName != null && garageName.length() > 0){
            this.garageName = garageName;
        } else {
            throw new IllegalArgumentException("Must enter valid Garage Name");
        }
    }

    public final String getGarageAddress() {
        return garageAddress;
    }

    public final void setGarageAddress(String garageAddress) throws IllegalArgumentException {
        if(garageAddress != null && garageName.length() > 0){
            this.garageAddress = garageAddress;
        } else {
            throw new IllegalArgumentException("Must enter valid Garage Address");
        }
    }

    public final int getTotalParkingSpaces() {
        return totalParkingSpaces;
    }

    public final void setTotalParkingSpaces(int totalParkingSpaces) throws IllegalArgumentException {
        if(totalParkingSpaces > 0){
            this.totalParkingSpaces = totalParkingSpaces;
        } else {
            throw new IllegalArgumentException("Must enter valid number of total spaces available (More than 0)");
        }
    }

    public final int getFullParkingSpaces() {
        return fullParkingSpaces;
    }

    public final void setFullParkingSpaces(int fullParkingSpaces) throws IllegalArgumentException {
        if(fullParkingSpaces >= 0){
            this.fullParkingSpaces = fullParkingSpaces;
        } else {
            throw new IllegalArgumentException("Must enter valid number of full spaces (At least 0)");
        }
    }

    public final int getEmptyParkingSpaces() {
        return emptyParkingSpaces;
    }

    public final void setEmptyParkingSpaces(int emptyParkingSpaces) throws IllegalArgumentException {
        if(emptyParkingSpaces >= 0){
            this.emptyParkingSpaces = emptyParkingSpaces;
        } else {
            throw new IllegalArgumentException("Must enter valid number of empty spaces (At least 0)");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.garageName);
        hash = 79 * hash + Objects.hashCode(this.garageAddress);
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
        final ParkingGarage other = (ParkingGarage) obj;
        if (!Objects.equals(this.garageName, other.garageName)) {
            return false;
        }
        if (!Objects.equals(this.garageAddress, other.garageAddress)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a <code>String</code> with the ID
     * 
     * @return a <code>String</code> with the ID
     */
    @Override
    public final String toString() {
        return garageName + " " + garageAddress + "\nTotal Parking Spaces: " + totalParkingSpaces +
                "\nFull Parking Spaces: " + fullParkingSpaces + "\nEmpty Parking Spaces: " + emptyParkingSpaces;
    }
}