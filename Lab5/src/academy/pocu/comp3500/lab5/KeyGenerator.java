package academy.pocu.comp3500.lab5;

import java.math.BigInteger;

public class KeyGenerator {
    public static boolean isPrime(final BigInteger number) {
        return isPrime(number, 100);
    }

    static BigInteger power(BigInteger x, BigInteger y, BigInteger p) {
        BigInteger res = BigInteger.ONE;
        x = x.mod(p);

        while (y.compareTo(BigInteger.ZERO) == 1) {
            if ((y.and(BigInteger.ONE).compareTo(BigInteger.ONE)) == 0)
                res = res.multiply(x).mod(p);

            y = y.shiftRight(1);
            x = x.multiply(x).mod(p);
        }

        return res;
    }

    static boolean miillerTest(BigInteger d, BigInteger n) {
        BigInteger randNum = BigInteger.valueOf((int) Math.random());
        BigInteger a = BigInteger.valueOf(2).add(randNum.mod(n.subtract(BigInteger.valueOf(4))));
        BigInteger x = power(a, d, n);

        if (x.compareTo(BigInteger.ONE) == 0 || x.compareTo(n.subtract(BigInteger.ONE)) == 0)
            return true;

        while (d.compareTo(n.subtract(BigInteger.ONE)) != 0) {
            x = x.multiply(x).mod(n);
            d = d.multiply(BigInteger.TWO);

            if (x.compareTo(BigInteger.ONE) == 0)
                return false;
            if (x.compareTo(n.subtract(BigInteger.ONE)) == 0)
                return true;
        }

        // Return composite
        return false;
    }

    static boolean isPrime(BigInteger n, int k) {
        BigInteger three = BigInteger.valueOf(3);
        BigInteger four = BigInteger.valueOf(4);
        if (n.compareTo(BigInteger.ONE) <= 0 || n.compareTo(four) == 0)
            return false;
        if (n.compareTo(three) <= 0)
            return true;

        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0)
            d = d.divide(BigInteger.TWO);

        for (int i = 0; i < k; i++)
            if (!miillerTest(d, n))
                return false;

        return true;
    }
}