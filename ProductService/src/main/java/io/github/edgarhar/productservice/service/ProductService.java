package io.github.edgarhar.productservice.service;

import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.productservice.feign.CatalogServiceClient;
import io.github.edgarhar.productservice.feign.InventoryServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final CatalogServiceClient   catalogServiceClient;
  private final InventoryServiceClient inventoryServiceClient;

  public List<Product> getCatalogsBySku(final String sku) {
    return catalogServiceClient.getProductBySku(sku).getBody();
  }

  public Product getCatalogById(final String id) {
    return catalogServiceClient.getProductById(id).getBody();
  }

}
