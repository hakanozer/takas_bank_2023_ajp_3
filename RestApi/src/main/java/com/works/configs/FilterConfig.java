package com.works.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("Server UP");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();

        boolean status = true;
        if ( url.contains("/customer/") ) {
            status = false;
        }

        if ( status ) {
            boolean loginStatus = req.getSession().getAttribute("user") == null;
            if (loginStatus) {
                res.sendRedirect("/customer/loginError");
            }else {
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {
        System.out.println("Server DOWN");
    }

}
