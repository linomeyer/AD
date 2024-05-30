package ch.hslu.ad.sw14.adjamatrix;

import java.util.Objects;

public record Route(Station from, Station to, int weight) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(to, route.to) && Objects.equals(from, route.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
