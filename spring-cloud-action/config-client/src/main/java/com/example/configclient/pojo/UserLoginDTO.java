package com.example.configclient.pojo;

public class UserLoginDTO {
    private JWT jwt;
    private User user;

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "jwt=" + jwt +
                ", user=" + user +
                '}';
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
