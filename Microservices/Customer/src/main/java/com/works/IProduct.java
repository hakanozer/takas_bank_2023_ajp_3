package com.works;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product")
public interface IProduct {

    @GetMapping("/product/single")
    Product single();

}
