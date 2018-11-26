package com.github.cssrumi.request;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {

    private String request;
    private JSONObject jsonObject;
    private String result;
    private StringBuilder stringBuilder;

    public Post(String request){
        this.request = request;
        stringBuilder = new StringBuilder();
    }

    public void send(JSONObject json){
        jsonObject = json;
        stringBuilder.setLength(0);
        try {
            URL url = new URL(request);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonObject.toString());
            wr.flush();
            wr.close();
            int httpResult = con.getResponseCode();
            if (httpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                br.close();
                result = stringBuilder.toString();
            } else {
                System.out.println(con.getResponseMessage());
                result = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAsJson(String string){
        jsonObject = new JSONObject(string);
        send(jsonObject);
    }

    public String getResult() {
        return result;
    }
}
