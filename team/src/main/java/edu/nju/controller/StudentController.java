package edu.nju.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:33 2020/1/5
 */
@RestController
@RequestMapping("/team/student")
public class StudentController {

    @GetMapping("")
    public String login() {
        return "要登录的哦～";
    }

}
