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
        List<Route> routes = nodes.get(line.getStartTown());

        if (routes != null) {
            for (Route route : routes) {
                if (route.getNode().equals(line.getEndTown())) {
                    throw new DuplicateRouteException();
                }
            }
            Route route = new Route(line.getEndTown(), line.getDistance());
            routes.add(route);

            nodes.put(line.getStartTown(), routes);
        } else {
            routes = new ArrayList<Route>();
            Route route = new Route(line.getEndTown(), line.getDistance());
            routes.add(route);

            nodes.put(line.getStartTown(), routes);
        }
    }

    public int numberOfRoutes(Town town) {
        return nodes.get(town).size();
    }
}
