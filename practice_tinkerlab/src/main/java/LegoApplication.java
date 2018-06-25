import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import lego.feeds.Article;
import lego.feeds.ArticleDAO;
import lego.feeds.ArticlesResource;
import lego.filter.LoginFilter;
import lego.stocks.StocksResource;
import lego.todo.Item;
import lego.todo.ItemDAO;
import lego.todo.ItemResource;
import lego.filter.AuthenticatoFilter;
import lego.user.ExampleAuthenticator;
import lego.user.ExampleAuthorizer;
import lego.user.User;
import lego.user.UserDAO;
import lego.user.UserResource;
import misc.AHealthCheck;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import java.util.Map;

public class LegoApplication extends Application<LegoConfiguration> {

    private final HibernateBundle<LegoConfiguration> hibernate = new HibernateBundle<LegoConfiguration>(Item.class,Article.class,User.class) {
        //@Override
        public DataSourceFactory getDataSourceFactory(LegoConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<LegoConfiguration> bootstrap) {

        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ViewBundle<LegoConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(LegoConfiguration config) {
                return config.getViewRendererConfiguration();

            }
        });
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void run(LegoConfiguration config, Environment environment) throws ClassNotFoundException {

        final ItemDAO itemDao = new ItemDAO(hibernate.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        final ArticleDAO articleDao = new ArticleDAO(hibernate.getSessionFactory());

        environment.jersey().register(new ItemResource(itemDao));
        environment.jersey().register(new UserResource(userDAO));
        environment.jersey().register(new StocksResource());
        environment.jersey().register(new ArticlesResource(articleDao));

        environment.jersey().register(AuthenticatoFilter.class);

        LoginFilter loginFilter = new UnitOfWorkAwareProxyFactory(hibernate)
                .create(LoginFilter.class, UserDAO.class, userDAO);
        environment.jersey().register(loginFilter);


        environment.jersey().register(RolesAllowedDynamicFeature.class);

        ExampleAuthenticator exampleAuthenticator = new UnitOfWorkAwareProxyFactory(hibernate)
                                .create(ExampleAuthenticator.class, UserDAO.class, userDAO);

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(exampleAuthenticator)
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("Monocle-O")
                .buildAuthFilter()));

        final AHealthCheck basicHealthCheck = new AHealthCheck();
        environment.healthChecks().register( "basic", basicHealthCheck );
    }

    public static void main(String[] args) throws Exception {
        new LegoApplication().run(args);
    }
}
