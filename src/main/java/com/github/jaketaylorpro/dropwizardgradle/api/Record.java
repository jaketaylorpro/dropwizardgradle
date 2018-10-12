package com.github.jaketaylorpro.dropwizardgradle.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

public class Record {
    private long id;

    private String name;

    private Date insertDate;

    public Record() {
        //deserializer
    }

    public Record(long id, String name, Date insertDate) {
        this.id = id;
        this.name = name;
        this.insertDate = insertDate;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public Date getInsertDate() {
        return insertDate;
    }


}
