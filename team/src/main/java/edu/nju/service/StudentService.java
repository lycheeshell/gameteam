package edu.nju.service;

import edu.nju.model.Student;
import edu.nju.util.ResultData;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:13 2020/1/8
 */
public interface StudentService {

    ResultData wechatLogin(String code);

    ResultData login(String account, String password);

    ResultData register(Student student);

    ResultData updateStudentImage(String studentId, MultipartFile file, HttpServletRequest request);

    ResultData update(Student student);

}
