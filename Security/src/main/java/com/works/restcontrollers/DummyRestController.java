package com.works.restcontrollers;

import com.works.services.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class DummyRestController {

    final DummyService dummyService;

    @GetMapping("/allProduct")
    public ResponseEntity allProduct() {
        return dummyService.allProduct();
    }

    @PostMapping("/login")
    public ResponseEntity login() {
        return dummyService.login();
    }

}
