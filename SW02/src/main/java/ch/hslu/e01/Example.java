package ch.hslu.e01;

import java.util.Objects;

public class Example {
    private final int number;

    public Example(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return number == example.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Example{" +
                "number=" + number +
                '}';
    }
}
