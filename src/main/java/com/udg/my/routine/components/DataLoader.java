package com.udg.my.routine.components;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.udg.my.routine.model.Role;
import com.udg.my.routine.model.User;
import com.udg.my.routine.service.PasswordEncryptionService;
import com.udg.my.routine.service.RoleService;
import com.udg.my.routine.service.UserService;

@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  private PasswordEncryptionService passwordEncryptionService;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Override
  public void run(String... args) throws Exception {
    loadDefaultRole();
    loadDefaultUser();
  }

  private void loadDefaultUser() {
    String username = "jalejandro_garcia";
    String encryptedPassword = passwordEncryptionService.encryptPassword("Tormentax55.");

    User dbUser = userService.findByUsername(username);
    if( dbUser != null ) return;

    Role dbRole = roleService.findByName("ADMIN");

    User user = new User();
    if( dbRole != null ) {
      user.setRoles(Arrays.asList(dbRole));
    }

    user.setUsername(username);
    user.setPassword(encryptedPassword);
    user.setEmail("jesus.garcia1953@alumnos.udg.mx");
    user.setIsActive(true);
    user.setGoogle(false);
    userService.save(user);
  }

  private void loadDefaultRole() {
    String roleName = "ADMIN";
    Role dbRole = roleService.findByName(roleName);

    if( dbRole != null ) return;

    Role role = new Role();
    role.setName(roleName);
    role.setIsActive(true);
    role.setDescription("Role description");
    roleService.save(role);
  }

}
