package com.github.cssrumi.request;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get {

    private String request;
    private JSONObject jsonObject;
    private String result;
    private StringBuilder stringBuilder;
    private int httpResult;


    public Get() {
        request = "";
        stringBuilder = new StringBuilder();
    }

    public Get(String request){
        this.request = request;
        stringBuilder = new StringBuilder();
    }

    public void send() {
        stringBuilder.setLength(0);
        try {
            URL url = new URL(request);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            httpResult = con.getResponseCode();
            if (httpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                br.close();
                result = stringBuilder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHttpResult() {
        return httpResult;
    }

    public String getResult() {
        return result;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
