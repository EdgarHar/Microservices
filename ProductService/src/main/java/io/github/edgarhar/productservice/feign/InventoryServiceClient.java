package io.github.edgarhar.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("inventory-service")
public interface InventoryServiceClient {

  @GetMapping("/inventory/{id}")
  ResponseEntity<Integer> getProductCountById(@PathVariable("id") String id);

}
