/*
 * Developed by Razil Minneakhmetov on 10/29/18 2:43 AM.
 * Last modified 10/29/18 2:43 AM.
 * Copyright © 2018. All rights reserved.
 */

package servlets;

import context.MyApplicationContext;
import models.Cart;
import services.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyApplicationContext context = MyApplicationContext.getMyContext();
        Long productId = Long.valueOf(request.getParameter("productId"));
        Long userId = Long.valueOf(request.getParameter("userId"));
        Cart cart = Cart.builder()
                .productId(productId)
                .userId(userId)
                .build();
        CartService cartService = (CartService) MyApplicationContext.getMyContext().getAttribute("CartService");
        cartService.addToCart(cart);
        response.sendError(200);
    }
}