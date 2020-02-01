package edu.nju.service.impl;

import edu.nju.dao.StudentDao;
import edu.nju.model.Student;
import edu.nju.service.StudentService;
import edu.nju.util.ResultData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:29 2020/1/8
 */
@Service
public class StudentServiceImpl implements StudentService {

    private static Logger logger = Logger.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Override
    public ResultData login(String account, String password) {
        ResultData result;
        Map<String, Object> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        ResultData response = studentDao.login(map);
        if (!response.isOK()) {
            result = ResultData.errorMsg("query student error");
        } else if (response.isEmpty()) {
            result = ResultData.empty(response.getData());
        } else {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData register(String account, String password, String phone, String email, int sex, String birthday, String school, int startYear) {
        Student student = new Student();
        student.setAccount(account);
        student.setPassword(password);
        student.setPhone(phone);
        student.setEmail(email);
        student.setSex(sex);
        student.setBirthday(birthday);
        student.setSchool(school);
        student.setStartYear(startYear);
        student.setCredit(100);
        //todo: wx_id,openid

        ResultData result = null;
        ResultData response = studentDao.insert(student);
        if (!response.isOK()) {
            result = ResultData.errorMsg("Fail to insert student to database");
        }
        if (response.isOK()) {
            result = ResultData.ok(response.getData());
        }
        return result;
    }

    @Override
    public ResultData updateStudentImage(String studentId, MultipartFile file, HttpServletRequest request) {
        ResultData result = null;
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "") {
            String fileSuffix = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1):"";//文件后缀

            String[] suffixArray = {"jpg", "jpeg", "png", "bmp"};
            boolean legal = false;
            for (String suf : suffixArray) {
                if (suf.equals(fileSuffix.toLowerCase())) {
                    legal = true;
                    break;
                }
            }
            if (!legal) {
                result = ResultData.errorMsg("图片格式非法");
                return result;
            }
            fileName = studentId + "." + fileSuffix;//新的文件名: gameId.type

            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/static/images/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("/"); //文件存储位置
            //先判断文件是否存在
            //获取文件夹路径
            File folder = new File(path + "static/images/student/");
            //如果文件夹不存在则创建
            if (!folder.exists()) {
                folder.mkdirs();
                System.out.println("create folder");
            }
            //将图片存入文件夹
            File targetFile = new File(folder, fileName);
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                String url = returnUrl + "student/" + fileName;
                Student student = new Student();
                student.setStudentId(studentId);
                student.setPhotoUrl(url);
                ResultData response = studentDao.updateImage(student);
                if (!response.isOK()) {
                    result = ResultData.errorMsg("Fail to update image of student to database");
                }
                if (response.isOK()) {
                    result = ResultData.ok(response.getData());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = ResultData.errorMsg("Fail to upload file");
            }
        }
        return result;
    }
}
