package io.github.EdgarHar.catalogservice;

import com.opencsv.exceptions.CsvException;
import io.github.EdgarHar.catalogservice.domain.Product;
import io.github.EdgarHar.catalogservice.util.CsvLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class CatalogServiceApplication {

  @Autowired
  private CsvLoader csvLoader;

  public static void main(String[] args) {
    SpringApplication.run(CatalogServiceApplication.class, args);
  }

  @Bean
  public List<Product> loadProducts() throws IOException, CsvException {
    return csvLoader.loadProducts();
  }

}
