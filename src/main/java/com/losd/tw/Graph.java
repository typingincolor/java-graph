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
    private Map<Town, List<Route>> nodes = new HashMap<Town, List<Route>>();

    public void addRoute(Line line) {
        if (isExistingLine(line)) {
            throw new DuplicateRouteException();
        }
        else {
            addRouteToTown(line);
        }
    }

    private boolean isExistingLine(Line line) {
        List<Route> routes = getRoutesForStartTown(line);

        if (routes.size() == 0) {
            return false;
        }

        for (Route route : routes) {
            if (route.town.equals(line.getEndTown())) {
                return true;
            }
        }

        return false;
    }

    private void addRouteToTown(Line line) {
        List<Route> routes = getRoutesForStartTown(line);

        Route route = new Route(line.getEndTown(), line.getDistance());
        routes.add(route);

        nodes.put(line.getStartTown(), routes);
    }

    private List<Route> getRoutesForStartTown(Line line) {
        return (nodes.get(line.getStartTown()) == null) ? new ArrayList<Route>() : nodes.get(line.getStartTown());
    }

    public int numberOfLinesStartingAt(Town town) {
        return nodes.get(town).size();
    }

    private class Route {
        private Town town;
        private int distance;

        public Route(Town town, int distance) {
            this.town = town;
            this.distance = distance;
        }
    }
}
