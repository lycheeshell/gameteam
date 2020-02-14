package edu.nju.dao.impl;

import edu.nju.dao.AdeptDao;
import edu.nju.dao.BaseDao;
import edu.nju.model.Adept;
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
public class AdeptDaoImpl extends BaseDao implements AdeptDao {

    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Adept> list = sqlSession.selectList("nju.team.adept.query", condition);
            if (list.isEmpty()) {
                result = ResultData.empty(list);
            } else {
                result = ResultData.ok(list.get(0));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData insert(Adept adept) {
        ResultData result;
        adept.setAdeptId(IDGenerator.generate("ADPT"));
        try {
            sqlSession.insert("nju.team.adept.insert", adept);
            result = ResultData.ok(adept);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData update(Adept adept) {
        ResultData result;
        try {
            sqlSession.update("nju.team.adept.update", adept);
            result = ResultData.ok(adept);
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }
}
