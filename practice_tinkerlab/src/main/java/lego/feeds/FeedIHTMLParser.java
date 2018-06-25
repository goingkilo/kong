package lego.feeds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by kraghunathan on 8/9/16.
 */
public class FeedIHTMLParser {

    static final String resetCSS = "/* http://meyerweb.com/eric/tools/css/reset/ \n" +
            "   v2.0 | 20110126\n" +
            "   License: none (public domain)\n" +
            "*/\n" +
            "\n" +
            "html, body, div, span, applet, object, iframe,\n" +
            "h1, h2, h3, h4, h5, h6, p, blockquote, pre,\n" +
            "a, abbr, acronym, address, big, cite, code,\n" +
            "del, dfn, em, img, ins, kbd, q, s, samp,\n" +
            "small, strike, strong, sub, sup, tt, var,\n" +
            "b, u, i, center,\n" +
            "dl, dt, dd, ol, ul, li,\n" +
            "fieldset, form, label, legend,\n" +
            "table, caption, tbody, tfoot, thead, tr, th, td,\n" +
            "article, aside, canvas, details, embed, \n" +
            "figure, figcaption, footer, header, hgroup, \n" +
            "menu, nav, output, ruby, section, summary,\n" +
            "time, mark, audio, video {\n" +
            "\tmargin: 0;\n" +
            "\tpadding: 0;\n" +
            "\tborder: 0;\n" +
            "\tfont-size: 100%;\n" +
            "\tfont: inherit;\n" +
            "\tvertical-align: baseline;\n" +
            "}\n" +
            "/* HTML5 display-role reset for older browsers */\n" +
            "article, aside, details, figcaption, figure, \n" +
            "footer, header, hgroup, menu, nav, section {\n" +
            "\tdisplay: block;\n" +
            "}\n" +
            "body {\n" +
            "\tline-height: 1;\n" +
            "}\n" +
            "ol, ul {\n" +
            "\tlist-style: none;\n" +
            "}\n" +
            "blockquote, q {\n" +
            "\tquotes: none;\n" +
            "}\n" +
            "blockquote:before, blockquote:after,\n" +
            "q:before, q:after {\n" +
            "\tcontent: '';\n" +
            "\tcontent: none;\n" +
            "}\n" +
            "table {\n" +
            "\tborder-collapse: collapse;\n" +
            "\tborder-spacing: 0;\n" +
            "}";

    public static void main(String[] args) throws IOException {
        File input = new File("/Users/kraghunathan/taxonomy/workspace/tinkerlab/src/main/resources/assets/sample.html");
        String doc  = reStyleV2(input.toString());
    }


    public static String reStyle(String html) {
        return reStyleV2(html);
    }

    public static String reStyleV2(String html) {
        try {
            Document doc = Jsoup.parse(html);
            doc.attr("css", resetCSS);
            return doc.toString();
        }
        catch(Exception ioe) {
            return null;
        }
    }

    public static String reStyleV1( String html) {
        try {
            Document doc = Jsoup.parse(html);

            Elements imgs = doc.select("img");
            for (int i = 0; i < imgs.size(); i++) {
                Element img = imgs.get(i);
                Attributes attrs = img.attributes();
                for (Attribute a : attrs) {
                    if (a.getKey().equals("src")) continue;
                    img.removeAttr(a.getKey());
                }
                img.attr("class","img-responsive");
            }
            Elements divs = doc.select("div");
            for (int i = 0; i < divs.size(); i++) {
                Element div = divs.get(i);
                Attributes attrs = div.attributes();
                for (Attribute a : attrs) {
                    if (a.getKey().equals("style") || a.getKey().equals("class")) {
                        div.removeAttr(a.getKey());
                    }
                }
            }

            Elements p_s = doc.select("p");
            for (int i = 0; i < p_s.size(); i++) {
                Element p_ = p_s.get(i);
                Attributes attrs = p_.attributes();
                for (Attribute a : attrs) {
                    p_.removeAttr(a.getKey());
                }
                p_.attr("class","img-responsive");
            }

            Elements blockquotes = doc.select("blockquote");
            for (int i = 0; i < blockquotes.size(); i++) {
                Element bq = blockquotes.get(i);
                Attributes attrs = bq.attributes();

                bq.attr("width","");
                bq.attr("style","");
                bq.attr("class","img-responsive");
            }

            return doc.toString();
        }
        catch(Exception ioe) {
            return null;
        }

    }

    static void ox (String s) {System.out.println(s);}


}
