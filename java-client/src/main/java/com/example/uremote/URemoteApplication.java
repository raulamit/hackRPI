package com.example.uremote;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.example.uremote.core.User;
import com.example.uremote.filter.DateRequiredFeature;
import com.example.uremote.health.TemplateHealthCheck;
import com.example.uremote.resources.ControlSchemaResource;
import com.example.uremote.resources.EventResource;
import com.example.uremote.tasks.EchoTask;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.Map;

public class URemoteApplication extends Application<URemoteConfiguration> {
    public static void main(String[] args) throws Exception {
        new URemoteApplication().run(args);
    }

    private final HibernateBundle<URemoteConfiguration> hibernateBundle =
        new HibernateBundle<URemoteConfiguration>(com.example.uremote.core.Person.class) {
            @Override
            public DataSourceFactory getDataSourceFactory(URemoteConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<URemoteConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addCommand(new com.example.uremote.cli.RenderCommand());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<URemoteConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(URemoteConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<URemoteConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(URemoteConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(URemoteConfiguration configuration, Environment environment) {
        final com.example.uremote.core.Template template = configuration.buildTemplate();
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.admin().addTask(new EchoTask());
        environment.jersey().register(DateRequiredFeature.class);
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new com.example.uremote.auth.ExampleAuthenticator())
                .setAuthorizer(new com.example.uremote.auth.ExampleAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
//        configuration.
        Cluster cluster = CouchbaseCluster.create(configuration.getCouchHost());
        Bucket bucket = cluster.openBucket(configuration.getCouchBucket(), "");
        environment.jersey().register(new EventResource());
        environment.jersey().register(new ControlSchemaResource(bucket));
    }
}
