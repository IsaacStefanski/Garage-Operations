package ics.parkinggarageapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac
 */
public class EntryStationTest {
    
    public EntryStationTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRaiseArmSetsLoweredToFalse() {
        EntryStation es = new EntryStation(null, "", null);
        es.raiseArm();
        assertTrue(!es.isArmLowered());
    }
    
    @Test
    public void testLowerArmSetsLoweredToTrue() {
        EntryStation es = new EntryStation(null, "", null);
        es.lowerArm();
        assertTrue(es.isArmLowered());
    }
}