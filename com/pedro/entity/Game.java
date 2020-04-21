package com.pedro.entity;

import java.util.Date;

public class Game {
    private int gameid;
    private String sponserid;
    private String title;
    private Date date;
    private String time;
    private String place;
    private String type;

    public Game() {
    }

    public Game(int gameid, String sponser, String title, Date date, String time, String place, String type) {
        this.gameid = gameid;
        this.sponserid = sponser;
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
        this.type = type;
    }

    public Game(String sponserid, String title, String date, String time, String place, String type) {
        this.sponserid = sponserid;
        this.title = title;
        this.date = java.sql.Date.valueOf(date);
        this.time = time;
        this.place = place;
        this.type = type;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getSponserid() {
        return sponserid;
    }

    public void setSponserid(String sponserid) {
        this.sponserid = sponserid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\"content\":\""+title+"\","+"\"date\":\""+date+"\","+"\"time\":\""+time+"\","+"\"site\":\""+place+"\","+"\"name\":\""+type+"\"";
                //"content":"title","date":"date",
    }

    public static void main(String[] args) {
        Game game = new Game("test","NBA","2020-1-1","10:00","二号","橄榄球");
        System.out.println(game.toString());
    }
}