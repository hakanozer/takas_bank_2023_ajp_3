package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final InfoRepository repository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        List<String> roles = new ArrayList<>();
        auth.getAuthorities().forEach( item -> {
            roles.add(item.getAuthority());
        } );
        String userAgent = req.getHeader("user-agent");
        String details = auth.getDetails().toString();

        Info i = new Info();
        i.setUserName(userName);
        i.setDetails(details);
        i.setRoles(roles.toString());
        i.setUrl(url);
        i.setUserAgent(userAgent);
        i.setDate(new Date());

        repository.save(i);

        filterChain.doFilter(req, res);
     }


}
