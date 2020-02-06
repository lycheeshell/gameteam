package edu.nju.controller;

import edu.nju.model.Play;
import edu.nju.service.PlayService;
import edu.nju.util.ResultData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:43 2020/1/5
 */
@RestController
@RequestMapping("/team/play")
public class PlayController {

    @Autowired
    PlayService playService;

    /**
     * 创建组局，创建后不可修改
     * 'content-type' : application/json
     * @param play
     * @return
     */
    @PostMapping(value = "/create")
    public ResultData create(@RequestBody Play play) {
        return playService.create(play);
    }

    /**
     * 按条件查找组局
     * @param play
     * @return
     */
    @GetMapping(value = "/search")
    public ResultData search(@RequestBody Play play) {
        return playService.search(play);
    }

    /**
     * 学生参加组局
     * @param playId
     * @param studentId
     * @return
     */
    @PostMapping(value = "/addParticipant")
    public ResultData addParticipant(String playId, String studentId) {
        if (StringUtils.isBlank(playId)) {
            return ResultData.errorMsg("playId is null");
        }
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        return playService.addParticipant(playId, studentId);
    }

    /**
     * 获取学生参与的组局
     * @param studentId
     * @return
     */
    @GetMapping(value = "/myPlay")
    public ResultData myPlay(String studentId) {
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        return playService.getPlaysByStudentId(studentId);
    }

    /**
     * 学生退出组局
     * @param playId
     * @param studentId
     * @return
     */
    @PostMapping(value = "/leaveParticipant")
    public ResultData leaveParticipant(String playId, String studentId) {
        if (StringUtils.isBlank(playId)) {
            return ResultData.errorMsg("playId is null");
        }
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        return playService.leaveParticipant(playId, studentId);
    }


}
