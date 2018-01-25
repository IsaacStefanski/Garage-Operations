package ics.parkinggarageapp;

/**
 * A MinNoMaxFeeCalc is an implementation of <code>ParkingFeeCalculator</code>.
 * If a vehicle is parked 2 hours or less, only the minimum fee is charged.
 * If a vehicle is parked more than 2 hours, a fee is charged for each
 * hour or part of an hour with no maximum fee.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of MinNoMaxFeeCalc class</li>
 * <li>2017-11-10: Revise fees to increase flexibility</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.02
 * @since 1.8
 */
public class MinNoMaxFeeCalc implements ParkingFeeCalculator {
    private double minFee;
    private double addFee;

    /**
     * Constructor to create a MinNoMaxFeeCalc variant of a 
     * <code>ParkingFeeCalculator</code>
     * 
     * @param minFee a <code>double</code> with the minimum fee charged
     * @param addFee a <code>double</code> with amount charged beyond the time
     * allowed for the minimum to be charged
     */
    public MinNoMaxFeeCalc(double minFee, double addFee) {
        setMinFee(minFee);
        setAdditionalTimeFee(addFee);
    }

    /**
     * Calculates and returns the fee owed for the time parked
     * 
     * @param hrsParked a <code>double</code> with the amount of time parked in hours
     * @return a <code>double</code> that is the amount owed
     */
    @Override
    public final double calcParkingFee(double hrsParked) {
        if(hrsParked <= 2.0){
            return minFee;
        } else {
            double timeBeyondTwoHours = hrsParked - 2.0;
            int integer = (int)timeBeyondTwoHours;
            double decimal = (10 * timeBeyondTwoHours - 10 * integer)/10;
            
            if(decimal > 0){
                return minFee + (addFee * integer) + addFee;
            } else {
                return minFee + (addFee * integer);
            }
        }
    }
    
    public final double getMinFee(){
        return minFee;
    }
    
    public final void setMinFee(double minFee) throws IllegalArgumentException {
        if(minFee > 0.00){
            this.minFee = minFee;
        } else {
            throw new IllegalArgumentException("Sorry, that amount is invalid");
        }
    }
    
    public final double getAdditionalTimeFee(){
        return addFee;
    }
    
    public final void setAdditionalTimeFee(double addFee) throws IllegalArgumentException {
        if(addFee > 0.00){
            this.addFee = addFee;
        } else {
            throw new IllegalArgumentException("Sorry, that amount is invalid");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.minFee) ^ (Double.doubleToLongBits(this.minFee) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.addFee) ^ (Double.doubleToLongBits(this.addFee) >>> 32));
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
        final MinNoMaxFeeCalc other = (MinNoMaxFeeCalc) obj;
        if (Double.doubleToLongBits(this.minFee) != Double.doubleToLongBits(other.minFee)) {
            return false;
        }
        if (Double.doubleToLongBits(this.addFee) != Double.doubleToLongBits(other.addFee)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a <code>String</code> with basic information about the fee calculator
     * 
     * @return a <code>String</code> with basic information about the fee calculator
     */
    @Override
    public final String toString() {
        return "Min. Fee: $" + minFee + " | Additional Fee: $" + addFee + "/hr";
    }
}