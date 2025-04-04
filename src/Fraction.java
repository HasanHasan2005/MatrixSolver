package src;

public class Fraction {
    int numerator;
    int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {throw new IllegalArgumentException("Zero denominator");}

        this.numerator = (denominator < 0) ? -numerator : numerator;
        this.denominator = (numerator == 0) ? 1 : Math.abs(denominator);
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        denominator = 1;
    }

    public int sign() {
        return (getNumerator() == 0) ? 0 : (getNumerator() / Math.abs(getNumerator()));
    }

    int getNumerator() {return numerator;}

    int getDenominator() {return denominator;}

    public Fraction getValue() {return new Fraction(numerator, denominator);}


    public String toString() {
        return "<" + numerator + " / " + denominator + ">";
    }

    public static int[] getFactors(int num, int[] primes) {
        int primeIndex = 0;
        int[] prime_factors = new int[primes.length];

        while (primeIndex < primes.length && primes[primeIndex] <= num) {
            if (num % primes[primeIndex] == 0) {
                num = num / primes[primeIndex];
                prime_factors[primeIndex] += 1;
            } else {
                primeIndex += 1;
            }
        }
        return prime_factors;
    }

    public void simplify() {
        int num = Math.abs(numerator);
        int den = Math.abs(denominator);

        if (num == 0) {numerator = 0; denominator = 1; return;}

        int sign = (numerator < 0 ? -1 : 1) * (denominator < 0 ? -1 : 1);

        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
                37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int[] num_factors = getFactors(num, primes);
        int[] den_factors = getFactors(den, primes);

        for (int i = 0; i < 20; i++) {
            int common_term = Math.min(num_factors[i], den_factors[i]);
            if (common_term > 0) {
                num = (int)(num / Math.pow(primes[i], common_term));
                den = (int)(den / Math.pow(primes[i], common_term));
            }
        }
        numerator = num * sign;
        denominator = den;
    }

    public Fraction getSimplified() {
        Fraction temp = new Fraction(numerator, denominator);
        temp.simplify();
        return temp;
    }


    public Fraction getAddInverse() {return new Fraction(-numerator, denominator).getSimplified();}

    public void invertAdditive() {numerator = -numerator;}

    public Fraction getMulInverse() {return new Fraction(denominator, numerator).getSimplified();}

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

    public void subtract(Fraction frac) {
        add(frac.getAddInverse());
    }

    public void multiply(Fraction frac) {
        this.numerator *= frac.getNumerator();
        this.denominator *= frac.getDenominator();
        simplify();
    }

    public void divide(Fraction frac) {
        multiply(frac.getMulInverse());
    }

    public static Fraction add(Fraction scalar1, Fraction scalar2) {
        Fraction temp = scalar1.getValue();
        temp.add(scalar2);
        return temp;
    }

    public static Fraction subtract(Fraction scalar1, Fraction scalar2) {
        Fraction temp = scalar1.getValue();
        temp.subtract(scalar2);
        return temp;
    }

    public static Fraction multiply(Fraction scalar1, Fraction scalar2) {
        Fraction temp = scalar1.getValue();
        temp.multiply(scalar2);
        return temp;
    }

    public static Fraction divide(Fraction scalar1, Fraction scalar2) {
        Fraction temp = scalar1.getValue();
        temp.divide(scalar2);
        return temp;
    }



    public int compareTo(Fraction frac) {
        int comparator = this.getNumerator() * frac.getDenominator() - this.getDenominator() * frac.getNumerator();

        return Integer.compare(comparator, 0);
    }

    public double doubleValue() {
        return (double)this.getNumerator() / (double)this.getDenominator();
    }

    public float floatValue() {
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
        boolean equalsNumerator = numerator == inputObj.getNumerator();
        boolean equalsDenominator = denominator == inputObj.getDenominator();
        return (equalsNumerator && equalsDenominator);
    }

    public int intValue() {
        return numerator / denominator;
    }

    public Fraction pow(int power) {
        int numerator = 1, denominator = 1;
        for (int i = 0; i < power; i++) {
            numerator *= this.getNumerator();
            denominator *= this.getDenominator();
        }
        return new Fraction(numerator, denominator).getSimplified();
    }
}