package io.github.edgarhar.productservice.controller;

import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.productservice.exception.InventoryNotSupportedException;
import io.github.edgarhar.productservice.exception.ServiceUnavailableException;
import io.github.edgarhar.productservice.service.ProductService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/{id}/sku")
  public ResponseEntity<List<Product>> getCatalogsBySku(@PathVariable(name = "id") String sku) {
    return ResponseEntity.ok(productService.getProductsBySku(sku));
  }

  @GetMapping("/{id}/id")
  public ResponseEntity<Product> getCatalogById(@PathVariable(name = "id") String id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @ExceptionHandler(InventoryNotSupportedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public Map<String, String> handleResourceNotFoundException(InventoryNotSupportedException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());

    return errorResponse;
  }

  @ExceptionHandler(ServiceUnavailableException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public Map<String, String> handleServiceUnavailableException(ServiceUnavailableException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());

    return errorResponse;
  }

}
