package io.github.EdgarHar.catalogservice.controller;

import io.github.EdgarHar.catalogservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

  private final Map<String, Product> productIdMap;
  private final Map<String, List<Product>> productSkuMap;

  @GetMapping("/{id}/sku")
  public ResponseEntity<List<Product>> getCatalogsBySku(@PathVariable(name = "id") String sku) {
    return ResponseEntity.ok(productSkuMap.get(sku));
  }

  @GetMapping("/{id}/id")
  public ResponseEntity<Product> getCatalogById(@PathVariable(name = "id") String id) {
    return ResponseEntity.ok(productIdMap.get(id));
  }

}
