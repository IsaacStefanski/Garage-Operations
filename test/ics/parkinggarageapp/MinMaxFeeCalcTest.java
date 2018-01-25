package ics.parkinggarageapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Isaac
 */
public class MinMaxFeeCalcTest {
    
    public MinMaxFeeCalcTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCalcParkingFeeLessThanThreeHours() {
        MinMaxFeeCalc calc = new MinMaxFeeCalc(2.0, 0.50, 10.00);
        double fee = calc.calcParkingFee(1.5);
        assertTrue(fee == 2.0);
    }
    
    @Test
    public void testCalcParkingFeeMoreThanThreeHours() {
        MinMaxFeeCalc calc = new MinMaxFeeCalc(2.0, 0.50, 10.00);
        double fee = calc.calcParkingFee(3.5);
        assertTrue(fee == 2.5);
    }
}