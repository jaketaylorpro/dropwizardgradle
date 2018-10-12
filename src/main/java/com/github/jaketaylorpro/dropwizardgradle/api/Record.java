package com.github.jaketaylorpro.dropwizardgradle.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Record {
    private long id;

    private String name;

    public Record() {
        //deserializer
    }

    public Record(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }


}
