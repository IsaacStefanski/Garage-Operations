/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics.parkinggarageapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac
 */
public class ParkingStationScreenTest {
    
    public ParkingStationScreenTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testOutputParkingInfoDisplaysMessageInDialog() {
        ParkingStationScreen s = new ParkingStationScreen();
        s.outputParkingInfo("Message");
    }
}