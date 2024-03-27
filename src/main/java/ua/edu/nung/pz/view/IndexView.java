package ua.edu.nung.pz.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexView {
    private String path;
    private static IndexView indexView = new IndexView();
    private IndexView() {}

    public static IndexView getInstance() {
        return indexView;
    }
    public String getPage(String title, String body) {
        return getHtml("emptyPage")
                .replace("<!--####title###-->", title)
                .replace("<!--####body###-->", body);
    }

    public String getBody(String header, String footer, String context) {
        return header +
                "<div class=\"container\">" +
                context +
                "</div>" +
                footer;
    }

    public String getHeader(String userName) {
        String html = getHtml("headerPartial");
        if (userName.length() > 0) {
            html = conditionalTextDelete(html, "usernameNotLogin")
                    .replace("<!--###username###-->", userName);
        } else {
            html = conditionalTextDelete(html, "usernameLoginedIn");
        }
        return html;
    }

    public String getFooter(String footer) {
        return getHtml("footerPartial");
    }

    public String getLoginForm() {
        return getHtml("loginFormPartial");
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String getHtml(String filename) {
        StringBuilder strb = new StringBuilder("\n");
        Path file = Paths.get(path + filename + ".html");
        Charset charset = StandardCharsets.UTF_8;

        try (BufferedReader reader = Files.newBufferedReader(file, charset))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                strb.append(line).append("\n");
            }
        }
          catch (IOException e) {
            throw new RuntimeException(e);
        }

        return strb.toString();
    }

    private String conditionalTextDelete(String html, String markToDelete) {
        String startMarker = "<!--Variable ###" + markToDelete + "###-->";
        String endMarker = "<!--endVariable-->";
        int startIndex = html.indexOf(startMarker);
        if (startIndex == -1) {
            return html;
        }
        int endIndex = html.indexOf(endMarker, startIndex);
        if (endIndex == -1) {
            return html;
        }
        String firstPart = html.substring(0, startIndex);
        String endPart = html.substring(endIndex + endMarker.length());
        return firstPart + endPart;
    }
}
