package edu.nju.model;

/**
 * 学生和游戏的熟练分的多对多的映射
 * @Author ：lycheeshell
 * @Date ：Created in 16:46 2020/1/8
 */
public class Adept extends Entity {

    private String adeptId;
    private Student student;
    private Game game;
    private int score;       //熟练分

    public Adept() {
        super();
    }

    public String getAdeptId() {
        return adeptId;
    }

    public void setAdeptId(String adeptId) {
        this.adeptId = adeptId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
