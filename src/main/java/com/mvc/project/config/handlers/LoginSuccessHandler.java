package com.mvc.project.config.handlers;

import com.mvc.project.entity.User;
import com.mvc.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author DacaP
 * @version 1.0
 * @create 09/10/2022 5:49 pm
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final String ADMIN_URL = "/admin/";
    private static final String USER_URL = "/user";

    private final UserService userService;

    @Autowired
    public LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());

        session.setAttribute("id", user.getId());
        session.setAttribute("userFirstName", user.getFirstName());

        if (user.getRole().getName().equals("admin")) {
            response.sendRedirect(ADMIN_URL);
        } else if (user.getRole().getName().equals("user")) {
            response.sendRedirect(USER_URL);
        }
    }
}
