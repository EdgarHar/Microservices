package io.github.edgarhar.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("inventory-service")
public interface InventoryServiceClient {}
