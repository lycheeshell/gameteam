package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.PlayDao;
import edu.nju.model.Play;
import edu.nju.util.IDGenerator;
import edu.nju.util.ResultData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:24 2020/1/8
 */
@Repository
public class PlayDaoImpl extends BaseDao implements PlayDao {

    @Override
    public ResultData insert(Play play) {
        ResultData result;
        play.setPlayId(IDGenerator.generate("PLAY"));
        play.setStatus(0);
        try {
            sqlSession.insert("nju.team.play.insert", play);
            result = ResultData.ok(play);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData search(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Play> list = sqlSession.selectList("nju.team.play.search", condition);
            if (list.isEmpty()) {
                result = ResultData.empty(list);
            } else {
                result = ResultData.ok(list);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Play> list = sqlSession.selectList("nju.team.play.query", condition);
            if (list.isEmpty()) {
                result = ResultData.empty(list);
            } else {
                result = ResultData.ok(list);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData update(Play play) {
        ResultData result;
        try {
            sqlSession.update("nju.team.play.update", play);
            result = ResultData.ok(play);
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }
}
