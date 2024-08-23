package io.github.edgarhar.productservice.service;

import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.productservice.exception.InventoryNotSupportedException;
import io.github.edgarhar.productservice.feign.CatalogServiceClient;
import io.github.edgarhar.productservice.feign.InventoryServiceClient;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final CatalogServiceClient   catalogServiceClient;
  private final InventoryServiceClient inventoryServiceClient;

  public Product getProductById(final String id) {
    return Optional
        .of(id)
        .map(id1 -> getCountByIdSet(Set.of(id)))
        .map(ResponseEntity::getBody)
        .map(map -> map.get(id))
        .filter(count -> count > 0)
        .map(count -> getCatalogById(id))
        .map(HttpEntity::getBody)
        .orElseThrow(() -> new InventoryNotSupportedException(
            "Product with id: " + id + " is not available in inventory")
        );
  }

  public List<Product> getProductsBySku(final String sku) {
    final List<Product> products = Optional.of(sku).map(this::getCatalogsBySku).map(HttpEntity::getBody).orElseThrow();
    final Map<String, Integer> inventory =
        getCountByIdSet(products.stream().map(Product::getId).collect(Collectors.toSet())).getBody();

    return products
        .stream()
        .filter(product -> inventory.get(product.getId()) > 0)
        .collect(Collectors.toList());

  }

  private ResponseEntity<Product> getCatalogById(final String id) {
    return Optional
        .of(id)
        .map(catalogServiceClient::getProductById)
        .filter(response -> !response.getStatusCode().is4xxClientError())
        .orElseThrow(() -> new NotFoundException("Product with ID: " + id + " not found in catalog"));
  }

  private ResponseEntity<List<Product>> getCatalogsBySku(final String sku) {
    return Optional
        .of(sku)
        .map(catalogServiceClient::getProductBySku)
        .filter(response -> !response.getStatusCode().is4xxClientError())
        .orElseThrow(() -> new NotFoundException("Products with SKU: " + sku + " not found in catalog"));
  }

  private ResponseEntity<Map<String, Integer>> getCountByIdSet(final Set<String> ids) {
    return inventoryServiceClient.getProductCountById(ids);
  }

}
