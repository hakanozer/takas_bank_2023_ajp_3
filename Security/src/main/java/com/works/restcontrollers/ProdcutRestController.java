package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.repositories.NoteRepository;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProdcutRestController {

    final ProductRepository repository;
    final CacheManager cacheManager;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product note) {
        Map hm = new LinkedHashMap();
        repository.save(note);
        hm.put("status", true);
        hm.put("result", note);
        cacheManager.getCache("product").clear();
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    @GetMapping("/list")
    @Cacheable("product")
    public ResponseEntity list() {
        Map hm = new LinkedHashMap();
        hm.put("status", true);
        hm.put("result", repository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    @Scheduled(fixedDelay = 9000, timeUnit = TimeUnit.MILLISECONDS)
    public void timer() {
        cacheManager.getCache("product").clear();
        System.out.println("Timer Call");
    }

}
