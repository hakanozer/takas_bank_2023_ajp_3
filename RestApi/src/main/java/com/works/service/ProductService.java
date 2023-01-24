package com.works.service;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    //@Autowired
    //ProductRepository productRepository;
    final ProductRepository productRepository;

    // product Save
    public ResponseEntity save(Product product) {
        Map<REnum, Object> hm = new LinkedHashMap<>();

        Optional<Product> optionalProduct = productRepository.findByTitleEqualsIgnoreCase(product.getTitle());
        if (optionalProduct.isPresent()) {
            hm.put(REnum.status, false);
            hm.put(REnum.errors, "Unique (Title) index or primary key Fail:" + product.getTitle());
            //throw new SQLIntegrityConstraintViolationException("Error Title");
            return new ResponseEntity(hm, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            productRepository.save(product);
            hm.put(REnum.status, true);
            hm.put(REnum.result, product);
            return new ResponseEntity(hm, HttpStatus.OK);
        }

    }

    public ResponseEntity<Map> list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, productRepository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity<Map> single( String stringPid ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            long pid = Long.parseLong(stringPid);
            Optional<Product> optionalProduct = productRepository.findById(pid);
            if ( optionalProduct.isPresent() ) {
                hm.put(REnum.status, true);
                hm.put(REnum.result, optionalProduct.get()  );
                return new ResponseEntity(hm, HttpStatus.OK);
            }
        }catch (Exception ex) {}
        hm.put(REnum.status, true);
        hm.put(REnum.errors, "Not Found : " + stringPid );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }


}
