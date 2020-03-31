package edu.nju.model;

/**
 * 组局和参与学生的多对多的关系映射
 * @Author ：lycheeshell
 * @Date ：Created in 21:34 2020/1/8
 */
public class Participant extends Entity {

    private String participantId;
    private String playId;
    private String studentId;
    private int signIn;     //0未签到， 1已签到

    public Participant() {
        super();
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSignIn() {
        return signIn;
    }

    public void setSignIn(int signIn) {
        this.signIn = signIn;
    }
}
