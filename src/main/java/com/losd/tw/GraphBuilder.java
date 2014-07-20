package com.losd.tw;

import java.util.StringTokenizer;

/**
 * Builds graphs
 * <p/>
 * Created by andrew on 20/07/2014.
 */
public abstract class GraphBuilder {
    /**
     * Builds a graph
     * <p/>
     * Towns are single characters. A route from A to B is represented by ABx where x is an integer. Routes are separated by commas.
     *
     * @param graph string representation of a graph
     * @return the graph
     * @throws com.losd.tw.exceptions.DuplicateRouteException thrown if a line already exists
     * @throws com.losd.tw.exceptions.InvalidLineException    thrown if a line tries to go from A to A
     */
    public static Graph build(String graph) {

        StringTokenizer st = new StringTokenizer(graph.replace(" ", ""), ",", false);

        Graph newGraph = new Graph();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            Town a = new Town(Character.toString(token.charAt(0)));
            Town b = new Town(Character.toString(token.charAt(1)));
            int distance = Integer.parseInt(token.substring(2));

            Line line = new Line(a, b, distance);
            newGraph.addTrip(line);
        }
        return newGraph;
    }
}
