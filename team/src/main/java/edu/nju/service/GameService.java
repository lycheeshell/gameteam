package edu.nju.service;

import edu.nju.util.ResultData;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 16:12 2020/1/8
 */
public interface GameService {

    ResultData createGame(String name, String description);

    ResultData deleteGame(String gameId);

    ResultData updateGame(String gameId, String name, String description);

    ResultData updateGameImage(String gameId, MultipartFile image);

}
