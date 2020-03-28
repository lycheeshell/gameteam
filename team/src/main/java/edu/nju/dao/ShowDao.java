package edu.nju.dao;

import edu.nju.model.Show;
import edu.nju.util.ResultData;

import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:20 2020/1/8
 */
public interface ShowDao {

    ResultData insert(Show show);

    ResultData query(Map<String, Object> condition);

    ResultData delete(Map<String, Object> condition);

}
