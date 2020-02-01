package edu.nju.controller;

import edu.nju.service.GameService;
import edu.nju.service.ManagerService;
import edu.nju.service.QuestionService;
import edu.nju.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 23:52 2020/1/29
 */
@RestController
@RequestMapping("/team/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @Autowired
    GameService gameService;

    @Autowired
    QuestionService questionService;

    /**
     * 管理员登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping(value = "/login")
    public ResultData login(String account, String password) {
        return managerService.login(account, password);
    }

    /**
     * @param name 游戏名
     * @param description  描述
     * @return
     */
    @PostMapping(value = "/createGame")
    public ResultData createGame(String name, String description) {
        return gameService.createGame(name, description);
    }

    /**
     * 更新游戏的图片
     * @param gameId
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/updateGameImage")
    public ResultData updateGameImage(String gameId, @RequestParam("file")MultipartFile file, HttpServletRequest request) {
        return gameService.updateGameImage(gameId, file, request);
    }

    /**
     *
     * @param gameId
     * @return
     */
    @PostMapping(value = "/deleteGame")
    public ResultData deleteGame(String gameId) {
        return gameService.deleteGame(gameId);
    }

    /**
     *
     * @param gameId
     * @param name
     * @param description
     * @return
     */
    @PostMapping(value = "/updateGame")
    public ResultData updateGame(String gameId, String name, String description) {
        return gameService.updateGame(gameId, name, description);
    }

    /**
     *
     * @param gameId 对应的game_id
     * @param description  题目描述
     * @param a  选项A
     * @param b  选项B
     * @param c  选项C
     * @param d  选项D
     * @param correctOption 正确选项 (大写ABCD)
     * @return
     */
    @PostMapping(value = "/createQuestion")
    public ResultData createQuestion(String gameId, String description, String a, String b, String c, String d, String correctOption) {
        return questionService.createQuestion(gameId, description, a, b, c, d, correctOption);
    }

    /**
     *
     * @param questionId
     * @return
     */
    @PostMapping(value = "/deleteQuestion")
    public ResultData deleteQuestion(String questionId) {
        return questionService.deleteQuestion(questionId);
    }

}
