package edu.nju.dao;

import edu.nju.model.Game;
import edu.nju.util.ResultData;

import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:20 2020/1/8
 */
public interface GameDao {

    ResultData insert(Game game);

    ResultData update(Game game);

    ResultData updateImage(Game game);

    ResultData delete(String gameId);

    ResultData search(Map<String, Object> condition);

    ResultData hot(Map<String, Object> condition);

    ResultData queryByStudentId(Map<String, Object> condition);

}
