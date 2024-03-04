package com.udg.my.routine.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udg.my.routine.model.Member;

public interface UserRepository extends CrudRepository<Member, Serializable> {
  
  List<Member> findAll();

  Member findById(Long id);

  Member findByUsername(String username);

  void deleteById(Long id);

  @SuppressWarnings("unchecked")
  Member save(Member member);

}
