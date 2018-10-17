package com.github.jaketaylorpro.dropwizardgradle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jaketaylorpro.dropwizardgradle.client.OpenRestyTestyClient;
import com.github.jaketaylorpro.dropwizardgradle.exception.MyExceptionMapper;
import com.github.jaketaylorpro.dropwizardgradle.resources.HelloResource;
import com.github.jaketaylorpro.dropwizardgradle.health.TemplateHealthCheck;
import com.github.jaketaylorpro.dropwizardgradle.resources.RecordResource;
import com.github.jaketaylorpro.dropwizardgradle.resources.RestyRecordResource;
import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jdbi.RecordDAO;
import org.apache.http.client.HttpClient;
import org.skife.jdbi.v2.DBI;

public class App extends Application<Config> {
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        // nothing to do yet
        bootstrap.addBundle(new MigrationsBundle<Config>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(Config configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(Config config,
                    Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        final RecordDAO dao = jdbi.onDemand(RecordDAO.class);
        final HelloResource res = new HelloResource(
                config.getTemplate(),
                config.getDefaultName());
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(config.getTemplate());
        final HttpClient httpClient = new HttpClientBuilder(environment).using(config.getHttpClientConfiguration())
                .build(getName());
        final OpenRestyTestyClient ortClient = new OpenRestyTestyClient(httpClient,new ObjectMapper());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(res);
        environment.jersey().register(new RecordResource(dao));
        environment.jersey().register(new RestyRecordResource(ortClient));
        environment.jersey().register(ortClient);
        environment.jersey().register(new MyExceptionMapper());

    }
}
