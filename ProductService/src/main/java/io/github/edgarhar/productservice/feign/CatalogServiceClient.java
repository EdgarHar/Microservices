package io.github.edgarhar.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("catalog-service")
public interface CatalogServiceClient {


}
