package com.losd.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrew on 19/07/2014.
 */
public class Graph {
    private HashMap<String, List<Route>> nodes = new HashMap();

    public void addRoute(String startNode, String endNode, int distance) {
         List<Route> routes = new ArrayList<Route>();

        Route route = new Route(new Node(endNode), distance);
        routes.add(route);

        nodes.put(startNode, routes);
    }

    public int numberOfRoutes(String node) {
        return nodes.get(node).size();
    }
}
