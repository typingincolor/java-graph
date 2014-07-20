package com.losd.tw;

import java.util.HashMap;

/**
 * Representation of a Town
 *
 * Created by andrew on 19/07/2014.
 */
public class Town {
    private static HashMap<String, Town> towns = new HashMap<String, Town>();
    private String id;

    private Town(String id) {
        this.id = id;
    }

    public static Town getInstance(String id) {
        Town town = towns.get(id);

        if (town == null) {
            Town newTown = new Town(id);
            towns.put(id, newTown);
            return newTown;
        }

        return town;
    }

    public String toString() {
        return id;
    }
}
