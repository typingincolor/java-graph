package com.losd.tw;

import java.util.StringTokenizer;

/**
 * Builds graphs
 *
 * Created by andrew on 20/07/2014.
 */
public abstract class GraphBuilder {
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
