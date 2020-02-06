package edu.nju.controller;

import edu.nju.model.Adept;
import edu.nju.service.GameService;
import edu.nju.service.QuestionService;
import edu.nju.util.ResultData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:42 2020/1/5
 */
@RestController
@RequestMapping("/team/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    QuestionService questionService;

    /**
     * 根据id获取游戏
     * @param gameId
     * @return
     */
    @GetMapping(value = "/getGame")
    public ResultData getGame(String gameId) {
        if (StringUtils.isBlank(gameId)) {
            return ResultData.errorMsg("gameId is null");
        }
        return gameService.getGame(gameId);
    }

    /**
     * 搜索游戏
     * @param word 搜索框内容
     * @return
     */
    @GetMapping(value = "/search")
    public ResultData search(String word) {
        return gameService.search(word);
    }

    /**
     * 热门游戏
     * @param num  热门前几
     * @return
     */
    @GetMapping(value = "/hot")
    public ResultData getHotGames(int num) {
        if (num <= 0 || num > 100) {
            return ResultData.errorMsg("num range from 1 to 100");
        }
        return gameService.getHotGames(num);
    }

    /**
     * 用户参与的游戏列表
     * @param studentId
     * @return
     */
    @GetMapping(value = "/myGame")
    public ResultData myGames(String studentId) {
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        return gameService.getMyGames(studentId);
    }

    /**
     * 用户是否参与过该游戏，如果没有则添加用户在该游戏的熟练度为0
     * （通过是否有用户在该游戏的熟练分判断）
     * @param studentId
     * @param gameId
     * @return
     */
    @GetMapping(value = "/getAdept")
    public ResultData getAdept(String studentId, String gameId) {
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        if (StringUtils.isBlank(gameId)) {
            return ResultData.errorMsg("gameId is null");
        }
        return gameService.getAdept(studentId, gameId);
    }

    /**
     * 获取随机的某种游戏的熟练度测试题目
     * @param gameId
     * @param num
     * @return
     */
    @GetMapping(value = "/getQuiz")
    public ResultData getQuiz(String gameId, int num) {
        if (StringUtils.isBlank(gameId)) {
            return ResultData.errorMsg("gameId is null");
        }
        if (num <= 0 || num > 100) {
            return ResultData.errorMsg("num range from 1 to 100");
        }
        return gameService.getQuiz(gameId, num);
    }

    /**
     * 更新用户在某个游戏的熟练分
     * @param studentId
     * @param gameId
     * @param score
     * @return
     */
    @PostMapping(value = "/updateAdept")
    public ResultData updateAdept(String studentId, String gameId, int score) {
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        if (StringUtils.isBlank(gameId)) {
            return ResultData.errorMsg("gameId is null");
        }
        if (score < 0 || score > 100) {
            return ResultData.errorMsg("score range from 1 to 100");
        }
        return gameService.updateAdept(studentId, gameId, score);
    }

}
