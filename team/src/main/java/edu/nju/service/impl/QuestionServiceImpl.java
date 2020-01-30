package edu.nju.service.impl;

import edu.nju.dao.QuestionDao;
import edu.nju.model.Question;
import edu.nju.service.QuestionService;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:19 2020/1/8
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private static Logger logger = Logger.getLogger(QuestionServiceImpl.class);

    @Autowired
    private QuestionDao questionDao;

    @Override
    public ResultData createQuestion(String gameId, String description, String a, String b, String c, String d, String correctOption) {
        Question question = new Question();
        question.setGameId(gameId);
        question.setDescription(description);
        question.setA(a);
        question.setB(b);
        question.setC(c);
        question.setD(d);
        question.setCorrectOption(correctOption);
        ResultData result = null;
        ResultData response = questionDao.insert(question);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to insert question to database");
        }
        if (response.isOK()) {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData deleteQuestion(String questionId) {
        ResultData result = null;
        ResultData response = questionDao.delete(questionId);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to delete question from database");
        }
        if (response.isOK()) {
            result = ResultData.ok(response.getData());
        }
        return result;
    }
}
