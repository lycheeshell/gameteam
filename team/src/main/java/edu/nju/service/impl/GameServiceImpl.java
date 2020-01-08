package edu.nju.service.impl;

import edu.nju.dao.GameDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:19 2020/1/8
 */
@Service
public class GameServiceImpl {

    private static Logger logger = Logger.getLogger(GameServiceImpl.class);

    @Autowired
    private GameDao gameDao;

}
