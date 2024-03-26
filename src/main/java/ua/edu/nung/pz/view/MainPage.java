package ua.edu.nung.pz.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainPage {
    private String fullPage;

    public MainPage(Builder builder) {
        this.fullPage = builder.fullPage;
    }

    public String getFullPage() {
        return fullPage;
    }

    public static class Builder {
        // inner use
        private static String path;
        // inner use
        private String emptyPage;
        private String fullPage;
        private String title;
        private String header;
        private String footer;

        public void setPath(String path) {
            this.path = path;
        }
        private static String getHtml(String filename) {
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

        // for builder pattern
        private Builder() {}

        // TODO implement empty page loding
        public static Builder newInstance() {
            path = ViewConfig.getInstance().getPath();
            return new Builder();
        }
        public Builder setHeader(String header) {
            this.header = header;
            return  this;
        }

        public Builder setFooter(String footer) {
            this.footer = footer;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public MainPage build() {
            emptyPage = getHtml("emptyPage");
            this.fullPage = this.title != null ? emptyPage.replace("<!--####title###-->", title)
                    : emptyPage;
            return new MainPage(this);
        }
    }
}
