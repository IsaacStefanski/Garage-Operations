package ics.parkinggarageapp;

import java.util.Objects;

/**
 * The ReceiptPrinter is an implementation of <code>Printer</code> that prints
 * a <code>Receipt</code> to the console.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of ReceiptPrinter class</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.01
 * @since 1.8
 */
public class ReceiptPrinter implements Printer {
    private Receipt receipt;
    
    /**
     * Constructor for a ReceiptPrinter object
     * 
     * @param receipt a valid <code>Receipt</code> that is not null
     */
    public ReceiptPrinter(Receipt receipt){
        setReceipt(receipt);
    }
    
    /**
     * Outputs the receipt to the console to simulate a printed receipt
     */
    @Override
    public final void performPrint() {
        System.out.println(receipt.toString());
    }
    
    public final Receipt getReceipt(){
        return receipt;
    }
    
    public final void setReceipt(Receipt receipt) throws IllegalArgumentException {
        if(receipt != null){
            this.receipt = receipt;
        } else {
            throw new IllegalArgumentException("Sorry, you have a null receipt");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.receipt);
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
        final ReceiptPrinter other = (ReceiptPrinter) obj;
        if (!Objects.equals(this.receipt, other.receipt)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a <code>String</code> with information about the Receipt being printed
     * 
     * @return a <code>String</code> with information about the Receipt being printed
     */
    @Override
    public final String toString() {
        return "Receipt: " + receipt.getExitTime() + " for " + receipt.getTicket().getVehicle().getVehicleID();
    }
}