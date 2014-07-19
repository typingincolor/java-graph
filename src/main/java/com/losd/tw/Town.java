package com.losd.tw;

/**
 * Representation of a Town
 *
 * Created by andrew on 19/07/2014.
 */
public class Town {
    private String id;

    public Town(String id) {
        this.id = id;
    }

    public boolean equals(Town o) {
        return this.id.equals(o.id);
    }

    public String toString() {
        return id;
    }
}
