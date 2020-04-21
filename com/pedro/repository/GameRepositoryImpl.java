package com.pedro.repository;

import com.pedro.entity.CheckResult;
import com.pedro.entity.Game;
import com.pedro.entity.User;
import com.pedro.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    @Override
    public int addGame(Game game) {
        int ret = 0;

        try {
            Connection connection = Utils.getConnection();

            //添加比赛
            String sql = "insert into game(sponsorid,title,date,time,place,type) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, game.getSponserid());
            preparedStatement.setString(2, game.getTitle());
            preparedStatement.setDate(3, (Date) game.getDate());
            preparedStatement.setString(4, game.getTime());
            preparedStatement.setString(5, game.getPlace());
            preparedStatement.setString(6, game.getType());

            preparedStatement.executeUpdate();

            //添加比赛与sponsor的联系
            //找到gameid
            int gameid = 0;
            String findGameId = "select gameid from game where title = ?";
            preparedStatement = connection.prepareStatement(findGameId);
            preparedStatement.setString(1, game.getTitle());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                gameid = resultSet.getInt(1);
            }

            //插入行
            String sql1 = "insert into bridge(gameid,openid,ishost) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql1);

            preparedStatement.setInt(1, gameid);
            preparedStatement.setString(2, game.getSponserid());
            preparedStatement.setInt(3, 1);

            preparedStatement.executeUpdate();

            ret = gameid;

            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    //通过openid和gameid返回比赛Users和是否是sponsor
    @Override
    public CheckResult checkGame(String openid, int gameid) {
        CheckResult checkResult = new CheckResult();
        try {
            //创建结果和连接
            Connection connection = Utils.getConnection();

            //查询用户
            ArrayList<User> users = new ArrayList<>();
            String sqlForUsers = "select u.openid,u.name,u.img from user u, bridge b where b.gameid = ? and b.openid = u.openid";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlForUsers);
            preparedStatement.setInt(1, gameid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String userid = resultSet.getString(1);
                String username = resultSet.getString(2);
                String img = resultSet.getString(3);

                User user = new User(userid, username, img);
                users.add(user);
            }

            boolean isPlayer = false;
            //检查是否参与比赛
            String sqlIfJoin = "select * from bridge where gameid = ? and openid = ?";
            preparedStatement = connection.prepareStatement(sqlIfJoin);
            preparedStatement.setInt(1, gameid);
            preparedStatement.setString(2, openid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("参与了比赛");
                isPlayer = true;
            }


            boolean isSponsor = false;
            if (isPlayer) {
                //检查是否是sponsor
                isSponsor = false;
                String sqlForSponsor = "select ishost from bridge where gameid = ? and openid = ?";
                preparedStatement = connection.prepareStatement(sqlForSponsor);
                preparedStatement.setInt(1, gameid);
                preparedStatement.setString(2, openid);

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int ret = resultSet.getInt(1);
                    if (ret == 0) {
                        isSponsor = false;
                    } else {
                        isSponsor = true;
                    }
                }
            }

            checkResult.setIshost(isSponsor);
            checkResult.setUsers(users);
            checkResult.setIsplayer(isPlayer);

            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return checkResult;
    }

    @Override
    public List<Game> findGames(String openid) {
        List<Game> games = new ArrayList<>();
        //创建连接
        Connection connection = Utils.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            String findSql = "select g.gameid,g.sponsorid,g.title,g.date,g.time,g.place,g.type from game g,bridge b where b.openid = ? and b.gameid = g.gameid";
            preparedStatement = connection.prepareStatement(findSql);
            preparedStatement.setString(1, openid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int gamdid = resultSet.getInt(1);
                String sponsorid = resultSet.getString(2);
                String title = resultSet.getString(3);
                java.util.Date date = resultSet.getDate(4);
                String time = resultSet.getString(5);
                String place = resultSet.getString(6);
                String type = resultSet.getString(7);
                games.add(new Game(gamdid, sponsorid, title, date, time, place, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return games;
    }

    public static void main(String[] args) {
        GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        /*Date date = Date.valueOf("2020-1-1");

        Game game = new Game(2,"test","NBA",date,"10:00","二号","橄榄球");
        gameRepository.addGame(game);*/

        //System.out.println(gameRepository.findGames("test"));

        /*CheckResult checkResult = gameRepository.checkGame("good", 1);
        System.out.println(checkResult);*/

/*        List<Game> good = gameRepository.findGames("good");
        System.out.println(Utils.listToStringForGame(good));

        CheckResult checkResult = gameRepository.checkGame("good", 1);
        System.out.println(Utils.listToStringForUser(checkResult.getUsers()));*/

        Game game = new Game("test", "NBA", "2015-1-1", "10:00", "二号", "保龄球");
        System.out.println(gameRepository.addGame(game));
    }
}
