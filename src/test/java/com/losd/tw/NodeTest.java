package com.losd.tw;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by andrew on 19/07/2014.
 */
public class NodeTest {
    @Test
    public void equal() {
        Node A = new Node("A");
        Node B = new Node("A");

        assertThat(A.equals(A), is(true));
        assertThat(A.equals(B), is(true));
        assertThat(B.equals(A), is(true));
    }

    @Test
    public void notEquals() {
        Node A = new Node("A");
        Node B = new Node("B");

        assertThat(A.equals(B), is(false));
        assertThat(B.equals(A), is(false));
    }
}
