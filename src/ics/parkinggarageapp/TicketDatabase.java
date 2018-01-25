package ics.parkinggarageapp;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * A TicketDatabase uses key-value pairs to track which car has which ticket. It
 * logs the ticket number and the car's license plate in a <code>TreeMap</code>.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-11-30: Initial version of TicketDatabase class</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.00
 * @since 1.8
 */
public class TicketDatabase {
    private static int ticketDBnum;
    private String ticketDbId;
    private Map<String, String> tickets;
    
    /**
     * Constructor for a TicketDatabase
     */
    public TicketDatabase(){
        ticketDBnum++;
        setTicketDbId("TDB" + ticketDBnum);
        setTickets(new TreeMap<>());
    }
    
    /**
     * Adds a ticket to the database taking the ticket and the license plate of
     * the car associated with that ticket
     * 
     * @param ticket a valid <code>Ticket</code> that is not null
     * @param licensePlate a <code>String</code> that is not null and is the
     * characters of a car's license plate
     * @throws IllegalArgumentException if either value is null
     */
    public final void addTicket(Ticket ticket, String licensePlate) throws IllegalArgumentException {
        if(licensePlate == null || licensePlate.isEmpty() || ticket == null){
            throw new IllegalArgumentException("Cannot add ticket to database");
        }
        tickets.put(ticket.getVehicle().getVehicleID().replace('V', 'T'), licensePlate);
    }
    
    /**
     * Generates a report of tickets that have been issued, showing the ticket
     * number and the license plate of the car that ticket is for
     * 
     * @return a report as a formatted <code>String</code>
     */
    public final String generateIssuedTicketsReport() {
        String s = "";
        s += "\nREPORT: ISSUED TICKETS";
        s += "\n";
        s += "\nTicket #  |  License Plate";
        s += "\n--------------------------";
        Set<String> keys = tickets.keySet();
        for(String k : keys){
            s += "\n" + k + "           " + tickets.get(k);
        }
        return s;
    }

    public final static int getTicketDBnum() {
        return ticketDBnum;
    }

    public final static void setTicketDBnum(int ticketDBnum) throws IllegalArgumentException {
        if(ticketDBnum >= 0){
            TicketDatabase.ticketDBnum = ticketDBnum;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid number");
        }
    }

    public final String getTicketDbId() {
        return ticketDbId;
    }

    public final void setTicketDbId(String ticketDbId) throws IllegalArgumentException {
        if(ticketDbId != null && ticketDbId.length() > 0 && ticketDbId.startsWith("TDB")){
            this.ticketDbId = ticketDbId;
        } else {
            throw new IllegalArgumentException("Sorry, that ID is invalid");
        }
    }

    public final Map<String, String> getTickets() {
        return tickets;
    }

    public final void setTickets(Map<String, String> tickets) throws IllegalArgumentException {
        if(tickets != null){
            this.tickets = tickets;
        } else {
            throw new IllegalArgumentException("Sorry, that TicketDatabase is invalid");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.ticketDbId);
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
        final TicketDatabase other = (TicketDatabase) obj;
        if (!Objects.equals(this.ticketDbId, other.ticketDbId)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "Ticket Database" + ticketDbId;
    }
}