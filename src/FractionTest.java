package src;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FractionTest {

    @Test
    public void testGetNumerator() {
        assertEquals(3, new Fraction(3, 4).getNumerator());
    }

    @Test
    public void testGetDenominator() {
        assertEquals(4, new Fraction(3, 4).getDenominator());
    }

    @Test
    public void testToString() {
        assertEquals("<3 / 4>", new Fraction(3, 4).toString());
    }

    @Test
    public void testSimplification() {
        Fraction frac = new Fraction(20, -16);
        assertEquals(-5, frac.getNumerator());
        assertEquals(4, frac.getDenominator());
    }

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
