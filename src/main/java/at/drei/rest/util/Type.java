package at.drei.rest.util;

public enum Type {
    // enum fields
    premium(1), standard(2);

    // internal state
    private int tp;

    // constructor
    private Type(final int t) {
        this.tp = t;
    }

    public int getTp() {
        return tp;
    }
}
