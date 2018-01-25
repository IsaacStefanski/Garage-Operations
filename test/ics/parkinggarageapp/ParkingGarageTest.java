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
public class ParkingGarageTest {
    
    public ParkingGarageTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFillParkingSpaceRemovesOneAvailableSpace() {
        ParkingGarage g = new ParkingGarage("Garage", "123 Main Streeet", 10);
        g.fillParkingSpace();
        assertTrue(g.getFullParkingSpaces() == 1);
        assertTrue(g.getEmptyParkingSpaces() == 9);
    }
    
    @Test
    public void testLesveParkingSpaceAddsOneAvailableSpace() {
        ParkingGarage g = new ParkingGarage("Garage", "123 Main Streeet", 10);
        g.fillParkingSpace();
        g.leaveParkingSpace();
        assertTrue(g.getFullParkingSpaces() == 0);
        assertTrue(g.getEmptyParkingSpaces() == 10);
    }
}