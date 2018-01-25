package ics.parkinggarageapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac
 */
public class ExitStationTest {
    
    public ExitStationTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testRaiseArmSetsLoweredToFalse() {
        ExitStation es = new ExitStation(null, "", null, null);
        es.raiseArm();
        assertTrue(!es.isArmLowered());
    }
    
    @Test
    public void testLowerArmSetsLoweredToTrue() {
        ExitStation es = new ExitStation(null, "", null, null);
        es.lowerArm();
        assertTrue(es.isArmLowered());
    }
}