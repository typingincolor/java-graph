package com.losd.tw;

import com.losd.tw.exceptions.DuplicateRouteException;
import com.losd.tw.exceptions.NoSuchRouteException;

import java.util.*;

/**
 * Representation of railroad map
 *
 * Created by andrew on 19/07/2014.
 */
public class Graph {
    private Map<Town, List<Trip>> nodes = new HashMap<Town, List<Trip>>();

    public void addTrip(Line line) {
        List<Trip> trips = getTripsForStartTown(line.getStartTown());

        Trip potentialTrip = new Trip(line.getEndTown(), line.getDistance());

        if (trips.contains(potentialTrip)) {
            throw new DuplicateRouteException();
        }

        trips.add(potentialTrip);
        nodes.put(line.getStartTown(), trips);
    }

    public int numberOfLinesStartingAt(Town town) {
        return nodes.get(town).size();
    }

    public int distance(Route route) {
        return calculateTotalDistance(route, 0);
    }

    private List<Trip> getTripsForStartTown(Town town) {
        return (nodes.get(town) == null) ? new ArrayList<Trip>() : nodes.get(town);
    }

    private int calculateTotalDistance(Route route, int totalDistance) {
        if (route == null) {
            return totalDistance;
        }

        Town start = route.getStart();
        Town destination = route.getFirstDestination();

        totalDistance+=distance(start, destination);

        return calculateTotalDistance(route.pop(), totalDistance);
    }

    private int distance(Town a, Town b) {
        List<Trip> trips = nodes.get(a);
        int tripIndex = trips.indexOf(new Trip(b, 0));

        if (tripIndex == -1) {
            throw new NoSuchRouteException();
        }

        return trips.get(tripIndex).distance;
    }

    public void ride(Town start, Town end, int maxSteps) {
        LinkedList<Town> visited = new LinkedList<Town>();
        visited.add(start);
        go(visited, end, maxSteps);
    }

    public void go(LinkedList<Town> visited, Town end, int maxSteps) {
        List<Trip> trips = getTripsForStartTown(visited.getLast());

        for (Trip trip : trips) {
            if (visited.size() > maxSteps) {
                continue;
            }

            if (trip.destination.equals(end)) {
                visited.add(trip.destination);
                if (visited.size() == maxSteps + 1) printPath(visited);
                visited.removeLast();
                break;
            }

        }

        for (Trip trip: trips) {
            if (visited.size() > maxSteps) {
                continue;
            }

            visited.addLast(trip.destination);
            go(visited, end, maxSteps);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<Town> visited) {
        for (Town town : visited) {
            System.out.print(town);
            System.out.print(" ");
        }
        System.out.println();
    }

    private class Trip {
        private Town destination;
        private int distance;

        public Trip(Town destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        public boolean equals(Object that) {
            if (! (that instanceof Trip)) return false;

            Trip x = (Trip) that;

            return this.destination.equals(x.destination);
        }
    }
}
