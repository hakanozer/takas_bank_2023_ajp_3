package com.works.service;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    //@Autowired
    //ProductRepository productRepository;
    final ProductRepository productRepository;

    // product Save
    public ResponseEntity save(Product product) {
        
    }

}
