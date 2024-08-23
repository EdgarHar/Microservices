package io.github.edgarhar.inventoryservice.service;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final Map<String, Integer> inventoryMap;

  public Integer getInventory(final String productId) {
    return Optional.of(inventoryMap.get(productId)).orElseThrow(
        () -> new NotFoundException("Product with id " + productId + " not found"));
  }

}
