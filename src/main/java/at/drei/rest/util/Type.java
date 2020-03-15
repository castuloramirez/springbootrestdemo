package at.drei.rest.util;

public enum Type {
    // enum fields
    premium(1), standard(2);

    // internal state
    private int priority;

    // constructor
    Type(final int t) {
        this.priority = t;
    }

    public int getPriority() {
        return priority;
    }
}
