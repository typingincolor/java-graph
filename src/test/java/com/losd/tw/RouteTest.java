package com.losd.tw;

import com.losd.tw.exceptions.RouteTooShortExeception;
import org.junit.Test;

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
}
