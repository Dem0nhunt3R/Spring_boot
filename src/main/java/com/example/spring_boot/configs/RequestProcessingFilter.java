package com.example.spring_boot.configs;

import com.example.spring_boot.dao.AuthDAO;
import com.example.spring_boot.models.AuthToken;
import com.example.spring_boot.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

public class RequestProcessingFilter extends GenericFilterBean {
    private AuthDAO authDAO;

    public RequestProcessingFilter(AuthDAO authDAO) {
        this.authDAO = authDAO;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestAuthorizationHeader = request.getHeader("Authorization");
        System.out.println(requestAuthorizationHeader);
        if (requestAuthorizationHeader != null && requestAuthorizationHeader.startsWith("Bearer ")) {
            String cleanToken = requestAuthorizationHeader.replace("Bearer ", "");
            AuthToken token = authDAO.findByToken(cleanToken);
            if (token != null) {
                User user = token.getUser();
                String username = user.getUsername();
                String password = user.getPassword();
                Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(new UsernamePasswordAuthenticationToken(username, password, authorities));
            }


        }
        filterChain.doFilter(servletRequest, servletResponse);


    }
}
