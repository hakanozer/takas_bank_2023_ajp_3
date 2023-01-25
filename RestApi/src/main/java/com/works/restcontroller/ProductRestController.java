package com.works.restcontroller;

import com.works.entities.Product;
import com.works.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveAll(@RequestBody List<Product> productList) {
        return productService.productSaveAll(productList);
    }

    @GetMapping("/list")
    public ResponseEntity list(@RequestParam(defaultValue = "1") Long cid) {
        return productService.list(cid);
    }

    @GetMapping("/single/{stringPid}")
    public ResponseEntity single( @PathVariable String stringPid ) {
        return productService.single(stringPid);
    }

    @GetMapping("/singleParam")
    public ResponseEntity singleParam( @RequestParam( defaultValue = "1") String pid ) {
        return productService.single(pid);
    }

    @DeleteMapping("/delete/{stringPid}")
    public ResponseEntity delete( @PathVariable(name = "stringPid") String stPid ) {
        return productService.delete(stPid);
    }

    @PutMapping("/update")
    public ResponseEntity update( @Valid @RequestBody Product product ) {
        return productService.update(product);
    }

    @GetMapping("/pageList")
    public ResponseEntity pageList( @RequestParam(defaultValue = "0") int pageCount ) {
        return productService.pageList(pageCount);
    }

}
