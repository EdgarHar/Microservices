package io.github.edgarhar.inventoryservice;

import io.github.edgarhar.common.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = {"io.github.edgarhar.inventoryservice", "io.github.edgarhar.common"})
public class InventoryServiceApplication {

  @Autowired
  private Set<Product> productSet;

  public static void main(String[] args) {
    SpringApplication.run(InventoryServiceApplication.class, args);
  }

  @Bean
  public Map<String, Integer> inventoryMap() {
    final Random random = new Random();

    return productSet
        .stream()
        .collect(Collectors.toMap(Product::getId, product -> random.nextInt(0, 11)));
  }

}
