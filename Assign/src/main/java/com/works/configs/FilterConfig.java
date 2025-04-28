package com.works.configs;

import com.works.entities.Customer;
import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import com.works.utils.SecurityUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Configuration
public class FilterConfig implements Filter {

    final InfoRepository infoRepository;

    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String sessionID = request.getSession().getId();
        long time = System.currentTimeMillis();
        String agent = request.getHeader("User-Agent");


        String[] freeUrls = {"/customer", "/swagger-ui", "/v3"};
        boolean loginStatus = false;
        for (String freeUrl : freeUrls) {
            if (url.startsWith(freeUrl)) {
                loginStatus = true;
            }
        }

        if (loginStatus) {

            filterChain.doFilter(request, response);
        }else {

            Object userObject = request.getSession().getAttribute("user");
            Customer customerSession = SecurityUtil.customerSession;
            if (userObject != null) {

                Customer customer = (Customer) userObject;
                String email = customer.getEmail();

                Info info = new Info();
                info.setEmail(email);
                info.setIp(ip);
                info.setTime(time);
                info.setAgent(agent);
                info.setSessionID(sessionID);
                info.setUrl(url);
                infoRepository.save(info);

                filterChain.doFilter(request, response);
            }else {

                PrintWriter out = response.getWriter();
                response.setHeader("Content-Type", "application/json");
                out.println("{ \"status\": false, \"message\": \"Lütfen oturum açınız\" }");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();

    }
}
