package com.works;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.works.props.News;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerRestController {

    final DiscoveryClient discoveryClient;
    final IProduct iProduct;
    final INews iNews;

    @GetMapping("/info")
    @HystrixCommand(fallbackMethod = "callFnc", commandProperties = @HystrixProperty(
            name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"
    ))
    public Map info() {
        Map hm = new LinkedHashMap();
        hm.put("status", true);
        //int i = 1 / 0;
        /*
        List<ServiceInstance> ls = discoveryClient.getInstances("product");
        if ( ls != null && ls.size() > 0 ) {
            ServiceInstance instance = ls.get(0);
            String url = instance.getUri().toString();
            RestTemplate restTemplate = new RestTemplate();
            url = url + "/product/single";
            Object  stData = restTemplate.getForObject(url, Object.class);
            hm.put("product", stData);
        }*/
        hm.put("product", iProduct.single());
        return hm;
    }

    public Map callFnc() {
        Map hm = new LinkedHashMap();
        hm.put("status", false);
        hm.put("message", "Servis Error, Try Again");
        return hm;
    }


    @GetMapping("/news")
    public Map news() {
        Map hm = new LinkedHashMap();
        hm.put("status", false);
        News news = iNews.news("38a9e086f10b445faabb4461c4aa71f8");
        hm.put("result", news.getArticles() );
        return hm;
    }

}
