package com.gojek.drivers;

import com.bendb.dropwizard.redis.JedisBundle;
import com.bendb.dropwizard.redis.JedisFactory;
import com.gojek.drivers.resources.DriversResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class whereIsMyDriver4Application extends Application<whereIsMyDriver4Configuration> {

    public static void main(final String[] args) throws Exception {
        new whereIsMyDriver4Application().run(args);
    }

    @Override
    public String getName() {
        return "whereIsMyDriver4";
    }

    @Override
    public void initialize(final Bootstrap<whereIsMyDriver4Configuration> bootstrap) {
        bootstrap.addBundle(new JedisBundle<whereIsMyDriver4Configuration>() {
            @Override
            public JedisFactory getJedisFactory(whereIsMyDriver4Configuration configuration) {
                return configuration.getJedisFactory();
            }
        });
    }

    @Override
    public void run(final whereIsMyDriver4Configuration configuration,
                    final Environment environment) {
        final DriversResource resource = new DriversResource();
        environment.jersey().register(resource);
    }

}
