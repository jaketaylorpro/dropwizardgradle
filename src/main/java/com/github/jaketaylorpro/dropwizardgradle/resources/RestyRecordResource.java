package com.github.jaketaylorpro.dropwizardgradle.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jaketaylorpro.dropwizardgradle.exception.MyException;
import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import com.github.jaketaylorpro.dropwizardgradle.client.OpenRestyTestyClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/resty-record")
@Produces(MediaType.APPLICATION_JSON)
public class RestyRecordResource {
    private final OpenRestyTestyClient client;

    public RestyRecordResource(OpenRestyTestyClient client) {
        this.client = client;
    }

    @GET
    @Path("{id}")
    @Timed
    public Record[] getRecord(@PathParam("id") int id) throws MyException {
        try{
            return client.findById(id);
        }
        catch (HttpResponseException e){
            throw new MyException(e.getStatusCode(),e.getMessage());
        }
        catch (ClientProtocolException e){
            throw new MyException(500, "Unknown Http Exception");
        }
        catch (JsonProcessingException e) {
            throw new MyException(500, "Unknown Mapping Exception");
        }
        catch (IOException e) {
            throw new MyException(500, "Unknown Exception");
        }
    }
}
