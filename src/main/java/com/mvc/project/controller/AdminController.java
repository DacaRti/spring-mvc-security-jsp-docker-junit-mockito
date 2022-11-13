package com.mvc.project.controller;

import com.mvc.project.entity.User;
import com.mvc.project.services.RoleService;
import com.mvc.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

/**
 * @author DacaP
 * @version 1.0
 * @create 01/10/2022 7:30 pm
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String REDIRECT_ON_ADMIN_PAGE = "redirect:/admin/";
    private static final String REDIRECT_ON_ADD_USER_PAGE = "redirect:/admin/add";
    private static final String REDIRECT_ON_UPDATE_USER_PAGE = "redirect:/admin/addForUpdate";
    private static final String ADMIN_JSP_FILE = "admin";
    private static final String UPDATE_JSP_FILE = "addOrUpdateUser";

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private String error = null;


    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showAdminPage(Model model) {
        if (error != null) {
            model.addAttribute("error", error);
            error = null;
        }
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return ADMIN_JSP_FILE;
    }

    @PostMapping("/delete")
    public String deleteUser(HttpSession session,
                             @RequestParam(required = false, name = "id") String id) {

        User admin = userService.findById(Integer.parseInt(String.valueOf(session.getAttribute("id"))));
        User user = userService.findById(Integer.parseInt(id));

        if (!admin.equals(user)) {
            userService.remove(Long.parseLong(id));
        } else {
            error = "The user cannot delete himself";
        }
        return REDIRECT_ON_ADMIN_PAGE;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        if (error != null) {
            model.addAttribute("error", error);
            error = null;
        }
        model.addAttribute("action", "Add user");
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("saveOrUpdate", "/admin/save");
        return UPDATE_JSP_FILE;
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("passwordAgain") String passwordAgain,
                           @RequestParam("email") String email,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("birthday") String birthday,
                           @RequestParam("role") String role) {

        if (!isPasswordsSame(password, passwordAgain) || !isUsernameUniqueness(username) || !isEmailUniqueness(email)) {
            return REDIRECT_ON_ADD_USER_PAGE;
        } else {
            userService.create(User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .birthday(LocalDate.parse(birthday))
                    .role(roleService.findByName(role))
                    .build());
            return REDIRECT_ON_ADMIN_PAGE;
        }
    }

    @GetMapping("/addForUpdate")
    public String addUserForUpdate(Model model,
                                   @RequestParam(required = false, name = "id") String id) {
        if (error != null) {
            model.addAttribute("error", error);
            error = null;
        }
        User userForUpdate = userService.findById(Long.parseLong(id));
        model.addAttribute("user", userForUpdate);
        model.addAttribute("action", "Update " + userForUpdate.getRole().getName());
        model.addAttribute("saveOrUpdate", "/admin/update/");
        model.addAttribute("readability", "readonly");
        model.addAttribute("roles", roleService.findAll());

        return UPDATE_JSP_FILE;
    }

    @PostMapping("/update")
    public String update(Model model,
                         @RequestParam(required = false, name = "id") String id,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("passwordAgain") String passwordAgain,
                         @RequestParam(required = false, name = "currentPassword") String currentPassword,
                         @RequestParam("email") String email,
                         @RequestParam(required = false, name = "currentEmail") String currentEmail,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("birthday") String birthday,
                         @RequestParam("role") String role) {

        model.addAttribute("id", Long.parseLong(id));

        if (!isPasswordsSame(password, passwordAgain) || !isEmailValidity(email, currentEmail)) {
            return REDIRECT_ON_UPDATE_USER_PAGE;
        } else {
            userService.update(User.builder()
                    .id(Long.parseLong(id))
                    .username(username)
                    .password(getPasswordForUpdatedUser(currentPassword, password))
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .birthday(LocalDate.parse(birthday))
                    .role(roleService.findByName(role))
                    .build());
            return REDIRECT_ON_ADMIN_PAGE;
        }
    }

    private String getPasswordForUpdatedUser(String currentPassword, String newPassword) {
        if (newPassword.isEmpty()) {
            return currentPassword;
        } else {
            return passwordEncoder.encode(newPassword);
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

    private boolean isEmailValidity(String email, String currentUserEmail) {
        if (userService.findByEmail(email) != null && !email.equals(currentUserEmail)) {
            error = "Email already exist";
            return false;
        } else {
            return true;
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
}

