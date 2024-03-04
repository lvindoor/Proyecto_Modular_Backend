package com.udg.my.routine.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udg.my.routine.model.Line;

public interface LineRepository extends JpaRepository<Line, Serializable> { }
