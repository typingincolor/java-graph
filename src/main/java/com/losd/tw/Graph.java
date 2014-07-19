package com.losd.tw;

import com.losd.tw.exceptions.DuplicateRouteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representation of railroad map
 *
 * Created by andrew on 19/07/2014.
 */
public class Graph {
    private Map<Town, List<Trip>> nodes = new HashMap<Town, List<Trip>>();

    public void addRoute(Line line) {
        List<Trip> trips = getTripsForStartTown(line.getStartTown());

        Trip potentialTrip = new Trip(line.getEndTown(), line.getDistance());

        if (trips.contains(potentialTrip)) {
            throw new DuplicateRouteException();
        }

        trips.add(potentialTrip);
        nodes.put(line.getStartTown(), trips);
    }

    private List<Trip> getTripsForStartTown(Town town) {
        return (nodes.get(town) == null) ? new ArrayList<Trip>() : nodes.get(town);
    }

    public int numberOfLinesStartingAt(Town town) {
        return nodes.get(town).size();
    }

    private class Trip {
        private Town town;
        private int distance;

        public Trip(Town town, int distance) {
            this.town = town;
            this.distance = distance;
        }

        public boolean equals(Object that) {
            if (! (that instanceof Trip)) return false;

            Trip x = (Trip) that;

            return this.town.equals(x.town);
        }
    }
}
