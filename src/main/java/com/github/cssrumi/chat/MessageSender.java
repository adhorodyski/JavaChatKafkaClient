package com.github.cssrumi.chat;

import com.github.cssrumi.JavaClient;
import com.github.cssrumi.request.Post;
import org.json.JSONObject;

public class MessageSender {

    private Post messagePost;
    private String messageRequest;

    public MessageSender() {
        messageRequest = "http://" + JavaClient.getServerIP() +":5000/api/message/producer";
        messagePost = new Post(messageRequest);

    }

    public void send(JSONObject message){
        messagePost.sendJson(message);
    }
}
