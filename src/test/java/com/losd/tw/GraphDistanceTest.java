package com.losd.tw;

import com.losd.tw.exceptions.NoSuchRouteException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Distance tests
 *
 * Created by andrew on 19/07/2014.
 */
public class GraphDistanceTest extends UnitTest{
    @Test
    public void simpleRoute() {
        Graph graph = GraphBuilder.build("AB5");
        Route route = new Route(A, B);

        assertThat(graph.distance(route), is(5));
    }

    @Test
    public void moreComplicatedRoute() {
        Graph graph = GraphBuilder.build("AB5, BC6");
        Route route = new Route(A, B, C);

        assertThat(graph.distance(route), is(11));
    }

    @Test(expected = NoSuchRouteException.class)
    public void noRoute() {
        Graph graph = GraphBuilder.build("AB5, BC6");
        Route route = new Route(A, C);

        assertThat(graph.distance(route), is(11));
    }
}
