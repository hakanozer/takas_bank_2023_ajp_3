package com.works.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.AllProducts;
import com.works.props.JWT;
import com.works.props.JWTUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DummyService {

    final RestTemplate restTemplate;
    final ObjectMapper objectMapper;

    public ResponseEntity allProduct() {
        String url = "https://dummyjson.com/products";
        AllProducts stData = restTemplate.getForObject(url, AllProducts.class);
        return new ResponseEntity(stData, HttpStatus.OK);
    }

    public ResponseEntity login( ) {

        JWT jwt = new JWT();
        jwt.setPassword("0lelplR");
        jwt.setUsername("kminchelle");
        String url = "https://dummyjson.com/auth/login";
        try {
            String jsonObj = objectMapper.writeValueAsString(jwt);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(jsonObj, httpHeaders);

            ResponseEntity<JWTUser> responseEntity = restTemplate.postForEntity(url, httpEntity, JWTUser.class );
            JWTUser jwtUser = responseEntity.getBody();
            System.out.println( jwtUser.getToken() );
            return responseEntity;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
