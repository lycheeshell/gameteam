package edu.nju.controller;

import edu.nju.service.StudentService;
import edu.nju.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:33 2020/1/5
 */
@RestController
@RequestMapping("/team/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 学生登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public ResultData login(String account, String password) {
        return studentService.login(account, password);
    }

    /**
     * 学生注册
     * @param account
     * @param password
     * @param phone
     * @param email
     * @param sex
     * @param birthday
     * @param school
     * @param startYear
     * @return
     */
    @PostMapping(value = "/register")
    public ResultData register(String account, String password, String phone, String email, int sex, String birthday, String school, int startYear) {
        return studentService.register(account, password, phone, email, sex, birthday, school, startYear);
    }

    /**
     * 更新学生头像
     * @param studentId
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/updateStudentPhoto")
    public ResultData updateStudentImage(String studentId, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return studentService.updateStudentImage(studentId, file, request);
    }

}
