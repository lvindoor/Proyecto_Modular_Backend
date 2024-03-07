package com.udg.my.routine.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.udg.my.routine.model.Transport;

public interface TransportRepository extends CrudRepository<Transport, Serializable> {

  @SuppressWarnings("null")
  List<Transport> findAll();

  Optional<Transport> findById(Long id);
    
  Transport findByName(String name);

  void deleteById(Long id);

  @SuppressWarnings({ "unchecked", "null" })
  Transport save(Transport transport);

}
