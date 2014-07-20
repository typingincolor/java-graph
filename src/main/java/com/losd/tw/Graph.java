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
    private HashMap<Town, List<Trip>> nodes = new HashMap<Town, List<Trip>>();
    private int shortestRoute = Integer.MAX_VALUE;

    public void addTrip(Line line) {
        List<Trip> trips = getTripsForStartTown(line.getStartTown());

        Trip potentialTrip = new Trip(line.getEndTown(), line.getDistance());

        if (trips.contains(potentialTrip)) {
            throw new DuplicateRouteException();
        }

        trips.add(potentialTrip);
        nodes.put(line.getStartTown(), trips);
    }

    public int numberOfTripsStartingAt(Town town) {
        List<Trip> trips = nodes.get(town);
        return trips == null ? 0 : trips.size();
    }

    public int distance(Route route) {
        return calculateTotalDistance(route, 0);
    }

    private List<Trip> getTripsForStartTown(Town town) {
        return (nodes.get(town) == null) ? new LinkedList<Trip>() : nodes.get(town);
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

    public Set<Route> searchByNumberOfStops(Town start, Town end, int numberOfStops) {
        VisitedTowns visited = new VisitedTowns(start);
        return searchRestrictedByNumberOfStops(visited, end, numberOfStops);
    }

    public Set<Route> searchByMaximumDistance(Town start, Town end, int maxDistance) {
        VisitedTowns visited = new VisitedTowns(start);
        return searchRestrictedByMaximumDistance(visited, end, maxDistance);
    }

    public Route getShortestRouteBetween(Town start, Town end) {
        VisitedTowns visited = new VisitedTowns(start);
        shortestRoute = 30;
        Set<Route> routes = searchForShortestRoute(visited, end);

        Route result = null;
        int shortest = Integer.MAX_VALUE;

        for (Route route : routes) {
            int distance = distance(route);
            if (result == null || distance < shortest) {
                shortest = distance;
                result = route;
            }
        }

        if (result == null) {
            throw new NoSuchRouteException();
        }

        return result;
    }

    private int distance(Town a, Town b) {
        List<Trip> trips = getTripsForStartTown(a);

        Trip trip = findTripEndingAt(trips, b);
        if (trip == null) {
            throw new NoSuchRouteException();
        }

        return trip.distance;
    }

    private Trip findTripEndingAt(List<Trip> trips, Town town) {
        for (Trip trip : trips) {
            if (trip.destination.equals(town)) {
                return trip;
            }
        }

        return null;
    }

    private Set<Route> searchForShortestRoute(VisitedTowns visited, Town end) {
        HashSet<Route> result = new HashSet<Route>();
        List<Trip> trips = getTripsForStartTown(visited.getLastVisited());

        for (Trip trip : trips) {
            if (visited.distanceTravelled > shortestRoute) {
                continue;
            }

            if (trip.destination.equals(end)) {
                visited.add(trip);
                if (visited.distanceTravelled <= shortestRoute) {
                    result.add(visited.toRoute());
                    shortestRoute = visited.distanceTravelled;
                }
                visited.removeLast();
                break;
            }
        }

        for (Trip trip: trips) {
            if (visited.distanceTravelled > shortestRoute) {
                continue;
            }

            visited.add(trip);
            result.addAll(searchForShortestRoute(visited, end));
            visited.removeLast();
        }

        return result;
    }

    private HashSet<Route> searchRestrictedByNumberOfStops(VisitedTowns visited, Town end, int numberOfStops) {
        HashSet<Route> result = new HashSet<Route>();
        List<Trip> trips = getTripsForStartTown(visited.getLastVisited());

        for (Trip trip : trips) {
            if (visited.size() > numberOfStops) {
                continue;
            }

            if (trip.destination.equals(end)) {
                visited.add(trip);
                if (visited.size() == numberOfStops + 1) {
                    result.add(visited.toRoute());
                }
                visited.removeLast();
                break;
            }
        }

        for (Trip trip: trips) {
            if (visited.size() > numberOfStops) {
                continue;
            }

            visited.add(trip);
            result.addAll(searchRestrictedByNumberOfStops(visited, end, numberOfStops));
            visited.removeLast();
        }

        return result;
    }

    private HashSet<Route> searchRestrictedByMaximumDistance(VisitedTowns visited, Town end, int maximumDistance) {
        HashSet<Route> result = new HashSet<Route>();
        List<Trip> trips = getTripsForStartTown(visited.getLastVisited());

        for (Trip trip : trips) {
            if (visited.distanceTravelled > maximumDistance) {
                continue;
            }

            if (trip.destination.equals(end)) {
                visited.add(trip);
                if (visited.distanceTravelled <= maximumDistance) {
                    result.add(visited.toRoute());
                }
                visited.removeLast();
                break;
            }

        }

        for (Trip trip: trips) {
            if (visited.distanceTravelled > maximumDistance) {
                continue;
            }

            visited.add(trip);
            result.addAll(searchRestrictedByMaximumDistance(visited, end, maximumDistance));
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
