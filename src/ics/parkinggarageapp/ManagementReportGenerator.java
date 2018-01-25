package ics.parkinggarageapp;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * A ManagementReportGenerator creates and displays reports that include total
 * time charged and total payments collected.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of ManagementReportGenerator class</li>
 * <li>2017-11-14: Add unique identifier to differentiate between report generators</li>
 * <li>2017-12-15: Add capability to output reports to a GUI (<code>ParkingGarageOperationsWindow</code>)</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.02
 * @since 1.8
 */
public class ManagementReportGenerator {
    private static int rptGenNum;
    private String rptGenID;
    private double totalTimeCharged;
    private double totalPaymentsCollected;
    private TicketDatabase ticketDB;
    private File totalsFile;
    private TextFileService fileService;
    private double totalTime;
    private double totalRevenue;
    private ParkingGarageOperationsWindow win;
    
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    
    /**
     * Constructs a ManagementReportGenerator, incrementing rptGenNum and setting the ID
     */
    public ManagementReportGenerator(TicketDatabase ticketDB, File totalsFile, TextFileService fileService, ParkingGarageOperationsWindow win) throws Exception {
        rptGenNum++;
        setRptGenID("RPTG" + rptGenNum);
        setTicketDB(ticketDB);
        setTotalsFile(totalsFile);
        setFileService(fileService);
        extractTotalsFromFile(totalsFile, fileService);
        setWin(win);
    }
    
    /**
     * Generate a report of running totals
     * 
     * @return a running totals report as a formatted <code>String</code>
     */
    public final String outputRunningTotals() {
        return generateRunningTotalsReport();
    }
    
    /**
     * Generate a report of issued tickets
     * 
     * @return a report of issued tickets as a formatted <code>String</code>
     */
    public final String outputIssuedTickets() {
        return ticketDB.generateIssuedTicketsReport();
    }
    
    /**
     * Updates running totals for time charged and payments collected
     * 
     * @param time a <code>double</code> with the amount of time parked in hours
     * (3.5 hours instead of 3:30)
     * @param revenue a <code>double</code> with the amount of money collected /
     * fees that have been paid
     * @throws IllegalArgumentException if time or revenue are negative
     */
    public final void updateTotals(double time, double revenue) throws IllegalArgumentException, IOException, EncodingException, Exception{
        if(time < 0.0 || revenue < 0.0){
            throw new IllegalArgumentException("Sorry, you have attempted to use invalid data for a report");
        }
        extractTotalsFromFile(totalsFile, fileService);
        setTotalTimeCharged(getTotalTimeCharged() + time);
        setTotalPaymentsCollected(getTotalPaymentsCollected() + revenue);
        saveTotalsToFile(totalsFile, fileService);
        win.getTotalsReportOut().setText(generateRunningTotalsReport());
        win.getTicketsOut().setText(ticketDB.generateIssuedTicketsReport());
    }
    
    /**
     * Generates a report of the running totals for the garage, showing the
     * hours charged and the revenue collected from fees
     * 
     * @return a report as a formatted <code>String</code>
     */
    public final String generateRunningTotalsReport() {
        DecimalFormat decFormat = new DecimalFormat("#.00");
        String s ="";
        s += "\nREPORT: RUNNING TOTALS";
        s += "\nTotal Time Charged: " + decFormat.format(totalTimeCharged) + " hrs." + 
                "\nTotal Payments Collected: " + currencyFormatter.format(totalPaymentsCollected);
        return s;
    }
    
    private final void extractTotalsFromFile(File file, TextFileService fileService) throws Exception {
        List<String> list = fileService.readFileToList(file);
        if(list.size() > 0){
            setTotalTime(Double.parseDouble(list.get(0)));
            setTotalRevenue(Double.parseDouble(list.get(1)));
        } else {
            //Initial run, requires new file, start totals at 0.0
            setTotalTime(0.0);
            setTotalRevenue(0.0);
        }        
        setTotalTimeCharged(totalTime);
        setTotalPaymentsCollected(totalRevenue);
    }
    
    private final void saveTotalsToFile(File file, TextFileService fileService) throws Exception, IOException, EncodingException {
        DecimalFormat decFormat = new DecimalFormat("#.00");
        List<Map<String, String>> totals = new ArrayList();
        Map<String, String> totalsMap = new LinkedHashMap<>();
        totalsMap.put("time", decFormat.format(totalTimeCharged));
        totalsMap.put("revenue", totalPaymentsCollected+"");
        totals.add(totalsMap);
        fileService.writeFile(totals, file);
    }
    
    public final static int getRptGenNum() {
        return rptGenNum;
    }

    public final static void setRptGenNum(int entryStationNum) throws IllegalArgumentException {
        if(rptGenNum >= 0){
            ManagementReportGenerator.rptGenNum = rptGenNum;
        } else {
            throw new IllegalArgumentException("Invalid ID number");
        }
    }

    public final String getRptGenID() {
        return rptGenID;
    }

    public final void setRptGenID(String rptGenID) {
        if(rptGenID != null && rptGenID.length() > 0 && rptGenID.startsWith("RPTG")){
            this.rptGenID = rptGenID;
        } else {
            throw new IllegalArgumentException("Sorry, that ID is invalid");
        }
    }

    public final double getTotalTimeCharged() {
        return totalTimeCharged;
    }

    public final void setTotalTimeCharged(double totalTimeCharged) throws IllegalArgumentException {
        if(totalTimeCharged >= 0.0){
            this.totalTimeCharged = totalTimeCharged;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid value for total time charged");
        }
    }

    public final double getTotalPaymentsCollected() {
        return totalPaymentsCollected;
    }

    public final void setTotalPaymentsCollected(double totalPaymentsCollected) throws IllegalArgumentException {
        if(totalPaymentsCollected >= 0.0){
            this.totalPaymentsCollected = totalPaymentsCollected;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid value for total payments collected");
        }
    }

    public final TicketDatabase getTicketDB() {
        return ticketDB;
    }

    public final void setTicketDB(TicketDatabase ticketDB) throws IllegalArgumentException {
        if(ticketDB != null){
            this.ticketDB = ticketDB;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid database");
        }
    }
    
    public final File getTotalsFile() {
        return totalsFile;
    }

    public final void setTotalsFile(File totalsFile) throws IllegalArgumentException {
        if(totalsFile != null){
            this.totalsFile = totalsFile;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid file");
        }
    }
    
    public final File getFileService() {
        return totalsFile;
    }

    public final void setFileService(TextFileService fileService) throws IllegalArgumentException {
        if(fileService != null){
            this.fileService = fileService;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid file service");
        }
    }

    public final double getTotalTime() {
        return totalTime;
    }

    public final void setTotalTime(double totalTime) throws IllegalArgumentException {
        if(totalTime >= 0){
            this.totalTime = totalTime;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid value for time");
        }
    }

    public final double getTotalRevenue() {
        return totalRevenue;
    }

    public final void setTotalRevenue(double totalRevenue) throws IllegalArgumentException {
        if(totalRevenue >= 0){
            this.totalRevenue = totalRevenue;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid value for revenue");
        }
    }

    public final ParkingGarageOperationsWindow getWin() {
        return win;
    }

    public final void setWin(ParkingGarageOperationsWindow win) throws IllegalArgumentException {
        if(win == null){
            throw new IllegalArgumentException("Sorry, configuration window cannot be null");
        }
        this.win = win;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.rptGenID);
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
        final ManagementReportGenerator other = (ManagementReportGenerator) obj;
        if (!Objects.equals(this.rptGenID, other.rptGenID)) {
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
        return "Management Report Generator ID: " + rptGenID;
    }
}