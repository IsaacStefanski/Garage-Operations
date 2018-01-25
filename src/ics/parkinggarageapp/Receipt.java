package ics.parkinggarageapp;

import edu.wctc.advjava.ics.dateutilities.DateUtilities;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

/**
 * The Receipt is printed when a vehicle leaves, using information from the
 * ticket which was received at entry. Date and time information is handled
 * using a custom <code>DateUtilities</code> API based on <b>Java 8</b>.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of Receipt class</li>
 * <li>2017-11-09: Add custom DateUtilities API</li>
 * <li>2017-11-10: Modify handling of <code>Ticket</code> information</li>
 * <li>2017-11-14: Add overrides to equals() and hashCode()</li>
 * <li>2017-12-12: Create and implement PrintMedia interface</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.04
 * @since 1.8
 */
public class Receipt implements PrintMedia {
    private static int receiptNum = 0;
    private LocalDateTime exitTime;
    private ParkingGarage garage;
    private Ticket ticket;
    private ParkingFeeCalculator feeCalc;
    private DateUtilities util;
    
    /**
     * Constructor for a Receipt containing information about the garage, the
     * time parked, and the fee owed for that time
     * 
     * @param garage a valid <code>ParkingGarage</code> that is not null
     * @param ticket a valid <code>Ticket</code> that is not null
     * @param feeCalc a valid <code>ParkingFeeCalculator</code> that is not null
     */
    public Receipt(ParkingGarage garage, Ticket ticket, ParkingFeeCalculator feeCalc){
        util = new DateUtilities();
        receiptNum++;
        setParkingGarage(garage);
        setTicket(ticket);
        setFeeCalc(feeCalc);
        setExitTime(ticket.getExitTime());
    }
    /**
     * Formats a <code>Receipt</code> to prepare for printing
     * 
     * @return a <code>Receipt</code> as a <code>String</code>
     */
    @Override
    public String formatForPrinting() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MM-dd-yyyy");
        DecimalFormat decFormat = new DecimalFormat("#.00");

        String s = "";
        s += "\n------------------------------------------";
        s += "\nReceipt #: " + receiptNum + "    " + util.format(exitTime, "MM-dd-yyyy hh:mm");;
        s += "\n";
        s += "\n" + garage.getGarageName();
        s += "\n" + garage.getGarageAddress();
        s += "\n";
        s += "\nVehicle ID: " + ticket.getVehicle().getVehicleID();
        s += "\nHours Parked: " + decFormat.format(ticket.getTimeParked());
        s += "\n";
        s += "\nTOTAL DUE: " + currencyFormatter.format(calcTotalDue());
        s += "\n------------------------------------------";
        return s;
    }
    
    /**
     * Calls the calcParkingFee() method of the fee calculator to get the fee using
     * the time parked from the ticket
     * 
     * @return a <code>double</code> that is the fee owed
     */
    public final double calcTotalDue(){
        return feeCalc.calcParkingFee(ticket.getTimeParked());
    }
    
    public final static int getReceiptNum() {
        return receiptNum;
    }

    public final ParkingGarage getParkingGarage() {
        return garage;
    }

    public final void setParkingGarage(ParkingGarage garage) throws IllegalArgumentException {
        if(garage != null){
            this.garage = garage;
        } else {
            throw new IllegalArgumentException("Sorry, the parking garage cannot be null");
        }
    }

    public final Ticket getTicket() {
        return ticket;
    }

    public final void setTicket(Ticket ticket) throws IllegalArgumentException {
        if(ticket != null){
            this.ticket = ticket;
        } else {
            throw new IllegalArgumentException("Sorry, the ticket cannot be null");
        }
    }
    
    public final ParkingFeeCalculator getFeeCalc() {
        return feeCalc;
    }

    public final void setFeeCalc(ParkingFeeCalculator feeCalc) throws IllegalArgumentException {
        if(feeCalc != null){
            this.feeCalc = feeCalc;
        } else {
            throw new IllegalArgumentException("Sorry, you have attempted to use an invalid parking fee calculator");
        }
    }

    public final LocalDateTime getExitTime() {
        return exitTime;
    }

    public final void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public final ParkingGarage getGarage() {
        return garage;
    }

    public final void setGarage(ParkingGarage garage) {
        this.garage = garage;
    }

    public final DateUtilities getUtil() {
        return util;
    }

    public final void setUtil(DateUtilities util) {
        this.util = util;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.exitTime);
        hash = 37 * hash + Objects.hashCode(this.garage);
        hash = 37 * hash + Objects.hashCode(this.ticket);
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
        final Receipt other = (Receipt) obj;
        if (!Objects.equals(this.exitTime, other.exitTime)) {
            return false;
        }
        if (!Objects.equals(this.garage, other.garage)) {
            return false;
        }
        if (!Objects.equals(this.ticket, other.ticket)) {
            return false;
        }
        return true;
    }
    
    /**
     * Prints a formatted receipt to the console, containing a receipt number,
     * the date and time leaving, garage information, vehicle ID, total hours
     * parked, and the fee owed
     * 
     * @return a formatted receipt as a <code>String</code>
     */
    @Override
    public final String toString(){
        return formatForPrinting();
    }
}