package edu.nju.service;

import edu.nju.dao.PlayDao;
import edu.nju.dao.StudentDao;
import edu.nju.model.Play;
import edu.nju.util.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @Author ：lycheeshell
 * @Date ：Created in 16:03 2020/3/31
 */
@Component
public class ScheduleTask {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PlayDao playDao;

    @Autowired
    private StudentDao studentDao;

    //每天23:59:00更新未签到参与者的信誉分
    @Scheduled(cron = "0 59 23 * * ?")
    public void scheduledTask1() {
        Date currentTime = new Date();
        String dateString = sdf.format(currentTime);
        System.out.println(dateString + ": 开始更新未签到的信誉分");

        //查看今天进行的所有play
        ResultData playResponse = playDao.queryOneDay();
        if (!playResponse.isOK()) {
            System.out.println(dateString + ": " + playResponse.getMsg());
            return;
        }
        List<Play> list = (List<Play>) playResponse.getData();

        System.out.println("今日的play数量: " + list.size());

        Map<String, Object> map = new HashMap<>();
        //对每一个play的未签到的学生，扣除信誉分25分
        for (Play play : list) {
            if (play.getStatus() == 1) {
                String playId = play.getPlayId();
                map.clear();
                map.put("playId", playId);
                ResultData studentResponse = studentDao.updateCreditUnsigned(map);
                if (!playResponse.isOK()) {
                    System.out.println("ERROR: " + dateString + ", playId: " + playId + ",errorMsg: " + studentResponse.getMsg());
                    return;
                }
                Integer updateNum = (Integer) studentResponse.getData();
                System.out.println(dateString + ", playId: " + playId + ",扣分人数: " + updateNum);

            }
        }

        //更新play的状态
        for (Play play : list) {
            if (play.getStatus() == 1) {
                play.setStatus(3);
            } else if (play.getStatus() == 0) {
                play.setStatus(2);
            }
            ResultData updateResponse = playDao.update(play);
            if (!updateResponse.isOK()) {
                System.out.println("ERROR: " + dateString + ", playId: " + play.getPlayId() + ",errorMsg: " + updateResponse.getMsg());
                return;
            }
            System.out.println(dateString + ", playId: " + play.getPlayId() + ",更新后状态: " + play.getStatus());
        }

    }

}
