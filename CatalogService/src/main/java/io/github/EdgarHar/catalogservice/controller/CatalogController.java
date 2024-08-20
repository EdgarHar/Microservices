package io.github.EdgarHar.catalogservice.controller;

import io.github.EdgarHar.catalogservice.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

  private final List<Product> products;

  @GetMapping
  public String getCatalog() {
    products.forEach(System.out::println);

    return "AAA";
  }

}
