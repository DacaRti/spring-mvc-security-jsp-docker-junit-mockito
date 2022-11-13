package com.mvc.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author DacaP
 * @version 1.0
 * @create 02/10/2022 7:43 pm
 */
@Controller
public class UserController {

    private static final String USER_JSP_FILE = "user";

    @GetMapping("/user")
    public String showUserPage() {
        return USER_JSP_FILE;
    }
}