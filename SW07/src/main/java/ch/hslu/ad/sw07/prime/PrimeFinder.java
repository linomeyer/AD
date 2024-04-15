package ch.hslu.ad.sw07.prime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class PrimeFinder implements Runnable {
    private final List<BigInteger> primeNumbers;
    private static final Logger LOG = LoggerFactory.getLogger(PrimeFinder.class);

    public PrimeFinder(final List<BigInteger> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    @Override
    public void run() {
        while (primeNumbers.size() < 100) {
            BigInteger bi = new BigInteger(1024, new Random());
            if (bi.isProbablePrime(Integer.MAX_VALUE)) {
                synchronized (primeNumbers) {
                    int size = primeNumbers.size();
                    if (size < 100) {
                        primeNumbers.add(bi);
                        LOG.info("{} : {}...", size + 1, bi.toString().substring(0, 20));
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
