package com.mvc.project.controller;

import com.mvc.project.entity.User;
import com.mvc.project.services.UserService;
import com.mvc.project.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

/**
 * @author DacaP
 * @version 1.0
 * @create 01/10/2022 7:03 pm
 */
@Controller
public class LoginController {

    private static final String REDIRECT_ON_REGISTRATION_PAGE = "redirect:/registration";
    private static final String REDIRECT_ON_LOGIN_PAGE = "redirect:/";
    private static final String LOGIN_JSP_FILE = "login";
    private static final String REGISTRATION_JSP_FILE = "registration";

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private String error = null;

    @Autowired
    public LoginController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showLoginPage() {
        return LOGIN_JSP_FILE;
    }

    @PostMapping("/error")
    public String showErrorLoginPage(Model model) {
        model.addAttribute("error", "Incorrect username or password");
        return LOGIN_JSP_FILE;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        if (error != null) {
            model.addAttribute("error", error);
            error = null;
        }
        return REGISTRATION_JSP_FILE;
    }

    @PostMapping("/registration/add")
    public String saveUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("passwordAgain") String passwordAgain,
                           @RequestParam("email") String email,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("birthday") String birthday,
                           HttpSession session, HttpServletRequest request) {

        String captcha = session.getAttribute("captcha_security").toString();
        String verifyCaptcha = request.getParameter("captcha");

        if (!isPasswordsSame(password, passwordAgain) || !isEmailUniqueness(email)
                || !isUsernameUniqueness(username) || !isCaptchaCorrect(captcha, verifyCaptcha)) {
            return REDIRECT_ON_REGISTRATION_PAGE;
        } else {
            userService.create(User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .birthday(LocalDate.parse(birthday))
                    .role(roleService.findByName("user"))
                    .build());
            return REDIRECT_ON_LOGIN_PAGE;
        }
    }

    private boolean isUsernameUniqueness(String username) {
        if (userService.findByUsername(username) != null) {
            error = "Login already exist";
            return false;
        } else {
            return true;
        }
    }

    private boolean isEmailUniqueness(String email) {
        if (userService.findByEmail(email) != null) {
            error = "Email already exist";
            return false;
        } else {
            return true;
        }
    }

    private boolean isPasswordsSame(String firstPassword, String secondPassword) {
        if (!firstPassword.equals(secondPassword)) {
            error = "Passwords must be same";
            return false;
        } else {
            return true;
        }
    }

    private boolean isCaptchaCorrect(String captcha, String verifyCaptcha) {
        if (captcha.equals(verifyCaptcha)) {
            return true;
        } else {
            error = "Captcha Invalid";
            return false;
        }
    }
}