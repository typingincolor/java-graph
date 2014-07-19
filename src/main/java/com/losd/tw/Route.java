package com.losd.tw;

import com.losd.tw.exceptions.RouteTooShortExeception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representation of a route
 *
 * Created by andrew on 19/07/2014.
 */
public class Route {
    private List towns = new ArrayList<Town>();

    public Route(Town ... towns) {
        if (towns.length < 2) {
            throw new RouteTooShortExeception();
        }

        this.towns = Arrays.asList(towns);
    }

    public int numberOfTowns() {
        return towns.size();
    }

    public Route pop() {
        List<Town> nextRoute = new ArrayList<Town>(towns);
        nextRoute.remove(0);

        return new Route(nextRoute.toArray(new Town[nextRoute.size()]));
    }

    public List<Town> getTowns() {
        return towns;
    }
}
