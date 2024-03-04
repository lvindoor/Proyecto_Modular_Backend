package com.udg.my.routine.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udg.my.routine.model.Station;

public interface StationRepository extends JpaRepository<Station, Serializable> { }
