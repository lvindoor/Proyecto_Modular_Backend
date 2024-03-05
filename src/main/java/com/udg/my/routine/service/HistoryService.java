package com.udg.my.routine.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udg.my.routine.model.History;
import com.udg.my.routine.repository.HistoryRepository;


@Service
public class HistoryService {

	@Autowired
	private HistoryRepository repository;
	
	public List<History> findAll() {
		return this.repository.findAll();
	}

	public Optional<History> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public History findByPassengerId(Long id) {
        return this.repository.findByPassengerId(id);
    }
	
	public List<History> findAllWhoCompletedChallengeToday() {
		
        LocalDate date = LocalDate.now();
        Date today = java.sql.Date.valueOf(date); 

        return repository.findByChallengeCompletedAndDate(true, today);
    }
	
	public boolean hasCompletedChallenge(Long passengerId) {
		
        LocalDate date = LocalDate.now();
        Date today = java.sql.Date.valueOf(date); 

        List<History> todaysRecords = repository.findByPassengerIdAndDate(passengerId, today);

        for (History record : todaysRecords) {
            if (record.isChallengeCompleted()) {
                return true; // Reto completado si algún registro tiene el reto como completado
            }
        }
        return false; // Reto no completado si ningún registro del día cumple el criterio
    }
	
	@Transactional
	public History save(History exerciseRecord) {
		return this.repository.save(exerciseRecord);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

}
