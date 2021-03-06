package com.losd.tw;

import com.losd.tw.exceptions.RouteTooShortException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representation of a route
 * <p/>
 * Created by andrew on 19/07/2014.
 */
public class Route {
    private List<Town> towns = new ArrayList<Town>();

    /**
     * Create a route
     *
     * @param towns variable list of towns
     *
     * @throws com.losd.tw.exceptions.RouteTooShortException thrown if an attempt is made to route from A to A directly.
     */
    public Route(Town... towns) {
        if (towns.length < 2) {
            throw new RouteTooShortException();
        }

        this.towns = Arrays.asList(towns);
    }

    public int numberOfTowns() {
        return towns.size();
    }

    public Route pop() {
        List<Town> nextRoute = new ArrayList<Town>(towns);
        nextRoute.remove(0);

        return nextRoute.size() < 2 ? null : new Route(nextRoute.toArray(new Town[nextRoute.size()]));
    }

    public List<Town> getTowns() {
        return towns;
    }

    public Town getStart() {
        return towns.get(0);
    }

    public Town getFirstDestination() {
        return towns.get(1);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Town town : towns) {
            result.append(town);
        }
        return result.toString();
    }

    public boolean equals(Object that) {
        if (!(that instanceof Route)) {
            return false;
        }

        if (towns.size() != ((Route) that).towns.size()) {
            return false;
        }

        for (int i = 0; i < towns.size(); i++) {
            if (!towns.get(i).equals(((Route) that).towns.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
