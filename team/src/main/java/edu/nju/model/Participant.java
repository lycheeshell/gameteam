package edu.nju.model;

/**
 * 组局和参与学生的多对多的关系映射
 * @Author ：lycheeshell
 * @Date ：Created in 21:34 2020/1/8
 */
public class Participant extends Entity {

    private String participantId;
    private Play play;
    private Student student;

    public Participant() {
        super();
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
