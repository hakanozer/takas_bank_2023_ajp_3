package com.works;

import com.works.props.News;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "newsapi", url = "https://newsapi.org/v2/")
public interface INews {

    @GetMapping("everything?q=bitcoin&from=2022-12-28&sortBy=publishedAt")
    News news(@RequestParam String apiKey);

}
