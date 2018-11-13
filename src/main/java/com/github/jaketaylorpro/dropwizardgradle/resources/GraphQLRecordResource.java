package com.github.jaketaylorpro.dropwizardgradle.resources;

import com.codahale.metrics.annotation.Timed;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.github.jaketaylorpro.dropwizardgradle.api.JsonGraphQLDocument;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/graph-ql-record")
@Produces(MediaType.APPLICATION_JSON)
public class GraphQLRecordResource {
    private final GraphQL graphQL;

    public GraphQLRecordResource(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @POST
    @Timed
    @Consumes("application/graphql")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> graphql(String body) throws Exception {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(body).build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        return executionResult.toSpecification();
    }

    @POST
    @Timed
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> json(JsonGraphQLDocument body) throws Exception {
        ExecutionInput executionInput = ExecutionInput
                .newExecutionInput()
                .query(body.getQuery())
                .variables(body.getVariables())
                .build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        return executionResult.toSpecification();
    }
}
