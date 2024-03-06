package com.udg.my.routine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udg.my.routine.model.Passenger;
import com.udg.my.routine.repository.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	private PassengerRepository repository;

	@Transactional(readOnly = true)
	public List<Passenger> findAll() {
		return this.repository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Passenger> findById(Long id) {
		return repository.findById(id);
	}

	@Transactional
	public Passenger save(Passenger passenger) {
		return this.repository.save(passenger);
	}

	@Transactional
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

}
