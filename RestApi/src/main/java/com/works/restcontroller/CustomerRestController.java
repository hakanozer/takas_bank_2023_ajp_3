package com.works.restcontroller;

import com.works.entities.Customer;
import com.works.service.CustomerService;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Customer customer) {
        return customerService.register(customer);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Customer customer) {
        return customerService.login(customer);
    }

    @GetMapping("/loginError")
    public ResponseEntity loginError() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.result, "Please Login" );
        return new ResponseEntity(hm, HttpStatus.UNAUTHORIZED);
    }

}
