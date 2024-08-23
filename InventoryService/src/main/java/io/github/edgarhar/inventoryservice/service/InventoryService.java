package io.github.edgarhar.inventoryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final Map<String, Integer> inventoryMap;

  public Map<String, Integer> getInventory(final Set<String> productId) {
    return inventoryMap.entrySet()
        .stream()
        .filter(entry -> productId.contains(entry.getKey()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

}
