package com.losd.tw;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Town Tests
 *
 * Created by andrew on 19/07/2014.
 */
public class TownTest {
    @Test
    public void equal() {
        Town A = new Town("A");
        Town B = new Town("A");

        assertThat(A.equals(A), is(true));
        assertThat(A.equals(B), is(true));
        assertThat(B.equals(A), is(true));
    }

    @Test
    public void notEquals() {
        Town A = new Town("A");
        Town B = new Town("B");

        assertThat(A.equals(B), is(false));
        assertThat(B.equals(A), is(false));
    }
}
