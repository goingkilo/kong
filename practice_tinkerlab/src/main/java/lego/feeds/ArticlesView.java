package lego.feeds;

import io.dropwizard.views.View;

import java.util.List;

/**
 * Created by kraghunathan on 7/19/16.
 */
public class ArticlesView extends View {

    private final List<Article> articles;

    public ArticlesView(List<Article> articles) {
        super("articles.ftl");
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }
}