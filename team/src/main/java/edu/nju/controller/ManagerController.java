package edu.nju.controller;

import edu.nju.model.Question;
import edu.nju.service.GameService;
import edu.nju.service.ManagerService;
import edu.nju.service.QuestionService;
import edu.nju.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
     * 创建游戏
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
     * 删除游戏
     * @param gameId
     * @return
     */
    @PostMapping(value = "/deleteGame")
    public ResultData deleteGame(String gameId) {
        return gameService.deleteGame(gameId);
    }

    /**
     * 更新游戏
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
     * 创建问题
     * 'content-type' : application/json
     * @param question
     * @return
     */
    @PostMapping(value = "/createQuestion")
    public ResultData createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    /**
     * 删除问题
     * @param questionId
     * @return
     */
    @PostMapping(value = "/deleteQuestion")
    public ResultData deleteQuestion(String questionId) {
        return questionService.deleteQuestion(questionId);
    }

}
