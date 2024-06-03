package ua.edu.nung.pz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.edu.nung.pz.dao.entity.Cart;
import ua.edu.nung.pz.dao.entity.User;
import ua.edu.nung.pz.dao.repository.OrderRepository;
import ua.edu.nung.pz.view.MainPage;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart/*"})
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        User user;
        String userName = "";
        String context = "<h2>Cart!</h2>\n";

        if (session != null) {
            user = (User) session.getAttribute(User.USER_SESSION_NAME);
            userName = user == null ? "" : user.getDisplayName();

            OrderRepository orderRepository = new OrderRepository();
            Cart cart = orderRepository.getOrdersByUserId(user.getId());
            // TODO implement build card logic
            context = "Body with cards todo";
            System.out.println(cart);
        }

        String builderPage = MainPage.Builder.newInstance()
                .setTitle("Green Shop")
                .setHeader(userName)
                .setBody(context)
                .setFooter()
                .build()
                .getFullPage();

        out.println(builderPage);
    }
}
