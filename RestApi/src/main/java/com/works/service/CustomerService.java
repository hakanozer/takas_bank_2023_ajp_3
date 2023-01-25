package com.works.service;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;
    final TinkEncDec tinkEncDec;
    final HttpSession httpSession;

    public ResponseEntity register(Customer customer) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        customer.setPassword( tinkEncDec.encrypt(customer.getPassword()) );
        hm.put(REnum.result, customerRepository.save(customer) );
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity login( Customer customer ) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<Customer> optionalCustomer = customerRepository.findByEmailEqualsIgnoreCase(customer.getEmail());
        if (optionalCustomer.isPresent() ) {
            Customer cus = optionalCustomer.get();
            String newPass = tinkEncDec.decrypt(cus.getPassword());
            if ( newPass.equals(customer.getPassword()) ) {
                hm.put(REnum.status, true);
                hm.put(REnum.result, "Login Success");
                httpSession.setAttribute("user", cus );
                return new ResponseEntity(hm, HttpStatus.OK);
            }
        }
        hm.put(REnum.status, false);
        hm.put(REnum.result, "Email or Password Fail");
        return new ResponseEntity(hm, HttpStatus.UNAUTHORIZED);
    }

}
