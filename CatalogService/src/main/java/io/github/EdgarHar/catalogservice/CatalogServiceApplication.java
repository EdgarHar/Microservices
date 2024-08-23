package io.github.EdgarHar.catalogservice;

import com.opencsv.exceptions.CsvException;
import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.common.utility.CsvLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication(scanBasePackages = {"io.github.EdgarHar.catalogservice", "io.github.edgarhar.common"})
public class CatalogServiceApplication {

  private static List<Product> products;

  @Autowired
  private CsvLoader csvLoader;

  public static void main(String[] args) {
    SpringApplication.run(CatalogServiceApplication.class, args);
  }

  @Bean
  public Map<String, Product> productIdMap() {
    return products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
  }

  @Bean
  public Map<String, List<Product>> productSkuMap() {
    return products.stream().collect(Collectors.groupingBy(Product::getSku));
  }

  @PostConstruct
  public void init() throws IOException, CsvException {
    products = csvLoader.loadProducts();
  }

}
