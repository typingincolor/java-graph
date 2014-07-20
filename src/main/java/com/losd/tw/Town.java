package com.losd.tw;

/**
 * Representation of a Town
 * <p/>
 * Created by andrew on 19/07/2014.
 */
public class Town {
    private String name;

    public Town(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object that) {
        return that instanceof Town && this.toString().equals(that.toString());

    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
