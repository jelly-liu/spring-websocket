package com.jelly.websocket.web;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jelly on 2017/3/16.
 */
public class BaseAction {
    protected <T> void responseJson(T t, HttpServletResponse response) throws IOException {
        response.getWriter().write(new Gson().toJson(t));
    }
}
