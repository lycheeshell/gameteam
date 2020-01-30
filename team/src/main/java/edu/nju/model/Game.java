package edu.nju.model;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:42 2020/1/8
 */
public class Game extends Entity {

    private String gameId;
    private String name;
    private String description;
    private String imageUrl;    //游戏图片的url

    public Game() {
        super();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
