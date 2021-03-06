package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ManagerDao;
import edu.nju.model.Manager;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 21:40 2020/1/31
 */
@Repository
public class ManagerDaoImpl extends BaseDao implements ManagerDao {

    @Override
    public ResultData login(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Manager> list = sqlSession.selectList("nju.team.manager.query", condition);
            if (list.isEmpty()) {
                result = ResultData.empty(list);
            } else {
                result = ResultData.ok(list);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

}
