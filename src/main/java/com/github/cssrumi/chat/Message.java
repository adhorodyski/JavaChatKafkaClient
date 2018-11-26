package com.github.cssrumi.chat;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private static JSONObject jsonObject;
    private static String result;
    private static final String specialCharacters = "ąćęłńśóżź";
    private static final String replaceCharacters = "acelnsozz";
    private static final char[] specialCharactersChar = specialCharacters.toCharArray();
    private static final char[] replaceCharactersChar = replaceCharacters.toCharArray();

    public static JSONObject createJsonMessage(String username, String message, String token) {
        message = replaceSpecialCharacters(message);
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

    public static String replaceSpecialCharacters(String message) {
        result = message;
        for (int i = 0; i<specialCharactersChar.length; i++) {
            result = result.replace(
                    specialCharactersChar[i],
                    replaceCharactersChar[i]
            );
        }
        return result;
    }

}
