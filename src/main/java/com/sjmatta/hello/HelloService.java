package com.sjmatta.hello;

import com.sjmatta.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class HelloService {

    @Inject
    public HelloService() {

    }

    public Response getHello(Map<String, Object> input) {
        return new Response("Serverless ♥ Dagger ", input);
    }
}
