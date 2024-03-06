package com.udg.my.routine.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.udg.my.routine.model.Passenger;

public interface PassengerRepository extends CrudRepository<Passenger, Serializable> {

  @SuppressWarnings("null")
  List<Passenger> findAll();

  Optional<Passenger> findById(Long id);

  void deleteById(Long id);
  
  @SuppressWarnings({ "null", "unchecked" })
  Passenger save(Passenger passenger);

}
