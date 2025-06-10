package src;

public class Fraction {
    /**
     * Instance variables
     */
    private int numerator;
    private int denominator;

    /**
     * Constants
     */
    public static final Fraction ZERO = new Fraction(0);
    public static final Fraction ONE = new Fraction(1);
    public static final Fraction MINUS_ONE = new Fraction(-1);


    /**
     * Constructors
     */

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {throw new IllegalArgumentException("Zero denominator");}

        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        denominator = 1;
    }

    public Fraction (float value, int decimalPrec) {
        this.denominator = 1;
        for (int i = 0; i < decimalPrec; i++) {
            denominator *= 10;
            value *= 10;
        }
        this.numerator = Math.round(value);
        simplify();
    }

    public Fraction (double value, int decimalPrec) {
        this.denominator = 1;
        for (int i = 0; i < decimalPrec; i++) {
            denominator *= 10;
            value *= 10;
        }
        this.numerator = (int) Math.round(value);
        simplify();
    }

    /**
     * Helper methods
     */
    public int sign() {
        return (numerator == 0) ? 0 : (numerator / Math.abs(numerator));
    }

    public int getNumerator() {return numerator;}

    public int getDenominator() {return denominator;}

    public Fraction getValue() {return new Fraction(numerator, denominator);}

    public String toString() {return "<" + numerator + " / " + denominator + ">";}

    private static int findGCD(int a, int b) {
        int bigger = Math.max(a, b);
        int smaller = Math.min(a, b);

        int rem = bigger % smaller;
        while (rem > 0) {
            bigger = smaller;
            smaller = rem;
            rem = bigger % smaller;
        }
        return smaller;
    }

    private void simplify() {
        if (numerator == 0) {
            denominator = 1;
            return;
        }

        int sign = (numerator > 0) ? 1 : -1;
        sign = (denominator > 0) ? sign : -sign;

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        int GCD = findGCD(numerator, denominator);

        numerator *= sign;
        numerator /= GCD;
        denominator /= GCD;
    }

    public Fraction getAdditiveInverse() {return new Fraction(-numerator, denominator);}

    public void invertAdditive() {numerator = -numerator;}

    public Fraction getMultiplicativeInverse() {return new Fraction(denominator, numerator);}

    public void invertMultiplicative() {
        int temp = denominator;
        denominator = numerator;
        numerator = temp;
    }

    public void add(Fraction frac) {
        this.numerator = this.numerator * frac.getDenominator() + this.denominator * frac.getNumerator();
        this.denominator *= frac.getDenominator();
        simplify();
    }

    public void subtract(Fraction frac) {add(frac.getAdditiveInverse());}

    public void multiply(Fraction frac) {
        this.numerator *= frac.getNumerator();
        this.denominator *= frac.getDenominator();
        simplify();
    }

    public void divide(Fraction frac) {multiply(frac.getMultiplicativeInverse());}

    public static Fraction add(Fraction frac1, Fraction frac2) {
        Fraction temp = frac1.getValue();
        temp.add(frac2);
        return temp;
    }

    public static Fraction subtract(Fraction frac1, Fraction frac2) {
        Fraction temp = frac1.getValue();
        temp.subtract(frac2);
        return temp;
    }

    public static Fraction multiply(Fraction frac1, Fraction frac2) {
        Fraction temp = frac1.getValue();
        temp.multiply(frac2);
        return temp;
    }

    public static Fraction divide(Fraction frac1, Fraction frac2) {
        Fraction temp = frac1.getValue();
        temp.divide(frac2);
        return temp;
    }

    public int compareTo(Fraction frac) {return compare(this, frac);}

    public static int compare(Fraction frac1, Fraction frac2) {
        int frac1val = frac1.getNumerator() * frac2.getDenominator();
        int frac2val = frac2.getNumerator() * frac1.getDenominator();

        return Integer.compare(frac1val, frac2val);
    }

    public double doubleValue() {
        return (double)this.getNumerator() / (double)this.getDenominator();
    }

    public float floatValue() {
        return (float)this.getNumerator() / (float)this.getDenominator();
    }

    public boolean isZero() {return numerator == 0;}

    public int intValue() {return numerator / denominator;}

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Fraction inputObj = (Fraction) obj;
        boolean equalsNumerator = numerator == inputObj.getNumerator();
        boolean equalsDenominator = denominator == inputObj.getDenominator();
        return (equalsNumerator && equalsDenominator);
    }

    public Fraction pow(int power) {
        if (power == 0) return new Fraction(1);

        int numerator = 1, denominator = 1;
        for (int i = 0; i < Math.abs(power); i++) {
            numerator *= this.getNumerator();
            denominator *= this.getDenominator();
        }

        return (power < 0) ? new Fraction(denominator, numerator) : new Fraction(numerator, denominator);
    }
}