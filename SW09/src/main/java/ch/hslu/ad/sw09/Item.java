package ch.hslu.ad.sw09;

import java.util.Objects;

public record Item(int number) implements Comparable<Item> {

    @Override
    public int compareTo(Item item) {
        return Integer.compare(this.number, item.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return number == item.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public String toString() {
        return number + "";
    }
}
