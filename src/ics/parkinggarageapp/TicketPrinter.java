package ics.parkinggarageapp;

import java.util.Objects;

/**
 * The TicketPrinter is an implementation of <code>Printer</code> that prints
 * a <code>Ticket</code> to the console.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of TicketPrinter class</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.01
 * @since 1.8
 */
public class TicketPrinter implements Printer {
    private Ticket ticket;
    
    /**
     * Constructor for a TicketPrinter object
     * 
     * @param ticket a valid <code>Ticket</code> that is not null
     */
    public TicketPrinter(Ticket ticket){
        setTicket(ticket);
    }
    
    /**
     * Outputs a Ticket to the console to simulate a printed ticket
     */
    @Override
    public final void performPrint() {
        System.out.println(ticket.toString());
    }
    
    public final Ticket getTciket(){
        return ticket;
    }
    
    public final void setTicket(Ticket ticket) throws IllegalArgumentException {
        if(ticket != null){
            this.ticket = ticket;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid ticket");
        }
    }  

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ticket);
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
        final TicketPrinter other = (TicketPrinter) obj;
        if (!Objects.equals(this.ticket, other.ticket)) {
            return false;
        }
        return true;
    }

    /**
     * Returns information about the <code>Ticket</code> being printed
     * 
     * @return a <code>String</code> containing information about the
     * <code>Ticket</code> being printed
     */
    @Override
    public final String toString() {
        return "Ticket: " + ticket.getExitTime() + " for " + ticket.getVehicle().getVehicleID();
    }
}