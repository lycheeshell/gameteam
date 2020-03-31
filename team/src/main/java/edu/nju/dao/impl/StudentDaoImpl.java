package edu.nju.dao.impl;

import edu.nju.dao.BaseDao;
import edu.nju.dao.StudentDao;
import edu.nju.model.Student;
import edu.nju.util.IDGenerator;
import edu.nju.util.ResultData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:26 2020/1/8
 */
@Repository
public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public ResultData insert(Student student) {
        ResultData result;
        student.setStudentId(IDGenerator.generate("STUD"));
        try {
            sqlSession.insert("nju.team.student.insert", student);
            result = ResultData.ok(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData update(Student student) {
        ResultData result;
        try {
            sqlSession.update("nju.team.student.update", student);
            result = ResultData.ok(student);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData updateImage(Student student) {
        ResultData result;
        try {
            sqlSession.update("nju.team.student.updateImage", student);
            result = ResultData.ok(student);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData login(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Student> list = sqlSession.selectList("nju.team.student.loginQuery", condition);
            if (list.isEmpty()) {
                result = ResultData.empty(list);
            } else {
                result = ResultData.ok(list);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData query(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Student> list = sqlSession.selectList("nju.team.student.query", condition);
            if (list.isEmpty()) {
                result = ResultData.empty(list);
            } else {
                result = ResultData.ok(list.get(0));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData queryStudentByOpenid(Map<String, Object> condition) {
        ResultData result;
        try{
            List<Student> list = sqlSession.selectList("nju.team.student.openidQuery", condition);
            if (list.isEmpty()) {
                result = ResultData.empty(list);
            } else {
                result = ResultData.ok(list.get(0));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData updateCreditQuit(Map<String, Object> condition) {
        ResultData result;
        try{
            int updateNum = sqlSession.update("nju.team.student.updateCreditQuit", condition);
            if (updateNum != 1) {
                result = ResultData.empty();
            } else {
                result = ResultData.ok();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData updateCreditSignIn(Map<String, Object> condition) {
        ResultData result;
        try{
            int updateNum = sqlSession.update("nju.team.student.updateCreditSignIn", condition);
            if (updateNum != 1) {
                result = ResultData.empty();
            } else {
                result = ResultData.ok();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ResultData updateCreditUnsigned(Map<String, Object> condition){
        ResultData result;
        try{
            int updateNum = sqlSession.update("nju.team.student.updateCreditUnsigned", condition);
            result = ResultData.ok(Integer.valueOf(updateNum));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = ResultData.errorMsg(e.getMessage());
        }
        return result;
    }

}
