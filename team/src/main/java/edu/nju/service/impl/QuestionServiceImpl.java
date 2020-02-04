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
    public ResultData createQuestion(Question question) {
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
