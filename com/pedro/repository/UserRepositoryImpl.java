package com.pedro.repository;

import com.pedro.entity.User;
import com.pedro.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository{

    @Override
    public void addUser(User user) {
        Connection connection = Utils.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            String sql = "insert into user(openid,name,img) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,user.getOpenid());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getImg());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{

                connection.close();
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        /*User test = new User("god","上帝","http://");
        userRepository.addUser(test);*/


    }
}
