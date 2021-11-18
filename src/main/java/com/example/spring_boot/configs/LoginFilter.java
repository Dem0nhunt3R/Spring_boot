package com.example.spring_boot.configs;

import com.example.spring_boot.dao.UserDAO;
import com.example.spring_boot.dto.UserRequestDTO;
import com.example.spring_boot.models.AuthToken;
import com.example.spring_boot.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private UserDAO userDAO;

    public LoginFilter(String url, AuthenticationManager authenticationManager, UserDAO userDAO) {
        setFilterProcessesUrl(url);
        setAuthenticationManager(authenticationManager);
        this.userDAO = userDAO;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserRequestDTO dto = new ObjectMapper().readValue(request.getInputStream(), UserRequestDTO.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .signWith(SignatureAlgorithm.HS512, "yes".getBytes(StandardCharsets.UTF_8))
                .compact();
        User user = userDAO.findUserByUsername(authResult.getName());
        AuthToken authToken = new AuthToken();
        authToken.setUser(user);
        authToken.setToken(jwtToken);
        user.getAuthTokens().add(authToken);
        userDAO.save(user);
        response.addHeader("Authorization", "Bearer " + jwtToken);
        chain.doFilter(request, response);
    }
}
