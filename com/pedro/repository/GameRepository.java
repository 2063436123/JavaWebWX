package com.pedro.repository;

import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;
import com.pedro.entity.User;

import java.util.List;

public interface GameRepository {
    //添加比赛
    public int addGame(Game game);

    //用户点击一场比赛，返回所有User，并判断他是不是sponsor
    public CheckResult checkGame(String openid,int gameid);

    //查找用户的所有比赛
    public List<Game> findGames(String openid);
}
