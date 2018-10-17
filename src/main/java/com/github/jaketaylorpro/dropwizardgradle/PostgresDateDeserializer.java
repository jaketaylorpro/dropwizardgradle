package com.github.jaketaylorpro.dropwizardgradle;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PostgresDateDeserializer extends StdDeserializer<Date> {
    public PostgresDateDeserializer() {
        this(null);
    }
    public PostgresDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        try {
            return new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(p.getText()).toInstant().toEpochMilli());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
