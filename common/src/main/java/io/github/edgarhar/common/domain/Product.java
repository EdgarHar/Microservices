package io.github.edgarhar.common.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(setterPrefix = "with")
public class Product {

  private String       id;
  private String       sku;
  private String       title;
  private String       description;
  private BigDecimal   price;
  private BigDecimal   salePrice;
  private String       category;
  private String       categoryTree;
  private String       averageRating;
  private String       productURL;
  private String       productImageURL;
  private String       brand;
  private String       totalReviews;

}
