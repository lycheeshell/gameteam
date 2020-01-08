package edu.nju.dao;

import org.apache.ibatis.session.SqlSession;
import javax.annotation.Resource;

/**
 * 该类用于管理持久层与数据库的连接sql session, 所有的Dao需要继承此方法
 * @Author ：lycheeshell
 * @Date ：Created in 16:15 2020/1/8
 */
public class BaseDao {
    @Resource
    protected SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
