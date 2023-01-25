package com.works.service;

import com.works.entities.Product;
import com.works.projections.IProJoinCat;
import com.works.repositories.ProductJoinCatRepository;
import com.works.repositories.ProductRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    //@Autowired
    //ProductRepository productRepository;
    final ProductRepository productRepository;
    final ProductJoinCatRepository productJoinCatRepository;

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

    // product save all
    public ResponseEntity productSaveAll( List<Product> productList ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        productRepository.saveAll( productList );
        hm.put(REnum.status, true);
        hm.put(REnum.result, productList);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity<Map> list(Long cid) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        //hm.put(REnum.result, productRepository.findAll());
        //hm.put(REnum.result, productJoinCatRepository.allProCat(1) );
        hm.put(REnum.result, productRepository.allProJoin(cid) );
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity<Map> pageList( int pageCount ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);

        Sort sort = Sort.by("price").ascending();
        Pageable pageable = PageRequest.of(pageCount, 5, sort );
        //Page<Product> list = productRepository.findAll(pageable);
        Page<IProJoinCat> list = productRepository.allPageProJoin(1l,pageable );
        hm.put(REnum.result, list );
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


    public ResponseEntity delete( String stringPid ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            long pid = Long.parseLong(stringPid);
            productRepository.deleteById(pid);
            hm.put(REnum.status, true);
            hm.put(REnum.result, "success");
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put(REnum.errors, "Not Found : " + stringPid );
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update( Product product ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        boolean status = productRepository.existsByPidEquals(product.getPid());
        if ( status ) {
            productRepository.saveAndFlush(product);
            hm.put(REnum.status, true);
            hm.put(REnum.result, product);
            return new ResponseEntity(hm, HttpStatus.OK);
        }
        hm.put(REnum.status, false);
        hm.put(REnum.errors, "Not Found :" + product.getPid());
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }


}
