package io.github.edgarhar.inventoryservice;

import com.opencsv.exceptions.CsvException;
import io.github.edgarhar.inventoryservice.domain.Product;
import io.github.edgarhar.inventoryservice.util.CsvLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication
public class InventoryServiceApplication {

  @Autowired
  private CsvLoader csvLoader;

  public static void main(String[] args) {
    SpringApplication.run(InventoryServiceApplication.class, args);
  }

  @Bean
  public Map<String, Integer> inventoryMap() throws IOException, CsvException {
//    final Random random = new Random();
//
//    return csvLoader.loadProducts()
//        .stream()
//        .collect(Collectors.toMap(Product::getId, product -> random.nextInt(0, 11)));

    return null;
  }

}
