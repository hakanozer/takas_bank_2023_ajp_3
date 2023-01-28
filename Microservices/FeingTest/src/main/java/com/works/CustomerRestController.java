package com.works;

import com.works.props.News;
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


    final INews iNews;

    @GetMapping("/news")
    public Map news() {
        Map hm = new LinkedHashMap();
        hm.put("status", false);
        News news = iNews.news("38a9e086f10b445faabb4461c4aa71f8");
        hm.put("result", news.getArticles() );
        return hm;
    }

}
