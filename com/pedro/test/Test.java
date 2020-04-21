package com.pedro.test;

import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;
import com.pedro.entity.User;
import com.pedro.repository.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        BridgeRepository bridgeRepository = new BridgeRepositoryImpl();
        GameRepository gameRepository = new GameRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();


        // 注册用户
        User user = new User("ddd", "罗本", "http");
        User user2 = new User("aaa", "梅西", "css");
        userRepository.addUser(user);
        userRepository.addUser(user2);
        for (int i = 0; i < 10; i++) {
            // 发起比赛
            Game game = new Game("ddd", "ddd的比赛", "2020-6-6", "12:00", "一号场地", "蹴鞠");
            gameRepository.addGame(game);

            // 获取相关的所有比赛
            List<Game> games = gameRepository.findGames("ddd");
            System.out.println(games);

            // 加入比赛
            bridgeRepository.joinGame("aaa", games.get(0).getGameid());

            // 点击比赛,查看比赛信息
            CheckResult checkResult = gameRepository.checkGame("ddd", games.get(0).getGameid());
            System.out.println(checkResult.getUsers());
            System.out.println(checkResult.isIshost());

            // 取消比赛
            bridgeRepository.cancelGame(games.get(0).getGameid());
        }
    }
}
