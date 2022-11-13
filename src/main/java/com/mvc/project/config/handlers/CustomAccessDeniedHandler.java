package com.mvc.project.config.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DacaP
 * @version 1.0
 * @create 09/10/2022 6:47 pm
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final String ACCESS_DENIED_JSP = "/WEB-INF/view/accessDenied.jsp";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            log.info("User " + authentication.getName()
                    + "attempted to access the URL: "
                    + request.getRequestURI());
        }
        request.getRequestDispatcher(ACCESS_DENIED_JSP).forward(request, response);
    }
}
