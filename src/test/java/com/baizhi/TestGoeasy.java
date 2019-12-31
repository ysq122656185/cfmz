package com.baizhi;

import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @ClassName TestPoi
 * @Discription
 * @Author
 * @Date 2019/12/24 0024 9:54
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoeasy {
    @Test
    public void test1() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-9783a893192440dd9d83b1f35792109d");
        goEasy.publish("my_channel", "Hello, GoEasy!");
    }
}
