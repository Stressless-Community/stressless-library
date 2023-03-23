package com.stressless.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/gradebook/**")
	            //.filters(f -> f.addRequestHeader("Hello", "World"))
	            .uri("lb://GRADEBOOK-SERVICE")).
	        build();
	}
}
