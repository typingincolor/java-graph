package com.losd.tw;

import com.losd.tw.exceptions.InvalidLineException;
import org.junit.Test;

/**
 * Line Tests
 *
 * Created by andrew on 19/07/2014.
 */
public class LineTest {
    @Test(expected = InvalidLineException.class)
    public void cannotCreateLineFromAtoA() {
        Town A = Town.getInstance("A");
        new Line(A, A, 5);
    }
}
