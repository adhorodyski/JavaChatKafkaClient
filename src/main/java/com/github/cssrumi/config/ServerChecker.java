package com.github.cssrumi.config;

import com.github.cssrumi.request.Get;

public class ServerChecker {
    private Get server;
    private int httpStatus;
    private boolean isValid;

    public ServerChecker() {
        server = new Get();
    }

    public void validate(String ip) {
        String request = "http://" + ip + ":5000/api/check_server";
        server.setRequest(request);
        server.send();
        httpStatus = server.getHttpResult();
        if(httpStatus == 200)
            isValid = true;
        else
            isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }
}
