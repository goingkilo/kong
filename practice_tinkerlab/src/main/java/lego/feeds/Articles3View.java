package lego.feeds;

import io.dropwizard.views.View;

import java.util.List;

/**
 * Created by kraghunathan on 7/19/16.
 */
public class Articles3View extends View {

    private final List<Article> articles;

    public Articles3View(List<Article> articles) {
        super("articles3.ftl");

        this.articles = articles;

        int len = articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }
}