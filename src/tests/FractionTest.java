package src.tests;


import org.junit.jupiter.api.Test;
import src.Fraction;

import static org.junit.jupiter.api.Assertions.*;

public class FractionTest {

    /**
     * Testing Numerator
     */
    @Test
    public void testGetNumerator_1() {assertEquals(3, new Fraction(3, 4).getNumerator());}

    @Test
    public void testGetNumerator_2() {assertEquals(5, new Fraction(5).getNumerator());}

    @Test
    public void testGetNumerator_Zero() {assertEquals(0, new Fraction(0, 3).getNumerator());}

    @Test
    public void testGetNumerator_Float() {assertEquals(67, new Fraction(3.35f, 2).getNumerator());}

    @Test
    public void testGetNumerator_Double() {assertEquals(569, new Fraction(0.05688, 4).getNumerator());}

    /**
     * Testing Denominator
     */
    @Test
    public void testGetDenominator_1() {assertEquals(4, new Fraction(3, 4).getDenominator());}

    @Test
    public void testGetDenominator_2() {assertEquals(1, new Fraction(5).getDenominator());}

    @Test
    public void testGetDenominator_Zero() {assertEquals(1, new Fraction(0, 3).getDenominator());}

    @Test
    public void testGetDenominator_Float() {assertEquals(20, new Fraction(3.35f, 2).getDenominator());}

    @Test
    public void testGetDenominator_Double() {assertEquals(10000, new Fraction(0.05688, 4).getDenominator());}


    /**
     * Testing Sign
     */
    @Test
    public void testGetSign_Positive() {assertEquals(1, new Fraction(5, 2).sign());}

    @Test
    public void testGetSign_Zero() {assertEquals(0, new Fraction(0.00f, 2).sign());}

    @Test
    public void testGetSign_Negative() {assertEquals(-1, new Fraction(2, -3).sign());}


    /**
     * Testing String
     */
    @Test
    public void testToString() {assertEquals("<3 / 4>", new Fraction(3, 4).toString());}

    /**
     * Testing Simplification
     */
    @Test
    public void testSimplification() {
        Fraction frac = new Fraction(20, -16);
        assertEquals(-5, frac.getNumerator());
        assertEquals(4, frac.getDenominator());
    }

    /**
     * Test Equals
     */
    @Test
    public void testEqualsSameObj() {
        Fraction fracA = new Fraction(3, 2);
        assertEquals(fracA, fracA);
    }

    @Test
    public void testEqualsNull() {assertNotEquals(new Fraction(3, 2), null);}

    @Test
    public void testEqualsDifferentNum() {assertNotEquals(new Fraction(3, 2), new Fraction(4, 2));}

    @Test
    public void testEqualsDifferentFormat() {assertEquals(new Fraction(6, 4), new Fraction(1.49, 1));}

    /**
     * Test Additive Inverses
     */
    @Test
    public void testGetAdditiveInverse() {
        assertEquals(new Fraction(-5, 4), new Fraction(5, 4).getAdditiveInverse());
    }

    @Test
    public void testInvertAdditive() {
        Fraction f = new Fraction(5, 4);
        f.invertAdditive();
        assertEquals(new Fraction(-5, 4), f);
    }

    /**
     * Test Multiplicative Inverses
     */
    @Test
    public void testGetMultiplicativeInverse() {
        assertEquals(new Fraction(4, 5), new Fraction(5, 4).getMultiplicativeInverse());
    }

    @Test
    public void testInvertMultiplicative() {
        Fraction f = new Fraction(5, 4);
        f.invertMultiplicative();
        assertEquals(new Fraction(4, 5), f);
    }

    /**
     * Test Adding
     */
    @Test
    public void testAdd() {
        Fraction f = new Fraction(1, 2);
        f.add(new Fraction(-1, 3));
        assertEquals(new Fraction(1, 6), f);
    }

    @Test
    public void testAddStatic() {assertEquals(new Fraction(1, 6), Fraction.add(new Fraction(-1, 3), new Fraction(1, 2)));}

    /**
     * Test Subtract
     */
    @Test
    public void testSubtract() {
        Fraction f = new Fraction(9, 10);
        f.subtract(new Fraction(2, 5));
        assertEquals(new Fraction(1, 2), f);
    }

    @Test
    public void testSubtractStatic() {assertEquals(new Fraction(1, 2), Fraction.subtract(new Fraction(9, 10), new Fraction(2, 5)));}

    /**
     * Test Multiply
     */
    @Test
    public void testMultiply() {
        Fraction f = new Fraction(2, 3);
        f.multiply(new Fraction(4, 5));
        assertEquals(new Fraction(8, 15), f);
    }

    @Test
    public void testMultiplyStatic() {assertEquals(new Fraction(8, 15), Fraction.multiply(new Fraction(2, 3), new Fraction(4, 5)));}

    /**
     * Test Divide
     */
    @Test
    public void testDivide() {
        Fraction f = new Fraction(1, 4);
        f.divide(new Fraction(-1, 2));
        assertEquals(new Fraction(-1, 2), f);
    }

    @Test
    public void testDivideStatic() {assertEquals(new Fraction(-1, 2), Fraction.divide(new Fraction(1, 4), new Fraction(-1, 2)));}

    /**
     * Test CompareTo
     */
    @Test
    public void testCompareTo_LessThan() {assertEquals(1, new Fraction(1, 2).compareTo(new Fraction(-1, 2)));}

    @Test
    public void testCompareTo_Equal() {assertEquals(0, new Fraction(1, 2).compareTo(new Fraction(1, 2)));}

    @Test
    public void testCompareTo_GreaterThan() {assertEquals(-1, new Fraction(-1, 2).compareTo(new Fraction(1, 2)));}

    /**
     * Test Compare
     */
    @Test
    public void testCompare_LessThan() {assertEquals(1, Fraction.compare(new Fraction(1, 2), new Fraction(-1, 2))) ;}

    @Test
    public void testCompare_Equal() {assertEquals(0, Fraction.compare(new Fraction(1, 2), new Fraction(1, 2)));}

    @Test
    public void testCompare_GreaterThan() {assertEquals(-1, Fraction.compare(new Fraction(-1, 2), new Fraction(1, 2)));}

    /**
     * Test Get Values
     */
    @Test
    public void testGetValue() {assertEquals(new Fraction(9, 6), new Fraction(6, 4).getValue());}

    @Test
    public void testDoubleValue() {assertEquals(0.42857142857142855, new Fraction(3, 7).doubleValue());}

    @Test
    public void testFloatValue() {assertEquals(0.42857143f, new Fraction(3, 7).floatValue());}

    @Test
    public void testIntValue() {assertEquals(-3, new Fraction(-10, 3).intValue());}

    /**
     * Test Zero
     */
    @Test
    public void testIsZeroTrue() {assertTrue(new Fraction(0, 3).isZero());}

    @Test
    public void testIsZeroFalse() {assertFalse(new Fraction(1, 3).isZero());}


    /**
     * Test Exponentiation
     */
    @Test
    public void testPowPositive() {assertEquals(new Fraction(-8, 27), new Fraction(-2, 3).pow(3));}

    @Test
    public void testPowNegative() {assertEquals(new Fraction(8, 1), new Fraction(1, 2).pow(-3));}

    @Test
    public void testPowZero() {assertEquals(Fraction.ONE, new Fraction(-2, 3).pow(0));}



    @Test
    public void testArithmetic() {
        Fraction frac1 = new Fraction(-20, -16).getAdditiveInverse();
        Fraction frac2 = new Fraction(-3, -5);
        Fraction frac3 = new Fraction(2, 1).pow(3).getMultiplicativeInverse();
        Fraction frac4 = new Fraction(-7, 8);

        Fraction res = Fraction.add(Fraction.multiply(frac1, frac2), Fraction.multiply(frac3, frac4));

        assertEquals(-55, res.getNumerator());
        assertEquals(64, res.getDenominator());
    }
}
