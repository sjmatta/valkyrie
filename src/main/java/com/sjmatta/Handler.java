package com.sjmatta;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sjmatta.dto.ApiGatewayResponse;
import com.sjmatta.hello.HelloService;
import com.sjmatta.hello.HelloService_Factory;
import dagger.Module;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;

@Module
public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    private HelloService helloService;

    public Handler() {
        this.helloService = HelloService_Factory.create().get();
    }

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: " + input);
        Response responseBody = helloService.getHello(input);
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(responseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
    }
}
