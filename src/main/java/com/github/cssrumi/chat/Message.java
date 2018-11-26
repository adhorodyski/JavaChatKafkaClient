package com.github.cssrumi.chat;

import org.apache.kafka.common.protocol.types.Field;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.SimpleFormatter;

public class Message {
    private static JSONObject jsonObject;
    private static String result;

    public static JSONObject createJsonMessage (String username, String message, String token) {
        jsonObject = new JSONObject();
        jsonObject.put("user", username);
        jsonObject.put("message", message);
        jsonObject.put("token", token);
        return jsonObject;
    }

    public static String MessageParser(String message) {
        result = "";
        try {
            jsonObject = new JSONObject(message);
            result =
                    jsonObject.getString("user")
                    + "("
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                    + "): "
                    + jsonObject.getString("message")
                    + "\n";

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
