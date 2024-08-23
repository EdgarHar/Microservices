package io.github.EdgarHar.catalogservice.controller;

import io.github.EdgarHar.catalogservice.service.CatalogService;
import io.github.edgarhar.common.domain.Product;
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
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

  private final CatalogService catalogService;

  @GetMapping("/{id}/sku")
  public ResponseEntity<List<Product>> getCatalogsBySku(@PathVariable(name = "id") String sku) {
    return ResponseEntity.ok(catalogService.getCatalogsBySku(sku));
  }

  @GetMapping("/{id}/id")
  public ResponseEntity<Product> getCatalogById(@PathVariable(name = "id") String id) {
    return ResponseEntity.ok(catalogService.getCatalogById(id));
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleResourceNotFoundException(NotFoundException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());

    return errorResponse;
  }

}
