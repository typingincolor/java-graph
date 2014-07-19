package com.losd.tw;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Distance tests
 *
 * Created by andrew on 19/07/2014.
 */
public class GraphDistanceTest {
    @Test
    public void simpleRoute() {
        Town A = new Town("A");
        Town B = new Town("B");

        Route route = new Route(A, B);

        Graph graph = new Graph();
        Line line = new Line(A, B, 5);
        graph.addTrip(line);

        assertThat(graph.distance(route), is(5));
    }
}
