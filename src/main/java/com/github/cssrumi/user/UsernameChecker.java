package com.github.cssrumi.user;

import com.github.cssrumi.JavaClient;
import com.github.cssrumi.request.Post;
import org.json.JSONObject;


public class UsernameChecker {
    private String token;
    private String setUserRequest;
    private Post setUserPost;
    private JSONObject resultJson;

    public UsernameChecker(){
        String ip = JavaClient.getServerIP();
        setUserRequest = "http://" + ip +":5000/api/user/set_user";
        setUserPost = new Post(setUserRequest);
    }

    public String ifValidGetToken(String username){
        String usernameJson = "{\"user\":\"" + username + "\"}";
        setUserPost.sendString(usernameJson);
        String result = setUserPost.getResult();
        setToken(result);
        return token;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String result){
        if(!result.equals("") && result != null){
            resultJson = new JSONObject(result);
            token = resultJson.getString("token");
        }
        else {
            token = "";
        }
    }

}
