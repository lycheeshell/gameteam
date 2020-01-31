package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.GameDao;
import edu.nju.model.Game;
import edu.nju.util.IDGenerator;
import edu.nju.util.ResultData;
import org.springframework.stereotype.Repository;

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
    public ResultData delete(String gameId) {
        ResultData result;
        try {
            sqlSession.delete("nju.team.game.delete",gameId);
            result = ResultData.ok();
        } catch ( Exception e) {
            e.printStackTrace();
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }
}
