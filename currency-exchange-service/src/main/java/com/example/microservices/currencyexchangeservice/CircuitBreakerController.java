package com.example.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    //@Retry(name = "sample-api" , fallbackMethod = "hardCodedResponse")
    //@CircuitBreaker(name = "sample-api" , fallbackMethod = "hardCodedResponse")
    @Bulkhead(name = "default")
    @RateLimiter(name = "default")

    // 10s -> allow a number of calls to some api
    public String sampleApi()
    {
        logger.info("sample api recieved");
       // ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url",String.class) ;

       /// return entity.getBody();
        return "holaa";
    }

    public String hardCodedResponse(Throwable ex){
        return "fallback Response";
    }
}
