package com.github.jaketaylorpro.dropwizardgradle.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class JsonGraphQLDocument {
    private Map<String, Object> variables;
    private String query;

    public JsonGraphQLDocument() {
        //deserializer
    }

    public JsonGraphQLDocument(String query, Map<String, Object> variables) {
        this.query = query;
        this.variables = variables;
    }

    @JsonProperty
    public Map<String, Object> getVariables() {
        return variables;
    }

    @JsonProperty
    public String getQuery() {
        return query;
    }
}
