package io.github.edgarhar.productservice.feign;

import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.productservice.exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

@FeignClient("inventory-service")
public interface InventoryServiceClient {

  @PostMapping("/inventory")
  @CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryServiceErrorHandler")
  ResponseEntity<Map<String, Integer>> getProductCountById(@RequestBody Set<String> id);

  default ResponseEntity<Product> inventoryServiceErrorHandler(Throwable throwable) {
    throw new ServiceUnavailableException("Could not call inventory service endpoint");
  }

}
