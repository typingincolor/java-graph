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
        List<Route> routes = getRoutesForStartTown(line);

        Route potentialRoute = new Route(line.getEndTown(), line.getDistance());

        if (routes.contains(potentialRoute)) {
            throw new DuplicateRouteException();
        }

        routes.add(potentialRoute);
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

        public boolean equals(Object that) {
            if (! (that instanceof Route)) return false;

            Route x = (Route) that;

            return this.town.equals(x.town);
        }
    }
}
