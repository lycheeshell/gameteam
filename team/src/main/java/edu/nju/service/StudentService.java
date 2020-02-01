package edu.nju.service;

import edu.nju.util.ResultData;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:13 2020/1/8
 */
public interface StudentService {

    ResultData login(String account, String password);

    ResultData register(String account, String password, String phone, String email, int sex, String birthday, String school, int startYear);

    ResultData updateStudentImage(String studentId, MultipartFile file, HttpServletRequest request);

}
