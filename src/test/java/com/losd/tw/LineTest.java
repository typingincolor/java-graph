package com.losd.tw;

import com.losd.tw.exceptions.InvalidLineException;
import org.junit.Test;

/**
 * Line Tests
 *
 * Created by andrew on 19/07/2014.
 */
public class LineTest extends UnitTest {
    @Test(expected = InvalidLineException.class)
    public void cannotCreateLineFromAtoA() {
        new Line(A, A, 5);
    }
}
