package com.github.jaketaylorpro.dropwizardgradle.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import jdbi.RecordDAO;

public class Query implements GraphQLQueryResolver {

    private final RecordDAO dao;

    public Query(RecordDAO dao) {
        this.dao = dao;
    }

    public Record record(int id) {
        return dao.findById(id);
    }
}
