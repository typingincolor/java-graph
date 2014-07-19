package com.losd.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrew on 19/07/2014.
 */
public class Graph {
    private HashMap<Node, List<Route>> nodes = new HashMap();

    public void addRoute(Node startNode, Node endNode, int distance) {
        List<Route> routes = nodes.get(startNode);

        if (routes != null) {
            for (Route route: routes) {
                if (route.getNode().equals(endNode)) {
                    throw new DuplicateRouteException();
                }
            }
            Route route = new Route(endNode, distance);
            routes.add(route);

            nodes.put(startNode, routes);
        }
        else {
            routes = new ArrayList<Route>();
            Route route = new Route(endNode, distance);
            routes.add(route);

            nodes.put(startNode, routes);
        }
    }

    public int numberOfRoutes(Node node) {
        return nodes.get(node).size();
    }
}
