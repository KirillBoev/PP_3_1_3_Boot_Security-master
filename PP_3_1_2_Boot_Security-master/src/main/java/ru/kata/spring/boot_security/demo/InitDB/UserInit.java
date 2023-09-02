package ru.kata.spring.boot_security.demo.InitDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserInit {
    private UserService userService;
    private RoleRepository roleRepository;


    @Autowired
    public UserInit(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void loadTestUsers() {
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        roles.add(userRole);
        User userAdmin = new User("admin", "admin", "dasda", 28L);
        userAdmin.setRoles(userAdmin.getRoles());
        userAdmin.setRoles(roles);
        userService.save(userAdmin);

        roles.clear();
        User user1 = new User("user", "user", "sdada", 25L);
        roles.add(userRole);
        user1.setRoles(roles);
        userService.save(user1);
    }
}
