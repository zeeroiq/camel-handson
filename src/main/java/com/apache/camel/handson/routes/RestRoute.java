package com.apache.camel.handson.routes;

import com.apache.camel.handson.services.OrderService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {

    private final Environment env;
    // context/env properties constants
    private final String CONTEXT_PATH = "/ecom";
    private final String API_CONTEXT_PATH = "api-doc";
    private final String PROPERTY_API_TITLE = "api.title";
    private final String TITLE = "Camel Hands On";
    private final String PROPERTY_API_VERSION = "api.version";
    private final String API_VERSION = "1.0";
    private final String PROPERTY_CORS = "cors";
    private final String CORS = "true";
    private final String PROPERTY_CONTEXT_ROUTE_ID = "api-doc";
    private final String PORT = "8090";

    // rest configuration constants
    private final String REST_PATH = "/order";
    private final String REST_DESCRIPTION = "Process Order";
    private final String REST_ROUTE_ID = "orders-api";

    public RestRoute(Environment env) {
        this.env = env;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .contextPath(CONTEXT_PATH)
                .apiContextPath(API_CONTEXT_PATH)
                .apiProperty(PROPERTY_API_TITLE, TITLE)
                .apiProperty(PROPERTY_API_VERSION, API_VERSION)
                .apiProperty(PROPERTY_CORS, CORS)
                .apiContextRouteId(PROPERTY_CONTEXT_ROUTE_ID)
                .port(env.getProperty("server.port", PORT))
                .bindingMode(RestBindingMode.json);

        rest(REST_PATH)
                .get("/process").description(REST_DESCRIPTION)
                .route().routeId(REST_ROUTE_ID)
                .bean(OrderService.class, "fetchOrderLine")
                .to("direct:processOrders")
//                .bean(OrderService.class, "fetchOrders")
                .endRest();
    }
}
