package src;

public class Fraction {
    public static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
            37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    int num;
    int den;

    public Fraction(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Zero denominator");
        }
        this.den = (num == 0) ? 1 : Math.abs(den);

        this.num = (den < 0) ? -num : num;
    }

    public Fraction(int num) {
        this.den = 1;
        this.num = num;
    }

    int getNum() {return num;}
    int getDen() {return den;}

    public String toString() {
        return "<" + num + " / " + den + ">";
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

    public static Fraction simplifyFraction(Fraction frac) {
        int num = Math.abs(frac.getNum());
        int den = frac.getDen();


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
        return new Fraction((frac.getNum() < 0) ? -num : num, den);
    }


    public static Fraction fracAdd(Fraction frac1, Fraction frac2) {
        int numerator = frac1.getNum()*frac2.getDen() + frac1.getDen()*frac2.getNum();
        int denominator = frac1.getDen()*frac2.getDen();
        return simplifyFraction(new Fraction(numerator, denominator));
    }

    public static Fraction fracMul(Fraction frac1, Fraction frac2) {
        return simplifyFraction(new Fraction(frac1.getNum()*frac2.getNum(), frac1.getDen()*frac2.getDen()));
    }

}
