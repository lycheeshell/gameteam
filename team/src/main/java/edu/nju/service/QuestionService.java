package edu.nju.service;

import edu.nju.util.ResultData;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:12 2020/1/8
 */
public interface QuestionService {

    ResultData createQuestion(String gameId, String description, String a, String b, String c, String d, String correctOption);

    ResultData deleteQuestion(String questionId);

}
