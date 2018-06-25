package com.gojek.drivers;

import com.bendb.dropwizard.redis.JedisFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class whereIsMyDriver4Configuration extends Configuration {
    // TODO: implement service configuration
    @NotNull
    @JsonProperty
    private JedisFactory redis;

    public JedisFactory getJedisFactory() {
        return redis;
    }

    public void setJedisFactory(JedisFactory jedisFactory) {
        this.redis = jedisFactory;
    }
}
