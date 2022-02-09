package pl.pabjan.schoolmanagementsystem.model;

public enum MarkValue {
    ONE(1), PLUS_ONE(1.5), MINUS_TWO(1.75), TWO(2), PLUS_TWO(2.5), MINUS_THREE(2.75), THREE(3), PLUS_THREE(3.5), MINUS_FOUR(3.75), FOUR(4), PLUS_FOUR(4.5), MINUS_FIVE(4.75), FIVE(5), PLUS_FIVE(5.5), SIX(6);

    private final double value;

    MarkValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
