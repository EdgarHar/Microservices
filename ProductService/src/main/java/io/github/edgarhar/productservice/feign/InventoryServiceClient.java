package io.github.edgarhar.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

@FeignClient("inventory-service")
public interface InventoryServiceClient {

  @GetMapping("/inventory")
  ResponseEntity<Map<String, Integer>> getProductCountById(@RequestBody Set<String> id);

}
