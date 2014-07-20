package com.losd.tw;

import com.losd.tw.exceptions.NoSuchRouteException;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Maximum distance tests
 *
 * Created by andrew on 20/07/2014.
 */
public class MaximumDistanceTest extends UnitTest {
    @Test
    public void simple() {
        Graph graph = GraphBuilder.build("AB5, BC6, AC1, CD1, BD4, AD7");

        Set<Route> routes = graph.searchForLessThanDistance(A, D, 8);

        Route expectedRoute1 = new Route(A, C, D);
        Route expectedRoute2 = new Route(A, D);

        assertThat(routes.size(), is(2));
        assertThat(routes, hasItem(expectedRoute1));
        assertThat(routes, hasItem(expectedRoute2));
    }

    @Test(expected = NoSuchRouteException.class)
    public void noRoute() {
        Graph graph = GraphBuilder.build("AB5, BC6, AC1, CD1, BD4, AD7");
        graph.searchForLessThanDistance(A, E, 9);
    }
}
