package ar.edu.itba.ss;

public class Pair<U,T> {
    private final U firstValue;
    private final T secondValue;

    public Pair(U firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public U getFirstValue() {
        return firstValue;
    }

    public T getSecondValue() {
        return secondValue;
    }
}
