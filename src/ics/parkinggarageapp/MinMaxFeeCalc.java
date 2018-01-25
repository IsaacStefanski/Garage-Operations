package ics.parkinggarageapp;

/**
 * A MinMaxFeeCalc is an implementation of <code>ParkingFeeCalculator</code>.
 * If a vehicle is parked 3 hours or less, only the minimum fee is charged.
 * If a vehicle is parked between 3 and 24 hours, a fee is charged for each
 * hour or part of an hour, but a vehicle's total fee will not exceed $10.00.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of MinMaxFeeCalc class</li>
 * <li>2017-11-10: Revise fees to increase flexibility</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.02
 * @since 1.8
 */
public class MinMaxFeeCalc implements ParkingFeeCalculator {
    private double minFee;
    private double addFee;
    private double maxFee;

    /**
     * Constructor to create a MinMaxFeeCalc variant of a 
     * <code>ParkingFeeCalculator</code>
     * 
     * @param minFee a <code>double</code> with the minimum fee charged
     * @param addFee a <code>double</code> with amount charged beyond the time
     * @param maxFee a <code>double</code> with the maximum amount charged
     * allowed for the minimum to be charged
     */
    public MinMaxFeeCalc(double minFee, double addFee, double maxFee) {
        setMinFee(minFee);
        setAdditionalTimeFee(addFee);
        setMaxFee(maxFee);
    }

    /**
     * Calculates and returns the fee owed for the time parked
     * 
     * @param hrsParked a <code>double</code> with the amount of time parked in hours
     * @return a <code>double</code> that is the amount owed
     */
    @Override
    public final double calcParkingFee(double hrsParked) {
        if(hrsParked <= 3.0){
            return minFee;
        } else if(hrsParked > 3.0 && hrsParked < 24.0){
            double timeBeyondThreeHours = hrsParked - 3.0;
            int integer = (int)timeBeyondThreeHours;
            double decimal = (10 * timeBeyondThreeHours - 10 * integer)/10;
            
            if(decimal > 0){
                if((minFee + (addFee * integer) + addFee) < 10.00){
                    return minFee + (addFee * integer) + addFee;
                } else {
                    return maxFee;
                }
            } else {
                if((minFee + (addFee * integer)) < 10.00){
                    return minFee + (addFee * integer) + addFee;
                } else {
                    return maxFee;
                }
            }
        } else {
            return maxFee;
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

    public final double getMaxFee() {
        return maxFee;
    }

    public final void setMaxFee(double maxFee) {
        if(maxFee > 0.00){
            this.maxFee = maxFee;
        } else {
            throw new IllegalArgumentException("Sorry, that amount is invalid");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.minFee) ^ (Double.doubleToLongBits(this.minFee) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.addFee) ^ (Double.doubleToLongBits(this.addFee) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.maxFee) ^ (Double.doubleToLongBits(this.maxFee) >>> 32));
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
        final MinMaxFeeCalc other = (MinMaxFeeCalc) obj;
        if (Double.doubleToLongBits(this.minFee) != Double.doubleToLongBits(other.minFee)) {
            return false;
        }
        if (Double.doubleToLongBits(this.addFee) != Double.doubleToLongBits(other.addFee)) {
            return false;
        }
        if (Double.doubleToLongBits(this.maxFee) != Double.doubleToLongBits(other.maxFee)) {
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
        return "Min. Fee: $" + minFee + " | Additional Fee: $" + addFee + "/hr | Max. Fee: $" + maxFee;
    }
}