package com.github.jaketaylorpro.dropwizardgradle;

import com.github.jaketaylorpro.dropwizardgradle.api.HelloResource;
import com.github.jaketaylorpro.dropwizardgradle.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    }

    @Override
    public void run(Config configuration,
                    Environment environment) {
        final HelloResource res = new HelloResource(
                configuration.getTemplate(),
                configuration.getDefaultName());
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(res);
    }
}
