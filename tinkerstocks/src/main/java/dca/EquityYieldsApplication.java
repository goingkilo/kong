package dca;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EquityYieldsApplication extends Application<StockConfiguration> {

    @Override
    public void initialize(Bootstrap<StockConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/static", "index.html"));
    }

    @Override
    public void run(StockConfiguration config, Environment environment) throws ClassNotFoundException {

        StockDataHandler stockDataHandler = new StockDataHandler();
        environment.jersey().register(new EquityResource(stockDataHandler));
    }

    public static void main(String[] args) throws Exception {
        new EquityYieldsApplication().run(args);
    }
}
