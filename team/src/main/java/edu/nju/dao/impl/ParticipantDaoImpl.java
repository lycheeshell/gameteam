package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.ParticipantDao;
import edu.nju.model.Participant;
import edu.nju.util.IDGenerator;
import edu.nju.util.ResultData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 20:05 2020/1/30
 */
@Repository
public class ParticipantDaoImpl extends BaseDao implements ParticipantDao {

    @Override
    public ResultData insert(Participant participant) {
        ResultData result;
        participant.setParticipantId(IDGenerator.generate("PART"));
        try {
            sqlSession.insert("nju.team.participant.insert", participant);
            result = ResultData.ok(participant);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData delete(Map<String, Object> condition) {
        ResultData result;
        try {
            sqlSession.delete("nju.team.participant.delete",condition);
            result = ResultData.ok();
        } catch ( Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Participant> list = sqlSession.selectList("nju.team.participant.query", condition);
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
}