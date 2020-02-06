package edu.nju.controller;

import edu.nju.model.Student;
import edu.nju.service.StudentService;
import edu.nju.util.ResultData;
import org.apache.commons.lang.StringUtils;
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
     * 用户登录
     * paramType:
     * header：请求参数放置于Request Header，使用@RequestHeader获取
     * query：请求参数放置于请求地址，使用@RequestParam获取
     * path：（用于restful接口）-->请求参数的获取：@PathVariable
     *
     * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟），开发者需要将 code 发送到开发者服务器后台，
     *                      使用code 换取 session_key api，将 code 换成 openid 和 session_key
     * @return 用户信息
     */
    @PostMapping(value = "/wxlogin")
    public ResultData wechatLogin(String code) throws Exception {
        if (StringUtils.isBlank(code)) {
            return ResultData.errorMsg("code is null");
        }
        return studentService.wechatLogin(code);
    }

    /**
     * 学生登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public ResultData login(String account, String password) {
        if (StringUtils.isBlank(account)) {
            return ResultData.errorMsg("account is null");
        }
        if (StringUtils.isBlank(password)) {
            return ResultData.errorMsg("password is null");
        }
        return studentService.login(account, password);
    }

    /**
     * 学生注册
     * 'content-type' : application/json
     * @param student
     * @return
     */
    @PostMapping(value = "/register")
    public ResultData register(@RequestBody Student student) {
        return studentService.register(student);
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
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        return studentService.updateStudentImage(studentId, file, request);
    }

    /**
     * 查看学生信息
     * @param studentId
     * @return
     */
    @GetMapping(value = "/query")
    public ResultData query(String studentId) {
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        return studentService.query(studentId);
    }

    /**
     * 更新学生信息
     * 'content-type' : application/json
     * @param student
     * @return
     */
    @PostMapping(value = "/update")
    public ResultData update(@RequestBody Student student) {
        return studentService.update(student);
    }

}
