package io.github.edgarhar.productservice.feign;

import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.productservice.exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("catalog-service")
public interface CatalogServiceClient {

  @GetMapping("/catalog/{id}/id")
  @CircuitBreaker(name = "catalogService", fallbackMethod = "catalogIdErrorHandler")
  ResponseEntity<Product> getProductById(@PathVariable("id") String id);

  @GetMapping("/catalog/{id}/sku")
  @CircuitBreaker(name = "catalogService", fallbackMethod = "catalogSkuErrorHandler")
  ResponseEntity<List<Product>> getProductBySku(@PathVariable("id") String sku);

  default ResponseEntity<Product> catalogIdErrorHandler(Throwable throwable) {
    throw new ServiceUnavailableException("Could not call catalog service 'id' endpoint");
  }

  default ResponseEntity<Product> catalogSkuErrorHandler(Throwable throwable) {
    throw new ServiceUnavailableException("Could not call catalog service 'sku' endpoint");
  }

}
