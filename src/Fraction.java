package src;

public class Fraction {
    public static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
            37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    int numerator;
    int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Zero denominator");
        }
        this.numerator = (denominator < 0) ? -numerator : numerator;
        this.denominator = (numerator == 0) ? 1 : Math.abs(denominator);
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int sign() {
        if (getNumerator() == 0) {
            return 0;
        }
        return getNumerator() / Math.abs(getNumerator());
    }

    int getNumerator() {return numerator;}
    int getDenominator() {return denominator;}

    public String toString() {
        return "<" + numerator + " / " + denominator + ">";
    }

    public static int[] getFactors(int num) {
        int primeIndex = 0;
        int[] prime_factors = new int[25];

        while (primeIndex < 25 && PRIMES[primeIndex] <= num) {
            if (num % PRIMES[primeIndex] == 0) {
                num = num / PRIMES[primeIndex];
                prime_factors[primeIndex] += 1;
            } else {
                primeIndex += 1;
            }
        }
        return prime_factors;
    }

    public static Fraction simplify(Fraction frac) {
        int num = Math.abs(frac.getNumerator());
        int den = Math.abs(frac.getDenominator());

        int sign = (frac.getNumerator() < 0 ? -1 : 1) * (frac.getDenominator() < 0 ? -1 : 1);

        if (num == 0) {
            return new Fraction(0, 1);
        }

        int[] num_factors = getFactors(num);
        int[] den_factors = getFactors(den);

        for (int i = 0; i < 20; i++) {
            int common_term = Math.min(num_factors[i], den_factors[i]);
            if (common_term > 0) {
                num = (int)(num / Math.pow(PRIMES[i], common_term));
                den = (int)(den / Math.pow(PRIMES[i], common_term));
            }
        }
        return new Fraction(num * sign, den);
    }


    public static Fraction add(Fraction frac1, Fraction frac2) {
        int numerator = frac1.getNumerator() * frac2.getDenominator() + frac1.getDenominator() * frac2.getNumerator();
        int denominator = frac1.getDenominator() * frac2.getDenominator();

        return simplify(new Fraction(numerator, denominator));
    }

    public static Fraction subtract(Fraction frac1, Fraction frac2) {
        int numerator = frac1.getNumerator() * frac2.getDenominator() - frac1.getDenominator() * frac2.getNumerator();
        int denominator = frac1.getDenominator() * frac2.getDenominator();

        return simplify(new Fraction(numerator, denominator));
    }

    public static Fraction multiply(Fraction frac1, Fraction frac2) {
        int numerator = frac1.getNumerator() * frac2.getNumerator();
        int denominator = frac1.getDenominator() * frac2.getDenominator();

        return simplify(new Fraction(numerator, denominator));
    }

    public static Fraction multiply(Fraction frac, int scalar) {
        int numerator = frac.getNumerator() * scalar;

        return simplify(new Fraction(numerator, frac.getDenominator()));
    }

    public static Fraction divide(Fraction frac1, Fraction frac2) {
        int numerator = frac1.getNumerator() * frac2.getDenominator();
        int denominator = frac1.getDenominator() * frac2.getNumerator();

        return simplify(new Fraction(numerator, denominator));
    }

    public static Fraction divide(Fraction frac, int scalar) {
        int denominator = frac.getDenominator() * scalar;

        return simplify(new Fraction(frac.getNumerator(), denominator));
    }

    public int compareTo(Fraction frac) {
        int compare = this.getNumerator() * frac.getDenominator() - this.getDenominator() * frac.getNumerator();

        return Integer.compare(compare, 0);
    }

    public double doubleValue() {
        return (double)this.getNumerator() / (double)this.getDenominator();
    }

    public double floatValue() {
        return (float)this.getNumerator() / (float)this.getDenominator();
    }

    public boolean isZero() {
        return getNumerator() == 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Fraction inputObj = (Fraction) obj;
        boolean equalsNumerator = this.getNumerator() == inputObj.getNumerator();
        boolean equalsDenominator = this.getDenominator() == inputObj.getDenominator();
        return (equalsNumerator && equalsDenominator);
    }

    public int intValue() {
        return getNumerator() / getDenominator();
    }

    public Fraction pow(int power) {
        int numerator = 1, denominator = 1;
        for (int i = 0; i < power; i++) {
            numerator *= this.getNumerator();
            denominator *= this.getDenominator();
        }
        return simplify(new Fraction(numerator, denominator));
    }

    public Fraction getAddInverse() {
        return simplify(new Fraction(this.getNumerator() * -1, this.getDenominator()));
    }

    public Fraction getMulInverse() {
        return simplify(new Fraction(this.getDenominator(), this.getNumerator()));
    }
}