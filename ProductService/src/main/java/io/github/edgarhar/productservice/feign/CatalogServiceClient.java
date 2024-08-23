package io.github.edgarhar.productservice.feign;

import io.github.edgarhar.common.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("catalog-service")
public interface CatalogServiceClient {

  @GetMapping("/catalog/{id}/id")
  ResponseEntity<Product> getProductById(@PathVariable("id") String id);

  @GetMapping("/catalog/{id}/sku")
  ResponseEntity<List<Product>> getProductBySku(@PathVariable("id") String sku);

}
