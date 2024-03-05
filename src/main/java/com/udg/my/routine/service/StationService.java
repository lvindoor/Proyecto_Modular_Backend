package com.udg.my.routine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udg.my.routine.model.Station;
import com.udg.my.routine.repository.StationRepository;

@Service
public class StationService {

  @Autowired
  private StationRepository repository;

  public List<Station> findAll() {
    return this.repository.findAll();
  }

  public Optional<Station> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  public Station save(Station station) {
    return this.repository.save(station);
  }

  @Transactional
  public void deleteById(Long id) {
    this.repository.deleteById(id);
  }

}
