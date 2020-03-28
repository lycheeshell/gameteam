package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ShowDao;
import edu.nju.model.Show;
import edu.nju.util.IDGenerator;
import edu.nju.util.ResultData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:21 2020/1/8
 */
@Repository
public class ShowDaoImpl extends BaseDao implements ShowDao {

    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Show> list = sqlSession.selectList("nju.team.show.query", condition);
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

    @Override
    public ResultData insert(Show show) {
        ResultData result;
        show.setShowId(IDGenerator.generate("SHOW"));
        try {
            sqlSession.insert("nju.team.show.insert", show);
            result = ResultData.ok(show);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData delete(Map<String, Object> condition) {
        ResultData result;
        try {
            sqlSession.delete("nju.team.show.delete", condition);
            result = ResultData.ok();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }
}
