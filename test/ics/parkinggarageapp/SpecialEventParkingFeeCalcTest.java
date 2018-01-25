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
public class SpecialEventParkingFeeCalcTest {
    
    public SpecialEventParkingFeeCalcTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCalcParkingFeeLessThanFourHours() {
        SpecialEventParkingFeeCalc calc = new SpecialEventParkingFeeCalc(1.80, 0.15);
        double fee = calc.calcParkingFee(1.0);
        assertTrue(fee == 1.85);
    }
    
    @Test
    public void testCalcParkingFeeMoreThanFourHours() {
        SpecialEventParkingFeeCalc calc = new SpecialEventParkingFeeCalc(1.80, 0.15);
        double fee = calc.calcParkingFee(4.5);
        assertTrue(fee == 2.0);
    }
}