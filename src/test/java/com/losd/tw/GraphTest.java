package com.losd.tw;

import com.losd.tw.exceptions.DuplicateRouteException;
import com.losd.tw.exceptions.InvalidLineException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Graph Tests
 *
 * Created by andrew on 19/07/2014.
 */
public class GraphTest {
    Town A = new Town("A");
    Town B = new Town("B");
    Town C = new Town("C");

    @Test
    public void addRoute() {
        Graph graph = GraphBuilder.build("AB5");
        assertThat(graph.numberOfTripsStartingAt(A), is(1));
    }

    @Test
    public void addTwoRoutes() {
        Graph graph = GraphBuilder.build("AB5, AC1");
        assertThat(graph.numberOfTripsStartingAt(A), is(2));
    }

    @Test
    public void triangle() {
        Graph graph = GraphBuilder.build("AB5, AC1, BC1");

        assertThat(graph.numberOfTripsStartingAt(A), is(2));
        assertThat(graph.numberOfTripsStartingAt(B), is(1));
        assertThat(graph.numberOfTripsStartingAt(C), is(0));
    }

    @Test(expected = DuplicateRouteException.class)
    public void failsForDuplicateRoute() {
        GraphBuilder.build("AB5, AB5");
    }

    @Test(expected = InvalidLineException.class)
    public void failsForRouteFromAToA() {
        GraphBuilder.build("AA5");
    }
}
