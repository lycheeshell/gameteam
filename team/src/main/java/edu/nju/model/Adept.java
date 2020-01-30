package edu.nju.model;

/**
 * 学生和游戏的熟练分的多对多的映射
 * @Author ：lycheeshell
 * @Date ：Created in 16:46 2020/1/8
 */
public class Adept extends Entity {

    private String adeptId;
    private String studentId;
    private String gameId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
