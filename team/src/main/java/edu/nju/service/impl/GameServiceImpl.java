package edu.nju.service.impl;

import edu.nju.dao.AdeptDao;
import edu.nju.dao.GameDao;
import edu.nju.dao.QuestionDao;
import edu.nju.model.Adept;
import edu.nju.model.Game;
import edu.nju.model.Question;
import edu.nju.service.GameService;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:19 2020/1/8
 */
@Service
public class GameServiceImpl implements GameService {

    private static Logger logger = Logger.getLogger(GameServiceImpl.class);

    @Autowired
    private GameDao gameDao;

    @Autowired
    private AdeptDao adeptDao;

    @Autowired
    private QuestionDao questionDao;

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

    @Override
    public ResultData search(String word) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("word", "%" + word + "%");
        ResultData response = gameDao.search(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query game error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData getHotGames(int num) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("num", num);
        ResultData response = gameDao.hot(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query game error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData getMyGames(String studentId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        ResultData response = gameDao.queryByStudentId(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query game error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData checkStudentJoined(String studentId, String gameId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        map.put("gameId", gameId);
        ResultData response = adeptDao.query(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query game error");
        } else if (response.isEmpty()) {
            Adept adept = new Adept();
            adept.setStudentId(studentId);
            adept.setGameId(gameId);
            adept.setScore(0);
            ResultData insertResponse = adeptDao.insert(adept);
            result = ResultData.empty(insertResponse.getData()); //empty表示是刚创建的熟练度
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData getQuiz(String gameId, int num) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("gameId", gameId);
        ResultData response = questionDao.query(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query game error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            List<Question> list = (List<Question>)response.getData();
            if (list.size() <= num) {
                result = ResultData.ok(response.getData());
            } else {
                Collections.shuffle(list);
                list = list.subList(0, num);
                result = ResultData.ok(list);
            }
        }
        return result;
    }

}
