import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.Map;

public class TinkerApplication extends Application<TinkerConfiguration> {

      @Override
    public void initialize(Bootstrap<TinkerConfiguration> bootstrap) {

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
        environment.jersey().register(new BasicResource());
    }

    public static void main(String[] args) throws Exception {
        new TinkerApplication().run(args);
    }
}
