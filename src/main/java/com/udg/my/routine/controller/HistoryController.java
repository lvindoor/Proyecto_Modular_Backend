package com.udg.my.routine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udg.my.routine.model.History;
import com.udg.my.routine.model.Passenger;
import com.udg.my.routine.model.Station;
import com.udg.my.routine.pojo.Response;
import com.udg.my.routine.service.HistoryService;
import com.udg.my.routine.service.StationService;
import com.udg.my.routine.service.PassengerService;
import com.udg.my.routine.service.ResponseService;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

	@Autowired
	private HistoryService historyService;

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private StationService stationService;

	@Autowired
	private ResponseService responseService;

	@GetMapping
	public ResponseEntity<Response> getAll() {
		try {
			Object response = historyService.findAll();
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getById(@PathVariable Long id) {
		try {
			Object response = historyService.findById(id);
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/passenger/{passengerId}")
	public ResponseEntity<Response> findByPassengerId(@PathVariable Long passengerId) {
		try {
			History response = historyService.findByPassengerId(passengerId);

			if (response == null) {
				return new ResponseEntity<Response>(new Response(false, "Error ", null), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			return new ResponseEntity<Response>(new Response(true, "Success", Arrays.asList(response)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/completed-challenge")
	public ResponseEntity<Response> getAllWhoCompletedChallengeToday() {
		try {
			List<History> records = historyService.findAllWhoCompletedChallengeToday();

			if (records.size() == 0) {
				new Response(true, "There are not data currently", Arrays.asList());
			}

			return new ResponseEntity<Response>(new Response(true, "Success", records), HttpStatus.OK);
		} catch (Exception e) {
			return responseService.errorResponse("There was an error to get complete");
		}
	}

	@GetMapping("/challenge-completed/{passengerId}")
	public boolean hasPassengerCompletedChallengeToday(@PathVariable Long passengerId) {
		return historyService.hasCompletedChallenge(passengerId);
	}

	@PostMapping("/new/{passengerId}/{stationId}")
	public ResponseEntity<Response> create(@PathVariable Long passengerId, @PathVariable Long stationId,
			@RequestBody History history) {
		try {

			Optional<Passenger> passenger = this.passengerService.findById(passengerId);
			if (!passenger.isPresent()) {
				String message = Passenger.class.getSimpleName() + " with id: " + passengerId + " not found";
				return new ResponseEntity<Response>(new Response(false, "error", this.responseService.errors(message)),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			Optional<Station> station = this.stationService.findById(stationId);
			if (!station.isPresent()) {
				String message = Station.class.getSimpleName() + " with id: " + stationId + " not found";
				return new ResponseEntity<Response>(new Response(false, "error", this.responseService.errors(message)),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			history.setPassenger(passenger.get());
			history.setStation(station.get());

			Object response = historyService.save(history);
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<Response> update(@RequestBody History history) {
		try {
			Object response = historyService.save(history);
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error: " + e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id) {
		try {
			historyService.deleteById(id);
			return new ResponseEntity<Response>(new Response(true, "Success", null), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
