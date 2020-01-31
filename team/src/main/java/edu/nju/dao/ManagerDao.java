package edu.nju.dao;

import edu.nju.util.ResultData;

import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 21:14 2020/1/31
 */
public interface ManagerDao {

    ResultData login(Map<String, Object> condition);

}
