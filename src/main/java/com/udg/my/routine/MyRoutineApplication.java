package com.udg.my.routine;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MyRoutineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRoutineApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<?> respomseHome() {
		Map<String, Object> response = new HashMap<>();
		response.put("msg", "server is running");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
