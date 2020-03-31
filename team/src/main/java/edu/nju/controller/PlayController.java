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
    @PostMapping(value = "/search")
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

    /**
     * 获取组局的所有参与者
     * @param playId
     * @return
     */
    @GetMapping(value = "/member")
    public ResultData member(String playId) {
        if (StringUtils.isBlank(playId)) {
            return ResultData.errorMsg("playId is null");
        }
        return playService.getPlayMembers(playId);
    }

    /**
     * 根据学生from是否对学生to展示信息，获得学生from的信息
     * @param playId
     * @param fromStudentId
     * @param toStudentId
     * @return 如果学生from对学生to展示了信息，返回学生from的信息:Student ，否则返回空对象
     */
    @GetMapping(value = "/getShowStudent")
    public ResultData getShowStudent(String playId, String fromStudentId, String toStudentId) {
        if (StringUtils.isBlank(playId)) {
            return ResultData.errorMsg("playId is null");
        }
        if (StringUtils.isBlank(fromStudentId)) {
            return ResultData.errorMsg("fromStudentId is null");
        }
        if (StringUtils.isBlank(toStudentId)) {
            return ResultData.errorMsg("toStudentId is null");
        }
        return playService.getShowStudent(playId, fromStudentId, toStudentId);
    }

    /**
     * 在组局中添加向学生to展示学生from的信息
     * @param playId
     * @param fromStudentId
     * @param toStudentId
     * @return
     */
    @PostMapping(value = "/addShowStudent")
    public ResultData addShowStudent(String playId, String fromStudentId, String toStudentId) {
        if (StringUtils.isBlank(playId)) {
            return ResultData.errorMsg("playId is null");
        }
        if (StringUtils.isBlank(fromStudentId)) {
            return ResultData.errorMsg("fromStudentId is null");
        }
        if (StringUtils.isBlank(toStudentId)) {
            return ResultData.errorMsg("toStudentId is null");
        }
        return playService.addShowStudent(playId, fromStudentId, toStudentId);
    }

    /**
     * 在组局中删除向学生to展示学生from的信息
     * @param playId
     * @param fromStudentId
     * @param toStudentId
     * @return
     */
    @PostMapping(value = "/deleteShowStudent")
    public ResultData deleteShowStudent(String playId, String fromStudentId, String toStudentId) {
        if (StringUtils.isBlank(playId)) {
            return ResultData.errorMsg("playId is null");
        }
        if (StringUtils.isBlank(fromStudentId)) {
            return ResultData.errorMsg("fromStudentId is null");
        }
        if (StringUtils.isBlank(toStudentId)) {
            return ResultData.errorMsg("toStudentId is null");
        }
        return playService.deleteShowStudent(playId, fromStudentId, toStudentId);
    }

    /**
     * 签到
     * @param playId
     * @param studentId
     * @return
     */
    @PostMapping(value = "/signIn")
    public ResultData signIn(String playId, String studentId) {
        if (StringUtils.isBlank(playId)) {
            return ResultData.errorMsg("playId is null");
        }
        if (StringUtils.isBlank(studentId)) {
            return ResultData.errorMsg("studentId is null");
        }
        return playService.signIn(playId, studentId);
    }

}
