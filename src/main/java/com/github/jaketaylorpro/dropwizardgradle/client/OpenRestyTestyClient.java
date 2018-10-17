package com.github.jaketaylorpro.dropwizardgradle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import javax.ws.rs.HttpMethod;
import java.io.IOException;

public class OpenRestyTestyClient {
    private final HttpClient httpClient;
    private final ObjectMapper recordMapper;
    public OpenRestyTestyClient(HttpClient httpClient, ObjectMapper recordMapper) {
        this.httpClient=httpClient;
        this.recordMapper=recordMapper;
    }

    public Record[] findById(int id) throws IOException {
        HttpResponse response = httpClient.execute(
                new HttpHost("localhost",80,"http"),
                new HttpGet("/record/"+id));
        return recordMapper.readValue(response.getEntity().getContent(), Record[].class);
    }
}
