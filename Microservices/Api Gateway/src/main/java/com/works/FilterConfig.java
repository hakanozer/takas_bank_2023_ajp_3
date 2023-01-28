package com.works;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class FilterConfig implements Filter {

    final DiscoveryClient discoveryClient;
    public FilterConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();

        String agent = req.getHeader("User-Agent");
        System.out.println(agent);

        String subUrl = url.split("/")[1];
        List<ServiceInstance> ls = discoveryClient.getInstances(subUrl);
        if (  ls != null && ls.size() > 0 ) {
            String gotoUrl = ls.get(0).getUri().toString();
            gotoUrl = gotoUrl + url;
            res.sendRedirect( gotoUrl );
        }else {
            filterChain.doFilter(req, res);
        }
    }

}
