package com.losd.tw;

import com.losd.tw.exceptions.RouteTooShortExeception;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Route tests
 *
 * Created by andrew on 19/07/2014.
 */
public class RouteTest {
    @Test
    public void simpleRoute() {
        Town A = new Town("A");
        Town B = new Town("B");

        Route route = new Route(A, B);

        assertThat(route.numberOfTowns(), is(2));
        assertThat(route.getStart(), is(A));
        assertThat(route.getFirstDestination(), is(B));
    }

    @Test(expected = RouteTooShortExeception.class)
    public void singleTownRoute() {
        Town A = new Town("A");
        new Route(A);
    }

    @Test(expected = RouteTooShortExeception.class)
    public void noTownRoute() {
        new Route();
    }

    @Test
    public void pop() {
        Town A = new Town("A");
        Town B = new Town("B");
        Town C = new Town("C");

        Route route = new Route(A, B, C);
        Route nextRoute = route.pop();
        assertThat(nextRoute.numberOfTowns(), is(2));
        assertThat(nextRoute.getTowns().get(0), is(B));
        assertThat(nextRoute.getTowns().get(1), is(C));
        assertThat(nextRoute.getStart(), is(B));
        assertThat(nextRoute.getFirstDestination(), is(C));
    }

    @Test
    public void endOfRoute() {
        Town A = new Town("A");
        Town B = new Town("B");

        Route route = new Route(A, B);
        assertThat(route.pop(), is(nullValue()));
    }
}
