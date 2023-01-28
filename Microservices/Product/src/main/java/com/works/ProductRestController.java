package com.works;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @GetMapping("/single")
    public Map single() {
        Map hm = new LinkedHashMap();
        hm.put("status", true);
        hm.put("result", "TV");
        return hm;
    }

}
