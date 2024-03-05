package com.udg.my.routine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udg.my.routine.model.Line;
import com.udg.my.routine.repository.LineRepository;

@Service
public class LineService {

  @Autowired
  private LineRepository repository;

  public List<Line> findAll() {
    return this.repository.findAll();
  }

  public Optional<Line> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  public Line save(Line line) {
    return this.repository.save(line);
  }

  @Transactional
  public void deleteById(Long id) {
    this.repository.deleteById(id);
  }

}
