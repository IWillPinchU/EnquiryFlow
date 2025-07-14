package com.dbe.gateway_server;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()

				.route(predicateSpec -> predicateSpec
						.path("/dbe/admin/**")
						.filters(f -> f
								.rewritePath("/dbe/admin/(?<segment>.*)","/${segment}")
										.circuitBreaker(config -> config
												.setName("adminCircuitBreaker")
												.setFallbackUri("forward:/contactSupport")
										)
								)
						.uri("lb://ADMIN-SERVICE"))

				.route(predicateSpec -> predicateSpec
						.path("/dbe/public/**")
						.filters(f -> f
								.rewritePath("/dbe/public/(?<segment>.*)","/${segment}")
								.circuitBreaker(config -> config
												.setName("enquiryCircuitBreaker")
												.setFallbackUri("forward:/contactSupport")
								)
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(ipKeyResolver()))
								)
						.uri("lb://ENQUIRY-SERVICE"))
				.build();
	}

	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCircuitBreakerCustomizer() {
		return reactiveResilience4JCircuitBreakerFactory -> reactiveResilience4JCircuitBreakerFactory
				.configureDefault(name -> new Resilience4JConfigBuilder(name)
						.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
						.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
						.build()
				);
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 10, 10);
	}

	@Bean
	public KeyResolver ipKeyResolver() {
		return exchange -> Mono.justOrEmpty(
				exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
		);
	}
}
