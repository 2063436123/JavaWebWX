package com.pedro.entity;

import java.util.List;

public class CheckResult {
    private List<User> users;
    private boolean ishost;
    private boolean isplayer;

    public CheckResult(List<User> users, boolean ishost, boolean isplayer) {
        this.users = users;
        this.ishost = ishost;
        this.isplayer = isplayer;
    }

    public CheckResult() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isIshost() {
        return ishost;
    }

    public void setIshost(boolean ishost) {
        this.ishost = ishost;
    }

    public boolean isIsplayer() {
        return isplayer;
    }

    public void setIsplayer(boolean isplayer) {
        this.isplayer = isplayer;
    }

    @Override
    public String toString() {
        return "CheckResult{" +
                "users=" + users +
                ", ishost=" + ishost +
                ", isplayer=" + isplayer +
                '}';
    }
}
