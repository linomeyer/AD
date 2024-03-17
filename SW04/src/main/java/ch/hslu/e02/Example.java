package ch.hslu.e02;

public record Example(int number) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return number == example.number;
    }

    @Override
    public String toString() {
        return "Example{" +
                "number=" + number +
                '}';
    }
}
