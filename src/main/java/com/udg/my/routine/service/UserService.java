package com.udg.my.routine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udg.my.routine.model.Member;
import com.udg.my.routine.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  // @Secured("ROLE_ADMIN")
  @Transactional(readOnly = true)
  public List<Member> findAll() {
    return this.userRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Member findByUsername(String username) {
    return this.userRepository.findByUsername(username);
  }

  @Transactional(readOnly = true)
  public Member findById(Long id) {
    return this.userRepository.findById(id);
  }

  @Transactional
  public Member save(Member member) {
    return this.userRepository.save(member);
  }

  @Transactional
  public void deleteById(Long id) {
    this.userRepository.deleteById(id);
  }

}
