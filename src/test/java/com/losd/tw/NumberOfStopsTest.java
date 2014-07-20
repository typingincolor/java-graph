package com.losd.tw;

import com.losd.tw.exceptions.NoSuchRouteException;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Number of stops tests
 *
 * Created by andrew on 20/07/2014.
 */
public class NumberOfStopsTest extends UnitTest {
    @Test
    public void simple() {
        Graph graph = GraphBuilder.build("AB5, BC6, AC1, CD1, BD4, AD7");

        Set<Route> routes = graph.searchByNumberOfStops(A, D, 3);

        Route expectedRoute1 = new Route(A, B, C, D);

        assertThat(routes.size(), is(1));
        assertThat(routes, hasItem(expectedRoute1));
    }

    @Test(expected = NoSuchRouteException.class)
    public void noRoute() {
        Graph graph = GraphBuilder.build("AB5, BC6, AC1, CD1, BD4, AD7");
        graph.searchByNumberOfStops(A, E, 9);
    }
}
