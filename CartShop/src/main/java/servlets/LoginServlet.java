/*
 * Developed by Razil Minneakhmetov on 10/27/18 11:38 PM.
 * Last modified 10/27/18 11:38 PM.
 * Copyright © 2018. All rights reserved.
 */

package servlets;

import app.Constants;
import com.vk.api.sdk.actions.Account;
import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.account.Info;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import com.vk.api.sdk.queries.account.AccountGetInfoQuery;
import com.vk.api.sdk.queries.users.UserField;
import com.vk.api.sdk.queries.wall.WallGetFilter;
import forms.LoginForm;
import lombok.SneakyThrows;
import models.User;
import repositories.UserRepository;
import services.LoginService;
import services.VKAuthService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        ServletContext context = getServletContext();
        VKAuthService service = new VKAuthService();
        UserXtrCounters VKUser = service.auth(code);

        LoginForm loginForm = LoginForm.builder()
                .vkId(Long.valueOf(VKUser.getId()))
                .name(VKUser.getFirstName())
                .photoURL(VKUser.getPhoto50())
                .build();
        LoginService loginService =
                new LoginService((UserRepository) context.getAttribute("userRepository"));

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