package io.github.EdgarHar.catalogservice;

import io.github.edgarhar.common.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = {"io.github.EdgarHar.catalogservice", "io.github.edgarhar.common"})
public class CatalogServiceApplication {

  @Autowired
  private Set<Product> productSet;

  public static void main(String[] args) {
    SpringApplication.run(CatalogServiceApplication.class, args);
  }

  @Bean
  public Map<String, Product> productIdMap() {
    return productSet.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
  }

  @Bean
  public Map<String, List<Product>> productSkuMap() {
    return productSet.stream().collect(Collectors.groupingBy(Product::getSku));
  }

}
