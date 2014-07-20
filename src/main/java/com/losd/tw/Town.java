package com.losd.tw;

import java.util.HashMap;

/**
 * Representation of a Town
 *
 * Created by andrew on 19/07/2014.
 */
public class Town {
    private static HashMap<String, Town> towns = new HashMap<String, Town>();
    private String name;

    private Town(String name) {
        this.name = name;
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
        return name;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
