package com.cainiao.training.util;

import org.junit.Assert;
import org.junit.Test;

public class ADemoUtilTest {

    @Test
    //TODO 最简单的 Helper/Util/Validation 层
    public void testIsPositive()  {
        Assert.assertFalse("返回值不为假", DemoUtil.isPositive(-1));
        Assert.assertFalse("返回值不为假", DemoUtil.isPositive(0));
        Assert.assertTrue("返回值不为真", DemoUtil.isPositive(1));
        Assert.assertFalse("返回值不为假", DemoUtil.isPositive((Integer)null));
        Assert.assertFalse("返回值不为假", DemoUtil.isPositive(Integer.valueOf(-1)));
        Assert.assertFalse("返回值不为假", DemoUtil.isPositive(Integer.valueOf(0)));
        Assert.assertTrue("返回值不为真", DemoUtil.isPositive(Integer.valueOf(1)));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme