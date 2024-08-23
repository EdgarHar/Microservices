package io.github.EdgarHar.catalogservice.service;

import io.github.edgarhar.common.domain.Product;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogService {

  private final Map<String, Product>       productIdMap;
  private final Map<String, List<Product>> productSkuMap;

  public Product getCatalogById(final String productId) {
    return Optional.of(productIdMap.get(productId))
                   .orElseThrow(() -> new NotFoundException("Product by ID: " + productId + " not found"));
  }

  public List<Product> getCatalogsBySku(final String sku) {
    return Optional.of(productSkuMap.get(sku))
                   .orElseThrow(() -> new NotFoundException("Products by SKU: " + sku + " not found"));
  }

}
