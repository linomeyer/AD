package ch.hslu.e03;

public class Ackermann {
    public long ack(long n, long m) {
        if (m > 0 && n == 0) {
            return m + 1;
        } else if (n > 0 && m == 0) {
            return ack(n - 1, 1);
        } else {
            return ack(n - 1, ack(n, m - 1));
        }
    }
}
