package com.github.cssrumi.user;

public class User {
    private String username;
    private String token;
    private UsernameChecker checker;
    private String result;

    public User(){
        checker = new UsernameChecker();
        result = "";
    }

    public String checkUserAndGetToken(String username){
        result = checker.ifValidGetToken(username);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void clear() {
        setUsername("");
        setToken("");
    }
}
