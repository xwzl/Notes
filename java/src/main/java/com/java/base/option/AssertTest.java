package com.java.base.option;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * @author xuweizhi
 * @since 2019-08-20
 */
public class AssertTest {

    @Test
    public void testAssert() {
        Assert.hasText("", "这是一个错误消息！");
    }

}
