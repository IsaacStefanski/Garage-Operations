package ics.parkinggarageapp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac
 */
public class ManagementReportGeneratorTest {
    
    public ManagementReportGeneratorTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionCaughtWithNegativeValuesForReport() throws IllegalArgumentException, IOException, EncodingException {
        try {
            ManagementReportGenerator rpt = new ManagementReportGenerator(null, null, null, null);
            rpt.updateTotals(-2.2, -4.4);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}