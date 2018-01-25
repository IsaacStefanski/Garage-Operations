package ics.parkinggarageapp;

/**
 * All implementations of ParkingFeeCalculator must include a method to
 * calculate the parking fee.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of ParkingFeeCalculator interface</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.00
 * @since 1.8
 */
public interface ParkingFeeCalculator {
    public abstract double calcParkingFee(double hrsParked);
}