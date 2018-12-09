/*
 * Developed by Razil Minneakhmetov on 10/27/18 11:38 PM.
 * Last modified 10/27/18 11:38 PM.
 * Copyright © 2018. All rights reserved.
 */

package servlets;

import com.vk.api.sdk.objects.users.UserXtrCounters;
import context.MyApplicationContext;
import forms.LoginForm;
import lombok.SneakyThrows;
import models.User;
import services.LoginService;
import services.VKAuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        //ServletContext context = getServletContext();
        VKAuthService vkAuthService = (VKAuthService) MyApplicationContext.getMyContext().getAttribute("VKAuthService");
        UserXtrCounters VKUser = vkAuthService.auth(code);

        LoginForm loginForm = LoginForm.builder()
                .vkId(Long.valueOf(VKUser.getId()))
                .name(VKUser.getFirstName())
                .photoURL(VKUser.getPhoto50())
                .build();
        LoginService loginService = (LoginService) MyApplicationContext.getMyContext().getAttribute("LoginService");

        User user = loginService.login(loginForm);
        Cookie vkId = new Cookie("vk_id", user.getVkId().toString());
        vkId.setMaxAge(60*60*24);
        Cookie userName = new Cookie("userName", user.getName());
        userName.setMaxAge(60*60*24);
        Cookie userPhoto = new Cookie("userPhoto", user.getPhotoURL());
        userName.setMaxAge(60*60*24);
        response.addCookie(vkId);
        response.addCookie(userPhoto);
        response.addCookie(userName);
        response.sendRedirect("/main");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPut(req, resp);
    }
}