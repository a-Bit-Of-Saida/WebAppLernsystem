package edu.fra.uas.API_Gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class CustomHeaderFilter extends AbstractGatewayFilterFactory<CustomHeaderFilter.Config> {
    private static final Logger log = LoggerFactory.getLogger(CustomHeaderFilter.class);
    public CustomHeaderFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Request received at API Gateway: {}", exchange.getRequest().getURI());
            exchange.getRequest()
                    .mutate()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer example-token");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Response sent from API Gateway: {}", exchange.getResponse().getStatusCode());
            }));
        };
    }
    public static class Config {
        
    }
}
