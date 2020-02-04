package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.QuestionDao;
import edu.nju.model.Question;
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
public class QuestionDaoImpl extends BaseDao implements QuestionDao {

    @Override
    public ResultData insert(Question question) {
        ResultData result;
        question.setQuestionId(IDGenerator.generate("QUES"));
        try {
            sqlSession.insert("nju.team.question.insert", question);
            result = ResultData.ok(question);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData delete(String questionId) {
        ResultData result;
        try {
            sqlSession.delete("nju.team.question.delete",questionId);
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
            List<Question> list = sqlSession.selectList("nju.team.question.query", condition);
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
