package lego.feeds;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/feeds")
@Produces(MediaType.APPLICATION_JSON)
public class ArticlesResource {

    public static final String APP_PATH = "/api";
//    List<Article> cached = null;

    ArticleDAO dao;

    public ArticlesResource(ArticleDAO dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    @Path("/json/{author}")
    public List<Article> getFeedJson(@PathParam("author") String author) {
        return getArticles(false, author);
    }

    @GET
    @UnitOfWork
    @Path("/html/{author}")
    @Produces(MediaType.TEXT_HTML)
    public ArticlesView getFeed(@PathParam("author") String author) {
        return new ArticlesView(getArticles(false, author));
    }

    @GET
    @UnitOfWork
    @Path("/html3")
    @Produces(MediaType.TEXT_HTML)
    public Articles3View getFeed() {

        return new Articles3View(
                loadCache( new Predicate<Article>(){
                    public boolean apply(Article input) {
                        return input.getLink().contains("ribbonfarm");
                    }
                })
        );
    }

    @GET
    @UnitOfWork
    @Path("/pull")
    @Produces(MediaType.TEXT_HTML)
    public Response pull() {

        FeedPuller puller = new FeedPuller();
        List<Article> articles = puller.pullAll();
        for( Article  a : articles ) {
            dao.save(a);
        }

        return Response.ok(" OK ").build();
    }

    @GET
    @UnitOfWork
    @Path("/pull/{index}")
    @Produces(MediaType.TEXT_HTML)
    public Response pullSingle(@PathParam("index") int index) {

        FeedPuller puller = new FeedPuller();
        List<Article> articles = puller.pull(index);
        for( int i = 0 ; i < articles.size() ; i++ ) {
            Article article = articles.get(i);
            String html = article.getContents();
            String sanitized = FeedIHTMLParser.reStyle(html);
            article.setContents(sanitized);
            dao.save(article);
        }
            return Response.ok(" OK ").build();
    }

    @GET
    @UnitOfWork
    @Path("/strip/{link}")
    @Produces(MediaType.TEXT_HTML)
    public Response sanitizeImgs(@PathParam("link") String link) {

        List<Article> articles = dao.getArticlesByLink(link);
        for( int i = 0 ; i < articles.size() ; i++ ) {
            Article article = articles.get(i);
            String html = article.getContents();
            String sanitized = FeedIHTMLParser.reStyle(html);
            article.setContents( sanitized);
            dao.save(article);
            System.out.println( "sanitized : " + article.getTitle());
        }
        return Response.ok(" OK ").build();
    }

    @GET
    @UnitOfWork
    @Path("/strip_all")
    @Produces(MediaType.TEXT_HTML)
    public Response sanitizeImgsAll() {

        List<Article> articles = dao.getArticles();
        for( int i = 0 ; i < articles.size() ; i++ ) {
            Article article = articles.get(i);
            String html = article.getContents();
            String sanitized = FeedIHTMLParser.reStyle(html);
            article.setContents( sanitized);
            dao.save(article);
            System.out.println( "sanitized : " + article.getTitle());
        }
        return Response.ok(" OK ").build();
    }

    private List<Article> getArticles(boolean fromCacheFirst, String author) {
//        if (fromCacheFirst) {
//            if (cached != null) {
//                return cached;
//            }
//        }
        return  loadCache(author);
    }

    private List<Article> loadCache(String author) {
        if( author.equals("")) {
            return dao.getArticles();
        }
        else {
            return dao.getArticlesByAuthor(author);
        }
    }

    private List<Article> loadCache( Predicate<Article> filter) {

        List<Article> list = dao.getArticles();
       return  new ArrayList<Article>(Collections2.filter(list,filter) );

    }
}
