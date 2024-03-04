package com.udg.my.routine.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udg.my.routine.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Serializable> { }
