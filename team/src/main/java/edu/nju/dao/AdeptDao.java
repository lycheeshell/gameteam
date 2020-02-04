package edu.nju.dao;

import edu.nju.model.Adept;
import edu.nju.util.ResultData;

import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:20 2020/1/8
 */
public interface AdeptDao {

    ResultData query(Map<String, Object> condition);

    ResultData insert(Adept adept);

    ResultData update(Adept adept);

}
