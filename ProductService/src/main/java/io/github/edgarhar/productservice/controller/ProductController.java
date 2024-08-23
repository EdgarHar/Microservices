package io.github.edgarhar.productservice.controller;

import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/{id}/sku")
  public ResponseEntity<List<Product>> getCatalogsBySku(@PathVariable(name = "id") String sku) {
    return ResponseEntity.ok(productService.getCatalogsBySku(sku));
  }

  @GetMapping("/{id}/id")
  public ResponseEntity<Product> getCatalogById(@PathVariable(name = "id") String id) {
    return ResponseEntity.ok(productService.getCatalogById(id));
  }

}
