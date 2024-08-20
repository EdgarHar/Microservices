package io.github.edgarhar.inventoryservice.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.github.edgarhar.inventoryservice.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CsvLoader {

  @Autowired
  private ResourceLoader resourceLoader;

  public List<Product> loadProducts() throws IOException, CsvException {
    final Resource resource = resourceLoader.getResource("classpath:data/jcpenney_com-ecommerce_sample.csv");
    final CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()));
    final List<Product> products = new ArrayList<>();
    final List<String[]> records = csvReader.readAll();
    records.remove(0);
    for (String[] record : records) {
      final Product product = Product
          .builder()
          .withId(record[0])
          .withSku(record[1])
          .withTitle(record[2])
          .withDescription(record[3])
          .withPrice(Optional
              .of(record[4])
              .filter(str -> !str.isBlank() && !str.contains("-"))
              .map(BigDecimal::new)
              .orElse(null))
          .withSalePrice(Optional
              .of(record[5])
              .filter(str -> !str.isBlank() && !str.contains("-"))
              .map(BigDecimal::new)
              .orElse(null))
          .withCategory(record[6])
          .withCategoryTree(record[7])
          .withAverageRating(record[8])
          .withProductURL(record[9])
          .withProductImageURL(record[10])
          .withBrand(record[11])
          .withTotalReviews(record[12])
          .build();

      products.add(product);
    }
    csvReader.close();

    return products;
  }

}
