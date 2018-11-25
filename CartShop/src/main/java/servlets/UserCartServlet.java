/*
 * Developed by Razil Minneakhmetov on 11/8/18 6:56 PM.
 * Last modified 11/8/18 6:56 PM.
 * Copyright © 2018. All rights reserved.
 */

package servlets;

import com.google.gson.Gson;
import context.ApplicationContext;
import models.Product;
import repositories.CartRepository;
import services.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/usercart")
public class UserCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter("userId"));
        CartService cartService = (CartService) ApplicationContext.getContext().getAttribute("cartService");


        List<Product> products = cartService.getProductsInCart(userId);
        String result = new Gson().toJson(products);
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(result);
    }
}