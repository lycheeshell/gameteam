package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.GameDao;
import edu.nju.model.Game;
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
public class GameDaoImpl extends BaseDao implements GameDao {

    @Override
    public ResultData insert(Game game) {
        ResultData result;
        game.setGameId(IDGenerator.generate("GAME"));
        try {
            sqlSession.insert("nju.team.game.insert", game);
            result = ResultData.ok(game);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData update(Game game) {
        ResultData result;
        try {
            sqlSession.update("nju.team.game.update", game);
            result = ResultData.ok(game);
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData updateImage(Game game) {
        ResultData result;
        try {
            sqlSession.update("nju.team.game.updateImage", game);
            result = ResultData.ok(game);
        }
        catch (Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData delete(Map<String, Object> condition) {
        ResultData result;
        try {
            sqlSession.delete("nju.team.game.delete",condition);
            result = ResultData.ok();
        } catch ( Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData search(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Game> list = sqlSession.selectList("nju.team.game.search", condition);
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
    public ResultData hot(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Game> list = sqlSession.selectList("nju.team.game.hot", condition);
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
    public ResultData queryByStudentId(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Game> list = sqlSession.selectList("nju.team.game.studentGame", condition);
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
            List<Game> list = sqlSession.selectList("nju.team.game.query", condition);
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
