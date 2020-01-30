package edu.nju.model;

/**
 * 游戏熟练度测试的问卷
 * @Author ：lycheeshell
 * @Date ：Created in 21:38 2020/1/8
 */
public class Question extends Entity {

    private String questionId;
    private String gameId;
    private String description;   //题目描述
    private String a;             //A选项
    private String b;             //B选项
    private String c;             //C选项
    private String d;             //D选项
    private String correctOption; //正确选项

    public Question() {
        super();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }
}
