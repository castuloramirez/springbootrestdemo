package at.drei.rest.model;

import at.drei.rest.util.Type;

public class SearchLocation {
    private String p1;
    private String p2;
    private Type type;
    private String limit;

    public SearchLocation(String p1, String p2, Type type, String limit) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
        this.limit = limit;
    }

    public SearchLocation() {
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "SearchLocation{" +
                "p1='" + p1 + '\'' +
                ", p2='" + p2 + '\'' +
                ", type='" + type + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
