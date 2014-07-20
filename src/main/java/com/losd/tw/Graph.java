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
        VisitedTowns visited = new VisitedTowns(start);
        go(visited, end, maxSteps);
    }

    private void go(VisitedTowns visited, Town end, int maxSteps) {
        List<Trip> trips = getTripsForStartTown(visited.getLastVisited());

        for (Trip trip : trips) {
            if (visited.size() > maxSteps) {
                continue;
            }

            if (trip.destination.equals(end)) {
                visited.add(trip);
                if (visited.size() == maxSteps + 1) System.out.println(visited);
                visited.removeLast();
                break;
            }

        }

        for (Trip trip: trips) {
            if (visited.size() > maxSteps) {
                continue;
            }

            visited.add(trip);
            go(visited, end, maxSteps);
            visited.removeLast();
        }
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

    private class VisitedTowns {
        private LinkedList<Trip> visited = new LinkedList<Trip>();
        private int distanceTravelled = 0;

        public VisitedTowns(Town start) {
            visited.addLast(new Trip(start, 0));
        }

        public void add(Trip trip) {
            visited.addLast(trip);
            distanceTravelled += trip.distance;
        }

        public void removeLast() {
            Trip last = visited.getLast();
            distanceTravelled -= last.distance;
            visited.removeLast();
        }

        public Town getLastVisited() {
            return visited.getLast().destination;
        }

        public int size() {
            return visited.size();
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            for (Trip town : visited) {
                result.append(town.destination);
            }

            result.append(" ").append(distanceTravelled);
            return result.toString();
        }
    }
}
