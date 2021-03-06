package edu.nju.dao;

import edu.nju.model.Question;
import edu.nju.util.ResultData;

import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 20:03 2020/1/30
 */
public interface QuestionDao {

    ResultData insert(Question question);

    ResultData delete(Map<String, Object> condition);

    ResultData query(Map<String, Object> condition);

}
