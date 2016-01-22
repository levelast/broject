package ru.broject.site.auth.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.broject.site.auth.service.AuthService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vyacheslav.svininyh on 22.01.2016.
 */
@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final String[] exclusiveUrls = {"/login", "/auth", "/static"};

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        if (authService.getUserBySession(sessionId) != null || isExclusive(request.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    private boolean isExclusive(String path) {
        for (String exclusiveUrl : exclusiveUrls) {
            if (path.startsWith(exclusiveUrl)) {
                return true;
            }
        }
        return false;
    }
}
