package com.udg.my.routine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udg.my.routine.model.Role;
import com.udg.my.routine.repository.RoleRepository;

@Service
public class RoleService {
  
  @Autowired
  private RoleRepository roleRepository;

  @Transactional(readOnly = true)
  public List<Role> findAll() {
    return this.roleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Role findById(Long id) {
    return this.roleRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public Role findByName(String name) {
    return this.roleRepository.findByName(name);
  }

  @Transactional
  public Role save(Role role) {
    return this.roleRepository.save(role);
  }

  @Transactional
  public void deleteById(Long id) {
    this.roleRepository.deleteById(id);
  }

}
