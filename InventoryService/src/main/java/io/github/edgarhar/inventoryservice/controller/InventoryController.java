package io.github.edgarhar.inventoryservice.controller;

import io.github.edgarhar.inventoryservice.service.InventoryService;
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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping("/{id}")
  public ResponseEntity<Integer> getInventory(@PathVariable(name = "id") String id) {
    return ResponseEntity.ok(inventoryService.getInventory(id));
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleResourceNotFoundException(NotFoundException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getMessage());

    return errorResponse;
  }

}
