package com.github.cssrumi.user;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class UsernameChecker {
    private JSONObject jsonObject;
    private String username;
    private String string;
    private URL url;
    private URLConnection connection;
    private OutputStreamWriter out;
    private BufferedReader in;

    public UsernameChecker(String ip){
        try {
            System.out.println("http://" + ip +"/api/message/producer");
            url = new URL("http://" + ip +"/api/message/producer");
            connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validate(String username){
        string = "{\"user\":\"" + username + "\"}";
        jsonObject = new JSONObject(string);
        try {
            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str = in.toString();
            in.close();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
