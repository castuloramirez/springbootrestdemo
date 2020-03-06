package at.drei.rest.util;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;


public class StringHelperTest {

    @Test
    public void getPointNotNull() {
        Assert.assertNotNull(StringHelper.getPoint("40,50"));
    }


    @Test
    public void getCorrectPoint() {
        Assert.assertSame(StringHelper.getPoint("40,50"), new Point(40, 50));
    }

    @Test
    public void getEqualsPoint() {
        Assert.assertEquals(StringHelper.getPoint("40,50"), new Point(40, 50));
    }

    @Test(expected = NullPointerException.class)
    public void checkParameterPoint() {
        StringHelper.getPoint("40,40");
    }
}