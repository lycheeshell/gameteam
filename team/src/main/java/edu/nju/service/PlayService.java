package edu.nju.service;

import edu.nju.model.Play;
import edu.nju.util.ResultData;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:12 2020/1/8
 */
public interface PlayService {

    ResultData create(Play play);

    ResultData search(Play play);

    ResultData addParticipant(String playId, String studentId);

    ResultData getPlaysByStudentId(String studentId);

    ResultData leaveParticipant(String playId, String studentId);

    ResultData getPlayMembers(String playId);

}
