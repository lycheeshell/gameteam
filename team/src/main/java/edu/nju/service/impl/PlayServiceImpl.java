package edu.nju.service.impl;

import edu.nju.dao.ParticipantDao;
import edu.nju.dao.PlayDao;
import edu.nju.model.Participant;
import edu.nju.model.Play;
import edu.nju.service.PlayService;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:28 2020/1/8
 */
@Service
public class PlayServiceImpl implements PlayService {

    private static Logger logger = Logger.getLogger(PlayServiceImpl.class);

    @Autowired
    private PlayDao playDao;

    @Autowired
    private ParticipantDao participantDao;

    @Override
    public ResultData create(Play play) {
        ResultData result = null;
        ResultData response = playDao.insert(play);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to insert play to database");
        }
        if (response.isOK()) {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData search(Play play) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        if (play.getGameId() != null && play.getGameId().length() > 0) {
            map.put("gameId", play.getGameId());
        }
        if (play.getStartTime() != null && play.getStartTime().length() > 0) {
            map.put("startTime", play.getStartTime());
        }
        if (play.getEndTime() != null && play.getEndTime().length() > 0) {
            map.put("endTime", play.getEndTime());
        }
        if (play.getProvince() != null && play.getProvince().length() > 0) {
            map.put("province", play.getProvince());
        }
        if (play.getCity() != null && play.getCity().length() > 0) {
            map.put("city", play.getCity());
        }
        if (play.getMinAdeptScore() > 0) {
            map.put("minAdeptScore", play.getMinAdeptScore());
        }
        ResultData response = playDao.search(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query play error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData addParticipant(String playId, String studentId) {
        ResultData result;

        //检查是否超过组局的最大人数
        Map<String, Object> map = new HashMap<>();
        map.put("playId", playId);
        ResultData participantResponse = participantDao.query(map);
        List<Participant> participantList = (List<Participant>)participantResponse.getData();
        ResultData playResponse = playDao.query(map);
        List<Play> playList =  (List<Play>) playResponse.getData();
        if (participantList.size() >= playList.get(0).getMaxPerson()) {
            result = ResultData.empty();
            result.setMsg("Num of Student oversize");
            return result;
        }

        //加入新的组局的参与者
        Participant participant = new Participant();
        participant.setPlayId(playId);
        participant.setStudentId(studentId);
        ResultData insertResponse = participantDao.insert(participant);
        if (!insertResponse.isOK()) {
            result = ResultData.errorMsg("Fail to insert participant to database");
            return result;
        }
        result = ResultData.ok(insertResponse.getData());

        //如果组局参与者人数达到最小，向所有参与者发送通知 todo

        return result;
    }
}
