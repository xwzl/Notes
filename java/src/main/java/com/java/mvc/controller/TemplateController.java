package com.java.mvc.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用注解的使用
 *
 * @author xuweizhi
 * @date 2019/03/31 13:14
 */
@RestController
public class TemplateController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/index3")
    public String index3() {
        return "index";
    }

    @RequestMapping("/index1")
    public String index1() {
        List<String> a = new ArrayList<>();
        a.add("12");

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i + 1));
        }
        return "index1";
    }

    @RequestMapping("/hello5")
    public String hello5(@RequestParam(value = "${server.port}") String name) {
        return "666";
    }

    @RequestMapping(value = "/string", method = RequestMethod.POST)
    public @ResponseBody
    String readString(@RequestBody String string) {
        return "Read string '" + string + "'";
    }
}
