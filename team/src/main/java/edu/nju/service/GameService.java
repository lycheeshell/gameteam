package edu.nju.service;

import edu.nju.util.ResultData;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:12 2020/1/8
 */
public interface GameService {

    ResultData createGame(String name, String description);

    ResultData deleteGame(String gameId);

    ResultData updateGame(String gameId, String name, String description);

    ResultData updateGameImage(String gameId, MultipartFile file, HttpServletRequest request);

    ResultData search(String word);

    ResultData getHotGames(int num);

    ResultData getMyGames(String studentId);

    ResultData checkStudentJoined(String studentId, String gameId);

    ResultData getQuiz(String gameId, int num);
}
