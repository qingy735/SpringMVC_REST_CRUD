package cn.qingy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Qing_Y
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "success";
    }

}
