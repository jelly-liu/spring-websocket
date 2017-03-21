package com.jelly.websocket.web;

import com.jelly.websocket.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jelly on 2017/3/16.
 */
@Controller
public class TestAction extends BaseAction {
    @RequestMapping(value = "/test")
    public void test(HttpServletRequest request, HttpServletResponse response){
        try {
            User user = new User("001", "tom", 18);
            responseJson(user, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
