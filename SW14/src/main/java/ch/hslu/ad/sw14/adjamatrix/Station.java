package ch.hslu.ad.sw14.adjamatrix;

import java.util.Objects;

public record Station(String station) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station1 = (Station) o;
        return Objects.equals(station, station1.station);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(station);
    }
}
