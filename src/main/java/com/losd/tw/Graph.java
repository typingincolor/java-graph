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
    private HashMap<Town, Set<Trip>> nodes = new HashMap<Town, Set<Trip>>();

    public void addTrip(Line line) {
        Set<Trip> trips = getTripsForStartTown(line.getStartTown());

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

    private Set<Trip> getTripsForStartTown(Town town) {
        return (nodes.get(town) == null) ? new HashSet<Trip>() : nodes.get(town);
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

    public HashSet<Route> searchByMaximumSteps(Town start, Town end, int maxSteps) {
        VisitedTowns visited = new VisitedTowns(start);
        return go(visited, end, maxSteps);
    }

    public Set<Route> searchByMaximumDistance(Town start, Town end, int maxDistance) {
        VisitedTowns visited = new VisitedTowns(start);
        return god(visited, end, maxDistance);
    }

    private int distance(Town a, Town b) {
        List<Trip> trips = new ArrayList<Trip>(nodes.get(a));
        int tripIndex = trips.indexOf(new Trip(b, 0));

        if (tripIndex == -1) {
            throw new NoSuchRouteException();
        }

        return trips.get(tripIndex).distance;
    }

    private HashSet<Route> go(VisitedTowns visited, Town end, int maxSteps) {
        HashSet<Route> result = new HashSet<Route>();
        Set<Trip> trips = getTripsForStartTown(visited.getLastVisited());

        for (Trip trip : trips) {
            if (visited.size() > maxSteps) {
                continue;
            }

            if (trip.destination.equals(end)) {
                visited.add(trip);
                if (visited.size() == maxSteps + 1) {
                    result.add(visited.toRoute());
                }
                visited.removeLast();
                break;
            }

        }

        for (Trip trip: trips) {
            if (visited.size() > maxSteps) {
                continue;
            }

            visited.add(trip);
            result.addAll(go(visited, end, maxSteps));
            visited.removeLast();
        }

        return result;
    }

    private HashSet<Route> god(VisitedTowns visited, Town end, int maxDistance) {
        HashSet<Route> result = new HashSet<Route>();
        Set<Trip> trips = getTripsForStartTown(visited.getLastVisited());

        for (Trip trip : trips) {
            if (visited.distanceTravelled > maxDistance) {
                continue;
            }

            if (trip.destination.equals(end)) {
                visited.add(trip);
                if (visited.distanceTravelled <= maxDistance) {
                    result.add(visited.toRoute());
                }
                visited.removeLast();
                break;
            }

        }

        for (Trip trip: trips) {
            if (visited.distanceTravelled > maxDistance) {
                continue;
            }

            visited.add(trip);
            result.addAll(god(visited, end, maxDistance));
            visited.removeLast();
        }

        return result;
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

        @Override
        public int hashCode() {
            return destination.hashCode();
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

        public Route toRoute() {
            Town[] towns = new Town[visited.size()];
            for (int i=0; i < visited.size(); i++)
            {
                towns[i] = visited.get(i).destination;
            }
            return new Route(towns);
        }
    }
}
