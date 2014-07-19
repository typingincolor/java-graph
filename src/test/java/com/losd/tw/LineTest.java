package com.losd.tw;

import org.junit.Test;

/**
 * Created by andrew on 19/07/2014.
 */
public class LineTest {
    @Test(expected = InvalidRouteException.class)
    public void cannotCreateLineFromAtoA() {
        Town A = new Town("A");
        new Line(A, A, 5);
    }
}
