package edu.nju.model;

/**
 * 学生from将自己的信息展示给学生to
 * @Author ：lycheeshell
 * @Date ：Created in 21:36 2020/1/8
 */
public class Show extends Entity {

    private String showId;
    private String playId;
    private String fromStudetnId;
    private String toStudentId;

    public Show() {
        super();
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getFromStudetnId() {
        return fromStudetnId;
    }

    public void setFromStudetnId(String fromStudetnId) {
        this.fromStudetnId = fromStudetnId;
    }

    public String getToStudentId() {
        return toStudentId;
    }

    public void setToStudentId(String toStudentId) {
        this.toStudentId = toStudentId;
    }
}
