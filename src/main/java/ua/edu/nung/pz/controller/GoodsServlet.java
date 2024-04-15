package ua.edu.nung.pz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.nung.pz.dao.entity.Good;
import ua.edu.nung.pz.dao.repository.GoodRepository;
import ua.edu.nung.pz.view.MainPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(name = "GoodsServlet", urlPatterns = {"/goods/*"})
public class GoodsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        GoodRepository goodRepository = new GoodRepository();
        ArrayList<Good>  goods = goodRepository.getByBrand("Naturalis");

        String body = goods.stream().map(good -> {
            return "<div class=\"col-12 col-sm-6 col-lg-4 col-xl-3 my-2\">" +
                    "<div class=\"card\" style=\"width: 18rem;\">\n" +
                    "<img src=\"/img/" + (good.getPhoto().length > 0 ? good.getPhoto()[0] : "") + "\" class=\"card-img-top\" alt=\"good image\">" +
                    "  <div class=\"card-body\">\n" +
                    "    <h5 class=\"card-title\">" + good.getName() + "</h5>\n" +
                    "    <h6 class=\"card-subtitle mb-2 text-body-secondary\">Price:" + good.getPrice().getFor_client() + " UAH</h6>\n" +
                    "    <p class=\"card-text\">" + good.getShortDescription() + "</p>\n" +
                    "    <a href=\"#\" class=\"card-link\">Card link</a>\n" +
                    "    <a href=\"#\" class=\"card-link\">Another link</a>\n" +
                    "  </div>\n" +
                    "</div>"
                    + "</div>";
        }).collect(Collectors.joining());

        body = "<div class=\"container-fluid\"> <div class=\"row\">" + body + "</div> </div>";

        String builderPage = MainPage.Builder.newInstance()
                .setTitle("Green Shop")
                .setHeader("")
                .setBody(body)
                .setFooter()
                .build()
                .getFullPage();

        out.println(builderPage);
    }
}
