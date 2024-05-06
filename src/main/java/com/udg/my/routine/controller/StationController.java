package com.udg.my.routine.controller;

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

import com.udg.my.routine.model.Station;
import com.udg.my.routine.pojo.Response;
import com.udg.my.routine.service.StationService;

@RestController
@RequestMapping("/api/station")
public class StationController {
	
	@Autowired
	private StationService stationService;

	@GetMapping
	public ResponseEntity<Response> getAll() {
		try {
			Object response = stationService.findAll();
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> getById(@PathVariable Long id) {
		try {
			Object response = stationService.findById(id);
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Response> create(@RequestBody Station station) {
		try {
			Object response = stationService.save(station);
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<Response> update(@RequestBody Station station) {
		try {
			Object response = stationService.save(station);
			return new ResponseEntity<Response>(new Response(true, "Success", response), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error: " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id) {
		try {							
			stationService.deleteById(id);			
			return new ResponseEntity<Response>(new Response(true, "Success", null), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Response>(new Response(false, "Error " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
