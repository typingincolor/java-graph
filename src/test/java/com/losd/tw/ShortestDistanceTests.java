package com.losd.tw;

import com.losd.tw.exceptions.NoSuchRouteException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test that I can find shortest distance between towns
 *
 * Created by andrew on 20/07/2014.
 */
public class ShortestDistanceTests {
    Town A = new Town("A");
    Town B = new Town("B");
    Town C = new Town("C");

    @Test
    public void simple() {
        Graph graph = GraphBuilder.build("AB5, BC6, AC1");

        Route result = graph.getShortestRouteBetween(A, C);
        Route expectedRoute = new Route(A, C);
        assertThat(result, is(expectedRoute));
    }

    @Test(expected = NoSuchRouteException.class)
    public void noRoute() {
        Graph graph = GraphBuilder.build("AB5, BC6, AC1");

        graph.getShortestRouteBetween(B, A);
    }
}
