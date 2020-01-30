package edu.nju.service.impl;

import edu.nju.dao.GameDao;
import edu.nju.model.Game;
import edu.nju.service.GameService;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:19 2020/1/8
 */
@Service
public class GameServiceImpl implements GameService {

    private static Logger logger = Logger.getLogger(GameServiceImpl.class);

    @Autowired
    private GameDao gameDao;

    @Override
    public ResultData createGame(String name, String description) {
        Game game = new Game();
        game.setName(name);
        game.setDescription(description);
        game.setImageUrl("");
        ResultData result = null;
        ResultData response = gameDao.insert(game);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to insert game to database");
        }
        if (response.isOK()) {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData deleteGame(String gameId) {
        ResultData result = null;
        ResultData response = gameDao.delete(gameId);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to delete game from database");
        }
        if (response.isOK()) {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData updateGame(String gameId, String name, String description) {
        Game game = new Game();
        game.setGameId(gameId);
        game.setName(name);
        game.setDescription(description);
        ResultData result = null;
        ResultData response = gameDao.update(game);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to update game to database");
        }
        if (response.isOK()) {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData updateGameImage(String gameId, MultipartFile image){
        //todo:image
        return null;
    }

}
