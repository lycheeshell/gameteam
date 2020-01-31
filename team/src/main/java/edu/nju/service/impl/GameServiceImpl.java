package edu.nju.service.impl;

import edu.nju.dao.GameDao;
import edu.nju.model.Game;
import edu.nju.service.GameService;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
    public ResultData updateGameImage(String gameId, MultipartFile file, HttpServletRequest request) {
        ResultData result = null;
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "") {
            String fileSuffix = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1):"";//文件后缀

            String[] suffixArray = {"jpg", "jpeg", "png", "bmp"};
            boolean legal = false;
            for (String suf : suffixArray) {
                if (suf.equals(fileSuffix.toLowerCase())) {
                    legal = true;
                    break;
                }
            }
            if (!legal) {
                result = ResultData.errorMsg("图片格式非法");
                return result;
            }
            fileName = gameId + "." + fileSuffix;//新的文件名: gameId.type

            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/static/images/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("/"); //文件存储位置
            //先判断文件是否存在
            //获取文件夹路径
            File folder = new File(path + "static/images/game/");
            //如果文件夹不存在则创建
            if (!folder.exists()) {
                folder.mkdirs();
                System.out.println("create folder");
            }
            //将图片存入文件夹
            File targetFile = new File(folder, fileName);
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                String url = returnUrl + "game/" + fileName;
                Game game = new Game();
                game.setGameId(gameId);
                game.setImageUrl(url);
                ResultData response = gameDao.updateImage(game);
                if (!response.isOK()) {
                    result = ResultData.errorMsg("Fail to update image of game to database");
                }
                if (response.isOK()) {
                    result = ResultData.ok(response.getData());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = ResultData.errorMsg("Fail to upload file");
            }
        }
        return result;
    }

}
