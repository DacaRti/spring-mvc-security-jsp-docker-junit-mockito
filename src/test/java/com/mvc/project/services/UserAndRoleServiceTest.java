package com.mvc.project.services;

import com.mvc.project.config.AppConfig;
import com.mvc.project.entity.Role;
import com.mvc.project.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author DacaP
 * @version 1.0
 * @create 11/10/2022 5:31 pm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserAndRoleServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void shouldReturnAllUsers() {
        assertEquals(List.of(TestData.admin, TestData.user), userService.findAll());
    }

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void shouldReturnUserById() {
        assertEquals(TestData.admin, userService.findById(1));
    }

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void shouldRemoveUserById() {
        userService.remove(1);
        assertEquals(1, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "users"));
    }

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void shouldCreateUser() {
        userService.create(TestData.Test);
        assertEquals(3, JdbcTestUtils.countRowsInTable(new JdbcTemplate(dataSource), "users"));
    }

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void shouldUpdateUser() {
        userService.update(TestData.userForUpdate);

        assertEquals(1,
                JdbcTestUtils.countRowsInTableWhere(new JdbcTemplate(dataSource),
                        "users", "username = 'testForUpdate'"));
    }

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void shouldReturnUserByEmail() {
        assertEquals(TestData.admin, userService.findByEmail("admin@gmail.com"));
    }

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    public void shouldReturnUserByUsername() {
        assertEquals(TestData.admin, userService.findByUsername("admin"));
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

        User Test = User.builder()
                .id(3)
                .username("Test")
                .password("Test")
                .email("Test@gmail.com")
                .role(Role.builder().id(2).name("user").build())
                .birthday(LocalDate.parse("1995-03-04"))
                .firstName("Test")
                .lastName("Test")
                .build();

        User userForUpdate = User.builder()
                .id(1)
                .username("testForUpdate")
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
    }
}
