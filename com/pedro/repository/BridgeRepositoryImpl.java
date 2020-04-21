package com.pedro.repository;

import com.pedro.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BridgeRepositoryImpl implements BridgeRepository{
    @Override
    public void joinGame(String openid, int gameid) {
        Connection connection = Utils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "insert into bridge(gameid,openid,ishost) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,gameid);
            preparedStatement.setString(2,openid);
            preparedStatement.setInt(3,0);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancelGame(int gameid) {
        Connection connection = Utils.getConnection();

        try {
            String sql1 = "delete from game where gameid = ?";
            String sql2 = "delete from bridge where gameid = ?";

            //删除game表中的
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1,gameid);
            preparedStatement.executeUpdate();

            //删除bridge表中的行
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,gameid);
            preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BridgeRepository bridgeRepository = new BridgeRepositoryImpl();
        //bridgeRepository.joinGame("good",2);

        bridgeRepository.cancelGame(3);
    }
}
