package ch.hslu.ad.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class RuntimeCalculator {
    private final List<Long> times;

    public RuntimeCalculator() {
        times = new ArrayList<Long>();
    }

    public void addTime(long elapsedTime) {
        times.add(elapsedTime);
    }

    /**
     * Disregards the first element of the list to get a better picture of the actual average runtime of an algorithm.
     *
     * @return the average runtime
     */
    public long calculateAverageRuntime() {
        OptionalDouble average = times.stream().mapToLong(Long::longValue).skip(1).average();
        return average.isPresent() ? Double.valueOf(average.getAsDouble()).longValue() : 0L;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("The runtime of each run was (ignoring the first): ");
        for (Long time : times) {
            stringBuilder.append("\t").append(time).append(" ms\n");
        }
        stringBuilder
                .append("\nThis makes an average runtime of:\n\t")
                .append(calculateAverageRuntime())
                .append(" ms\n\n");

        return stringBuilder.toString();
    }
}
