package com.github.jaketaylorpro.dropwizardgradle.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import jdbi.RecordDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/record")
@Produces(MediaType.APPLICATION_JSON)
public class RecordResource {
    private final RecordDAO dao;

    public RecordResource(RecordDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("{id}")
    @Timed
    public Record getRecord(@PathParam("id") int id) {
        return dao.findById(id);
    }
}
