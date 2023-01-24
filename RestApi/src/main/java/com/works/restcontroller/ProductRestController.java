package com.works.restcontroller;

import com.works.entities.Product;
import com.works.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return productService.list();
    }

    @GetMapping("/single/{stringPid}")
    public ResponseEntity single( @PathVariable String stringPid ) {
        return productService.single(stringPid);
    }

    @GetMapping("/singleParam")
    public ResponseEntity singleParam( @RequestParam(defaultValue = "1") String pid ) {
        return productService.single(pid);
    }

}
