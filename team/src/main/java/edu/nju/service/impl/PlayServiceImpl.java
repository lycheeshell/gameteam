package edu.nju.service.impl;

import edu.nju.dao.ParticipantDao;
import edu.nju.dao.PlayDao;
import edu.nju.dao.ShowDao;
import edu.nju.dao.StudentDao;
import edu.nju.model.Participant;
import edu.nju.model.Play;
import edu.nju.model.Show;
import edu.nju.model.Student;
import edu.nju.service.PlayService;
import edu.nju.util.MailUtil;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private StudentDao studentDao;

    @Autowired
    private ParticipantDao participantDao;

    @Autowired
    private ShowDao showDao;

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
        if (play.getCounty() != null && play.getCounty().length() > 0) {
            map.put("county", play.getCounty());
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

    private boolean earlierThenNow(String time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = df.parse(time);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }

        if(d.before(new Date())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public ResultData addParticipant(String playId, String studentId) {
        ResultData result;

        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        //检查信誉分是否有60
        ResultData creditResponse = studentDao.query(map);
        Student student = (Student) creditResponse.getData();
        if (student.getCredit() < 60) {
            result = ResultData.ok();
            result.setData(Integer.valueOf(-1));
            return result;
        }

        //检查是否超过组局的最大人数
        map.put("playId", playId);
        ResultData participantResponse = participantDao.query(map);
        List<Participant> participantList = (List<Participant>) participantResponse.getData();
        ResultData playResponse = playDao.query(map);
        List<Play> playList = (List<Play>) playResponse.getData();
        if (participantList.size() >= playList.get(0).getMaxPerson()) {
            result = ResultData.empty();
            result.setMsg("已达组局的最大人数，加入失败");
            return result;
        }

        //检查是否超过组局的开始时间
        String playStartTime = playList.get(0).getStartTime();
        if (earlierThenNow(playStartTime)) {
            result = ResultData.errorMsg("已超过组局时间！");
            return result;
        }

        //加入新的组局的参与者
        Participant participant = new Participant();
        participant.setPlayId(playId);
        participant.setStudentId(studentId);
        participant.setSignIn(0);
        ResultData insertResponse = participantDao.insert(participant);
        if (!insertResponse.isOK()) {
            result = ResultData.errorMsg("Fail to insert participant to database");
            return result;
        }
        result = ResultData.ok(insertResponse.getData());

        //如果组局参与者人数达到最小，更新游戏的状态为1
        if (playList.get(0).getMinPerson() <= 1 + participantList.size()) {
            Play play = new Play();
            play.setPlayId(playId);
            play.setStatus(1);
            ResultData updateResponse = playDao.update(play);
            if (!updateResponse.isOK()) {
                result = ResultData.errorMsg("Fail to update play to database");
                return result;
            }

            //发送组局成功的邮件
            Map<String, Object> mapTmp = new HashMap<>();
            mapTmp.put("playId", playId);
            ResultData playQueryResponse = playDao.query(mapTmp);
            Play pla = ((List<Play>) playQueryResponse.getData()).get(0);
            StringBuilder sb = new StringBuilder();
            sb.append("您参与的在").append(pla.getProvince()).append(pla.getCity()).append(pla.getCounty())
                    .append("于").append(pla.getStartTime())
                    .append("开始的线下组局已达最少参与人数，请您到时签到并参与。如未签到，将扣除10分的信誉分。");
            String mailMessage = sb.toString();
            try {
                for (Participant par : participantList) {
                    mapTmp.clear();
                    mapTmp.put("studentId", par.getStudentId());
                    ResultData stuQueryResponse = studentDao.query(mapTmp);
                    Student stu = (Student) stuQueryResponse.getData();
                    if (stu.getEmail().length() > 1) {
                        MailUtil.sendMail(stu.getEmail(), "组局成功通知", mailMessage);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
//                result = ResultData.errorMsg("Fail to send mail");
//                return result;
            }
        }

        return result;
    }

    @Override
    public ResultData getPlaysByStudentId(String studentId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        ResultData response = playDao.getPlaysByStudentId(map);
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
    @Transactional
    public ResultData leaveParticipant(String playId, String studentId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("playId", playId);
        map.put("studentId", studentId);

        //检查组局是否处于状态0或1
        ResultData queryResponse = playDao.query(map);
        Play play = ((List<Play>) queryResponse.getData()).get(0);
        if (play.getStatus() > 1) {
            result = ResultData.errorMsg("游戏已结束，退出失败");
            return result;
        }

        //检查是否超过组局的开始时间
        String playStartTime = play.getStartTime();
        if (earlierThenNow(playStartTime)) {
            result = ResultData.errorMsg("已超过组局时间！");
            return result;
        }

        //删除参与者
        ResultData deleteResponse = participantDao.delete(map);
        if (!deleteResponse.isOK()) {
            result = ResultData.errorMsg("Fail to delete participant from database");
            return result;
        }
        if (((Integer)deleteResponse.getData()) == 0) {
            result = ResultData.errorMsg("尚未加入该组局！");
            return result;
        }
        result = ResultData.ok(deleteResponse.getData());

        //如果组局人数刚好等于最小人数，将组局状态设置为0
        if (play.getStatus() == 1) {

            //扣除退出者的信誉分
            Map<String, Object> studentMap = new HashMap<>();
            map.put("studentId", studentId);
            ResultData creditResponse = studentDao.updateCreditQuit(studentMap);
            if (!creditResponse.isOK() || creditResponse.isEmpty()) {
                result = ResultData.errorMsg("Fail to update student credit to database");
                return result;
            }

            ResultData participantResponse = participantDao.query(map);
            List<Participant> participantList = (List<Participant>) participantResponse.getData();
            if (participantList.size() == play.getMinPerson()) {
                play.setStatus(0);
                ResultData updateResponse = playDao.update(play);
                if (!updateResponse.isOK()) {
                    result = ResultData.errorMsg("Fail to update play to database");
                    return result;
                }

                //向其他学生发送组局失败的邮件
                Map<String, Object> mapTmp = new HashMap<>();
                StringBuilder sb = new StringBuilder();
                sb.append("由于有人退出，您参与的在").append(play.getProvince()).append(play.getCity()).append(play.getCounty())
                        .append("于").append(play.getStartTime())
                        .append("开始的线下组局的人数少于最少参与人数，请您继续等待新的参与者。");
                String mailMessage = sb.toString();
                try {
                    for (Participant par : participantList) {
                        mapTmp.clear();
                        mapTmp.put("studentId", par.getStudentId());
                        ResultData stuQueryResponse = studentDao.query(mapTmp);
                        Student stu = (Student) stuQueryResponse.getData();
                        if (stu.getEmail().length() > 1) {
                            MailUtil.sendMail(stu.getEmail(), "组局人员退出变动通知", mailMessage);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
//                result = ResultData.errorMsg("Fail to send mail");
//                return result;
                }
            }
        }

        return result;
    }

    @Override
    public ResultData getPlayMembers(String playId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("playId", playId);
        ResultData response = playDao.getPlayMembers(map);
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
    public ResultData getShowStudent(String playId, String fromStudentId, String toStudentId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("playId", playId);
        map.put("fromStudentId", fromStudentId);
        map.put("toStudentId", toStudentId);
        ResultData response = showDao.query(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query show error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            map.clear();
            map.put("studentId", fromStudentId);
            ResultData studentResponse = studentDao.query(map);
            if (!studentResponse.isOK()) {
                result = ResultData.errorMsg("Fail to query student from database");
            } else {
                result = ResultData.ok(studentResponse.getData());
            }
        }
        return result;
    }

    @Override
    public ResultData addShowStudent(String playId, String fromStudentId, String toStudentId) {
        ResultData result;
        Show show = new Show();
        show.setPlayId(playId);
        show.setFromStudentId(fromStudentId);
        show.setToStudentId(toStudentId);
        ResultData response = showDao.insert(show);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to insert show to database");
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData deleteShowStudent(String playId, String fromStudentId, String toStudentId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("playId", playId);
        map.put("fromStudentId", fromStudentId);
        map.put("toStudentId", toStudentId);
        ResultData response = showDao.delete(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query play error");
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    @Transactional
    public ResultData signIn(String playId, String studentId) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("playId", playId);

        //检查组局是否在时间范围内
        ResultData queryResponse = playDao.query(map);
        Play play = ((List<Play>) queryResponse.getData()).get(0);
        String startTime = play.getStartTime();
        String endTime = play.getEndTime();
        if (!(earlierThenNow(startTime) && !earlierThenNow(endTime))) {
            result = ResultData.errorMsg("请在组局时间范围内签到！");
            return result;
        }

        Participant participant = new Participant();
        participant.setPlayId(playId);
        participant.setStudentId(studentId);
        participant.setSignIn(1);
        ResultData response = participantDao.update(participant);
        if (((Integer)response.getData()) == 0) {
            result = ResultData.errorMsg("尚未加入该组局！");
            return result;
        }
        if (!response.isOK()) {
            result = ResultData.errorMsg("update participant error");
        } else {
            result = ResultData.ok(response.getData());
        }

        map.clear();
        map.put("studentId", studentId);
        ResultData studentResponse = studentDao.updateCreditSignIn(map);
        if (!studentResponse.isOK()) {
            result = ResultData.errorMsg("Fail to update student credit to database");
            return result;
        }

        return result;
    }
}
