package com.udg.my.routine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udg.my.routine.model.Role;
import com.udg.my.routine.model.Member;
import com.udg.my.routine.pojo.Response;
import com.udg.my.routine.service.PasswordEncryptionService;
import com.udg.my.routine.service.ResponseService;
import com.udg.my.routine.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private PasswordEncryptionService passwordEncryptionService;

  @Autowired
  private UserService userService;

  @Autowired
  private ResponseService resService;

  @GetMapping
  public ResponseEntity<Response> getUsers() {
    try {
      List<Member> members = this.userService.findAll();
      return new ResponseEntity<Response>(new Response(true, "success", members), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/username/{username}")
  public ResponseEntity<Response> getUserByUsername(@PathVariable String username) {
    try {
      Member dbUser = this.userService.findByUsername( username.trim() );

      if( dbUser == null ) {
        String message = Member.class.getSimpleName() + " with username: " + username + " not found";
        return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(message)), HttpStatus.INTERNAL_SERVER_ERROR);
      }

      return new ResponseEntity<Response>(new Response(true, "success", dbUser), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/user/role/{userId}")
  public ResponseEntity<Response> getRolesByUserId(@PathVariable Long userId) {
    try {
      Member dbUser = this.userService.findById(userId);
      if( dbUser == null ) {
        String message = Member.class.getSimpleName() + " with id: " + userId + " not found";
        return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(message)), HttpStatus.INTERNAL_SERVER_ERROR);
      }

      List<Role> roles = dbUser.getRoles();

      return new ResponseEntity<Response>(new Response(true, "success", roles), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response> getUserById(@PathVariable Long id) {
    try {
      Member dbUser = this.userService.findById( id );

      if(dbUser == null) {
        String message = Member.class.getSimpleName() + " with id: " + id + " not found";
        return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(message)), HttpStatus.INTERNAL_SERVER_ERROR);
      }

      return new ResponseEntity<Response>(new Response(true, "success", dbUser), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<Response> createUser(@RequestBody Member member) {
    try {
      String encryptedPassword = passwordEncryptionService.encryptPassword(member.getPassword());
      member.setPassword(encryptedPassword);
      
      Member userUpdated = this.userService.save(member);
      return new ResponseEntity<Response>(new Response(true, "created", userUpdated), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id ,@RequestBody Member member) {
    try {

      //Validate if exist user
      Member dbUser = this.userService.findById(id);
      if(dbUser == null) {
        String message = Member.class.getSimpleName() + " with id: " + id + " not found";
        return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(message)), HttpStatus.INTERNAL_SERVER_ERROR);
      }

      // Update user
      dbUser.setUsername(member.getUsername() );
      dbUser.setEmail( member.getEmail());
      dbUser.setGoogle( member.getGoogle());
      dbUser.setIsActive( member.getIsActive());
      dbUser.setRoles( member.getRoles() );

      Member userUpdated = this.userService.save(dbUser);
      return new ResponseEntity<Response>(new Response(true, "updated", userUpdated), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response> deleteUserById(@PathVariable Long id) {
    try {
      Member dbUser = this.userService.findById( id );
      
      if(dbUser == null) {
        String message = Member.class.getSimpleName() + " with id: " + id + " not found";
        return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(message)), HttpStatus.INTERNAL_SERVER_ERROR);
      }

      this.userService.deleteById(id);
      return new ResponseEntity<Response>(new Response(true, "deleted", dbUser), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Response>(new Response(false, "error", this.resService.errors(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
