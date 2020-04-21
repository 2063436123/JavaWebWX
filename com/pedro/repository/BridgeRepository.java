package com.pedro.repository;

public interface BridgeRepository {
    //非举办者加入比赛
    public void joinGame(String openid,int gameid);


    //删除比赛
    public void cancelGame(int gameid);
}
