package io.github.edgarhar.common;

import com.opencsv.exceptions.CsvException;
import io.github.edgarhar.common.domain.Product;
import io.github.edgarhar.common.utility.CsvLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Set;

@Configuration
@SpringBootApplication
public class CommonApplication {

  @Autowired
  private CsvLoader csvLoader;

  public static void main(String[] args) {
    SpringApplication.run(CommonApplication.class, args);
  }

  @Bean
  public Set<Product> productSet() throws IOException, CsvException {
    return csvLoader.loadProducts();
  }

}
