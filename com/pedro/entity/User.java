package com.pedro.entity;

public class User {
    private String openid;
    private String name;
    private String img;

    public User(String openid, String name, String img) {
        this.openid = openid;
        this.name = name;
        this.img = img;
    }

    public User() {
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "\"nickname\":\""+name+"\","+"\"avatarurl\":\""+img+"\"";
        //"content":"title","date":"date",
    }

    public static void main(String[] args) {
        User test = new User("god","上帝","http://");
        System.out.println(test.toString());
    }
}


