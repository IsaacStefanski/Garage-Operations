package ics.parkinggarageapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac
 */
public class MinNoMaxFeeCalcTest {
    
    public MinNoMaxFeeCalcTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCalcParkingFeeLessThanTwoHours() {
        MinNoMaxFeeCalc calc = new MinNoMaxFeeCalc(1.50, 0.75);
        double fee = calc.calcParkingFee(1.0);
        assertTrue(fee == 1.5);
    }
    
    @Test
    public void testCalcParkingFeeMoreThanTwoHours() {
        MinNoMaxFeeCalc calc = new MinNoMaxFeeCalc(1.50, 0.75);
        double fee = calc.calcParkingFee(2.5);
        assertTrue(fee == 2.25);
    }
}