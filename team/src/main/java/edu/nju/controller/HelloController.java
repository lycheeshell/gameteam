package edu.nju.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 18:51 2020/2/11
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String getGame() {
        return "欢迎来到微信小程序——南哪儿组局";
    }

}
