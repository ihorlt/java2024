package ua.edu.nung.pz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.nung.pz.model.Good;
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

        // TODO remove test data
        // testing data
        String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec in porta lectus, a suscipit ex. Cras quis pretium nisl, id bibendum nisl. Vivamus id enim id lectus ullamcorper auctor at pretium velit. Quisque mattis nisi eget metus finibus, id pretium lacus maximus. Pellentesque lobortis facilisis suscipit. ";
        String photos[] = new String[3];
        ArrayList<Good> goods = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            goods.add(new Good(
                    i + 1,
                    "Good " + (i + 1),
                    lorem,
                    "Brand " + (i + 1),
                    photos,
                    (i + 4)
            ));
        }

        String body = goods.stream().map(good -> {
            return "<div class=\"col-12 col-sm-6 col-lg-4 col-xl-3\">" +
                    "<div class=\"card\" style=\"width: 18rem;\">\n" +
                    "  <div class=\"card-body\">\n" +
                    "    <h5 class=\"card-title\">" + good.getName() + "</h5>\n" +
                    "    <h6 class=\"card-subtitle mb-2 text-body-secondary\">Card subtitle</h6>\n" +
                    "    <p class=\"card-text\">" + good.getDescription() + "</p>\n" +
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
