package com.github.jaketaylorpro.dropwizardgradle.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class OpenRestyTestyClient {
    private final HttpClient httpClient;
    private final ObjectMapper recordMapper;

    public OpenRestyTestyClient(HttpClient httpClient, ObjectMapper recordMapper) {
        this.httpClient = httpClient;
        this.recordMapper = recordMapper;
    }

    public Record[] findById(int id) throws IOException, ExecutionException, InterruptedException {
        OpenRestyTestyCommand command = new OpenRestyTestyCommand(id);
        return command.queue().get();
    }

    private class OpenRestyTestyCommand extends HystrixCommand<Record[]> {

        private final int id;

        protected OpenRestyTestyCommand(int id) {
            super(HystrixCommandGroupKey.Factory.asKey("OpenRestyTesty"));
            this.id = id;
        }

        @Override
        protected Record[] run() throws IOException {
            HttpResponse response = httpClient.execute(
                    new HttpHost("localhost", 80, "http"),
                    new HttpGet("/record/" + id));
            return recordMapper.readValue(response.getEntity().getContent(), Record[].class);
        }

        @Override
        protected String getCacheKey() {
            return String.valueOf(id);
        }
    }
}
