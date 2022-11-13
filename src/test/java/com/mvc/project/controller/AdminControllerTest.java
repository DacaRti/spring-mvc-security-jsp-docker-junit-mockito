package com.mvc.project.controller;

import com.mvc.project.entity.Role;
import com.mvc.project.entity.User;
import com.mvc.project.services.RoleService;
import com.mvc.project.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author DacaP
 * @version 1.0
 * @create 11/10/2022 11:03 pm
 */
public class AdminControllerTest {

    @Mock
    private RoleService roleService;
    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AdminController adminController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void testAdminShow() throws Exception {
        when(userService.findAll()).thenReturn(List.of(TestData.admin, TestData.user));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("admin"));

        assertIterableEquals(List.of(TestData.admin, TestData.user), userService.findAll());
    }

    @Test
    public void testAddUserShow() throws Exception {
        when(roleService.findAll()).thenReturn(List.of(TestData.adminRole, TestData.userRole));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/add/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("action"))
                .andExpect(model().attributeExists("saveOrUpdate"))
                .andExpect(view().name("addOrUpdateUser"));

        assertIterableEquals(List.of(TestData.adminRole, TestData.userRole), roleService.findAll());
    }

    @Test
    public void testUpdateUserShow() throws Exception {
        when(roleService.findAll()).thenReturn(List.of(TestData.adminRole, TestData.userRole));
        when(userService.findById(1)).thenReturn(TestData.admin);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/addForUpdate/")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("action"))
                .andExpect(model().attributeExists("saveOrUpdate"))
                .andExpect(model().attributeExists("readability"))
                .andExpect(view().name("addOrUpdateUser"));

        assertIterableEquals(List.of(TestData.adminRole, TestData.userRole), roleService.findAll());
    }

    @Test
    public void testSaveWhenUserIsCorrect() throws Exception {
        when(roleService.findByName("user")).thenReturn(Role.builder().id(2).name("user").build());
        when(passwordEncoder.encode("user")).thenReturn("user");
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/save/")
                        .param("username", "user")
                        .param("password", "user")
                        .param("passwordAgain", "user")
                        .param("email", "user@gmail.com")
                        .param("firstName", "user")
                        .param("lastName", "user")
                        .param("birthday", "2003-03-03")
                        .param("role", "user")
                )
                .andExpect(view().name("redirect:/admin/"));
    }

    @Test
    public void testSaveWhenPasswordsIsNotSame() throws Exception {
        when(roleService.findByName("user")).thenReturn(Role.builder().id(2).name("user").build());
        when(passwordEncoder.encode("user")).thenReturn("user");
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/save/")
                        .param("username", "user")
                        .param("password", "user")
                        .param("passwordAgain", "userka")
                        .param("email", "user@gmail.com")
                        .param("firstName", "user")
                        .param("lastName", "user")
                        .param("birthday", "2003-03-03")
                        .param("role", "user")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/add"));
    }

    @Test
    public void testSaveWhenUsernameAlreadyExist() throws Exception {
        when(roleService.findByName("user")).thenReturn(Role.builder().id(2).name("user").build());
        when(userService.findByUsername("user")).thenReturn(TestData.user);
        when(passwordEncoder.encode("user")).thenReturn("user");
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/save/")
                        .param("username", "user")
                        .param("password", "user")
                        .param("passwordAgain", "user")
                        .param("email", "user@gmail.com")
                        .param("firstName", "user")
                        .param("lastName", "user")
                        .param("birthday", "2003-03-03")
                        .param("role", "user")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/add"));
    }

    @Test
    public void testSaveWhenEmailAlreadyExist() throws Exception {
        when(roleService.findByName("user")).thenReturn(Role.builder().id(2).name("user").build());
        when(userService.findByEmail("user@gmail.com")).thenReturn(TestData.user);
        when(passwordEncoder.encode("user")).thenReturn("user");
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/save/")
                        .param("username", "user")
                        .param("password", "user")
                        .param("passwordAgain", "user")
                        .param("email", "user@gmail.com")
                        .param("firstName", "user")
                        .param("lastName", "user")
                        .param("birthday", "2003-03-03")
                        .param("role", "user")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/add"));
    }

    @Test
    public void testUpdateWhenUserIsCorrect() throws Exception {
        when(roleService.findByName("user")).thenReturn(Role.builder().id(2).name("user").build());
        when(passwordEncoder.encode("user")).thenReturn("user");
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/")
                        .param("id", "2")
                        .param("username", "user")
                        .param("password", "user")
                        .param("passwordAgain", "user")
                        .param("email", "user@gmail.com")
                        .param("firstName", "user")
                        .param("lastName", "user")
                        .param("birthday", "2003-03-03")
                        .param("role", "user")
                )
                .andExpect(view().name("redirect:/admin/"));
    }

    @Test
    public void testUpdateWhenPasswordsIsNotSame() throws Exception {
        when(roleService.findByName("user")).thenReturn(Role.builder().id(2).name("user").build());
        when(passwordEncoder.encode("user")).thenReturn("user");
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/")
                        .param("id", "2")
                        .param("username", "user")
                        .param("password", "user")
                        .param("passwordAgain", "userka")
                        .param("email", "user@gmail.com")
                        .param("firstName", "user")
                        .param("lastName", "user")
                        .param("birthday", "2003-03-03")
                        .param("role", "user")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/addForUpdate"));
    }

    @Test
    public void testUpdateWhenEmailAlreadyExist() throws Exception {
        when(roleService.findByName("user")).thenReturn(Role.builder().id(2).name("user").build());
        when(userService.findByEmail("user@gmail.com")).thenReturn(TestData.user);
        when(passwordEncoder.encode("user")).thenReturn("user");
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/update/")
                        .param("id", "2")
                        .param("username", "user")
                        .param("password", "user")
                        .param("passwordAgain", "user")
                        .param("email", "user@gmail.com")
                        .param("firstName", "user")
                        .param("lastName", "user")
                        .param("birthday", "2003-03-03")
                        .param("role", "user")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/addForUpdate"));
    }

    interface TestData {
        User admin = User.builder()
                .id(1)
                .username("admin")
                .password("admin")
                .email("admin@gmail.com")
                .firstName("Admin")
                .lastName("Admin")
                .birthday(LocalDate.parse("2003-05-05"))
                .role(Role.builder().id(1).name("admin").build())
                .accountNonExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(false)
                .build();

        User user = User.builder()
                .id(2)
                .username("user")
                .password("user")
                .email("user@gmail.com")
                .firstName("User")
                .lastName("User")
                .birthday(LocalDate.parse("1991-05-05"))
                .role(Role.builder().id(2).name("user").build())
                .accountNonExpired(false)
                .accountNonLocked(false)
                .credentialsNonExpired(false)
                .enabled(false)
                .build();

        Role adminRole = Role.builder().id(1).name("admin").build();
        Role userRole = Role.builder().id(2).name("user").build();
    }
}
