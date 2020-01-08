package edu.nju.service.impl;

import edu.nju.dao.StudentDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:29 2020/1/8
 */
@Service
public class StudentServiceImpl {

    private static Logger logger = Logger.getLogger(GameServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

}
