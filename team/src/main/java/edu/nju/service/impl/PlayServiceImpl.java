package edu.nju.service.impl;

import edu.nju.dao.PlayDao;
import edu.nju.service.PlayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:28 2020/1/8
 */
@Service
public class PlayServiceImpl implements PlayService {

    private static Logger logger = Logger.getLogger(PlayServiceImpl.class);

    @Autowired
    private PlayDao playDao;
}
