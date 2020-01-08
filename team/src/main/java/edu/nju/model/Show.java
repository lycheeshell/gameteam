package edu.nju.model;

/**
 * 学生from将自己的信息展示给学生to
 * @Author ：lycheeshell
 * @Date ：Created in 21:36 2020/1/8
 */
public class Show extends Entity {

    private String showId;
    private Play play;
    private Student from;
    private Student to;

    public Show() {
        super();
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public Student getFrom() {
        return from;
    }

    public void setFrom(Student from) {
        this.from = from;
    }

    public Student getTo() {
        return to;
    }

    public void setTo(Student to) {
        this.to = to;
    }
}
