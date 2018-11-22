package com.github.cssrumi.chat;

import org.json.JSONObject;

public class Message {
    private static JSONObject jsonObject;

    public static JSONObject createJsonMessage (String username, String message, String token) {
        jsonObject = new JSONObject();
        jsonObject.put("user", username);
        jsonObject.put("message", message);
        jsonObject.put("token", token);
        return jsonObject;
    }
}
