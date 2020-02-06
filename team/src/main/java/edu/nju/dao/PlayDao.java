package edu.nju.dao;

import edu.nju.model.Play;
import edu.nju.util.ResultData;

import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:21 2020/1/8
 */
public interface PlayDao {

    ResultData insert(Play play);

    ResultData search(Map<String, Object> condition);

    ResultData query(Map<String, Object> condition);

    ResultData update(Play play);

    ResultData getPlaysByStudentId(Map<String, Object> condition);

    ResultData getPlayMembers(Map<String, Object> condition);
}
