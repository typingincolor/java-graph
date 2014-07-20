package com.losd.tw;

import java.util.StringTokenizer;

/**
 * Created by andrew on 20/07/2014.
 */
public abstract class GraphBuilder {
    public static Graph build(String graph) {

        StringTokenizer st = new StringTokenizer(graph, ",", false);

        Graph newGraph = new Graph();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().replace(" ", "");
            Town a = Town.getInstance(Character.toString(token.charAt(0)));
            Town b = Town.getInstance(Character.toString(token.charAt(1)));
            int distance = Integer.parseInt(token.substring(2));

            Line line = new Line(a, b, distance);
            newGraph.addTrip(line);
        }
        return newGraph;
    }
}
