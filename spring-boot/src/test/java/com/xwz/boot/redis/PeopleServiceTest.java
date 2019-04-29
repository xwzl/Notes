package com.xwz.boot.redis;

import com.xwz.boot.SpringBootsTest;
import com.xwz.boot.model.People;
import com.xwz.boot.service.PeopleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuweizhi
 * @date 2019/04/23 16:28
 */
public class PeopleServiceTest extends SpringBootsTest {

    @Autowired
    private PeopleService peopleService;

    @Test
    public void getUser() {
        People byId = peopleService.findById(5);
        System.out.println(byId);
    }
}
