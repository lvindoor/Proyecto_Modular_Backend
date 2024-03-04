package com.udg.my.routine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udg.my.routine.model.Transport;
import com.udg.my.routine.repository.TransportRepository;

@Service
public class TransportService {

  @Autowired
  private TransportRepository repository;

  public List<Transport> findAll() {
    return this.repository.findAll();
  }

  public Optional<Transport> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  public Transport save(Transport transport) {
    return this.repository.save(transport);
  }

  @Transactional
  public void deleteById(Long id) {
    this.repository.deleteById(id);
  }

}
