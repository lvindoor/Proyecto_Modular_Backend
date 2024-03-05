package com.udg.my.routine.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true) // not visible on swagger
	private Long id;

	@Column
	@CreationTimestamp
	private Date date;

	@Column(length = 10)
	private String duration;
	
	@Column(length = 25)
	private String exercise;

	@Column(name = "repetitions_done")
	private int repetitionsDone;
	
	@Column(name = "target_reps")
	private int targetReps;
	
	@Column(name = "challenge_completed")
	private boolean challengeCompleted;

	@ManyToOne(optional = false)
	@JoinColumn(name = "passenger_id", nullable = false)
	@ApiModelProperty(hidden = true) // not visible on swagger
	private Passenger passenger;

	@ManyToOne(optional = false)
	@JoinColumn(name = "station_id", nullable = false)
	@ApiModelProperty(hidden = true) // not visible on swagger
	private Station station;

}
