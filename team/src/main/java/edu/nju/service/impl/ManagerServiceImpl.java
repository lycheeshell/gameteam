package edu.nju.service.impl;

import edu.nju.dao.ManagerDao;
import edu.nju.service.ManagerService;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 21:12 2020/1/31
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private static Logger logger = Logger.getLogger(GameServiceImpl.class);

    @Autowired
    private ManagerDao managerDao;

    @Override
    public ResultData login(String account, String password) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        ResultData response = managerDao.login(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query manager error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

}
