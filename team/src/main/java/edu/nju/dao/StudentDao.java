package edu.nju.dao;

import edu.nju.model.Student;
import edu.nju.util.ResultData;

import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:21 2020/1/8
 */
public interface StudentDao {

    ResultData insert(Student student);

    ResultData update(Student student);

    ResultData updateImage(Student student);

    ResultData login(Map<String, Object> condition);

    ResultData query(Map<String, Object> condition);

    ResultData queryStudentByOpenid(Map<String, Object> condition);

    ResultData updateCreditQuit(Map<String, Object> condition);

}
