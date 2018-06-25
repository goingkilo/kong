import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.Map;

public class TinkerApplication extends Application<TinkerConfiguration> {

    private final HibernateBundle<TinkerConfiguration> hibernate = new HibernateBundle<TinkerConfiguration>(Item.class) {
        //@Override
        public DataSourceFactory getDataSourceFactory(TinkerConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<TinkerConfiguration> bootstrap) {

        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ViewBundle<TinkerConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(TinkerConfiguration config) {
                return config.getViewRendererConfiguration();

            }
        });
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void run(TinkerConfiguration config, Environment environment) throws ClassNotFoundException {

        final ItemDAO itemDao = new ItemDAO(hibernate.getSessionFactory());

        environment.jersey().register(new ItemResource(itemDao));

        final AHealthCheck basicHealthCheck = new AHealthCheck();
        environment.healthChecks().register( "basic", basicHealthCheck );
    }

    public static void main(String[] args) throws Exception {
        new TinkerApplication().run(args);
    }
}
