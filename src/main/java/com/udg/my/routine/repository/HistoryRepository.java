package com.udg.my.routine.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udg.my.routine.model.History;

public interface HistoryRepository extends JpaRepository<History, Serializable> {

  List<History> findByPassengerIdAndDate(Long passengerId, Date date);

  List<History> findByChallengeCompletedAndDate(boolean challengeCompleted, Date date);

  History findByPassengerId(Long id);

}