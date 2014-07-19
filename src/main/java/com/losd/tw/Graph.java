package com.losd.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrew on 19/07/2014.
 */
public class Graph {
    private HashMap<Town, List<Route>> nodes = new HashMap();

    public void addRoute(Town startTown, Town endTown, int distance) {
        if (startTown.equals(endTown)) {
            throw new InvalidRouteException();
        }

        List<Route> routes = nodes.get(startTown);

        if (routes != null) {
            for (Route route: routes) {
                if (route.getNode().equals(endTown)) {
                    throw new DuplicateRouteException();
                }
            }
            Route route = new Route(endTown, distance);
            routes.add(route);

            nodes.put(startTown, routes);
        }
        else {
            routes = new ArrayList<Route>();
            Route route = new Route(endTown, distance);
            routes.add(route);

            nodes.put(startTown, routes);
        }
    }

    public int numberOfRoutes(Town town) {
        return nodes.get(town).size();
    }
}
