package com.cg.aps.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Naga Vishnu
 * guard shift entity
 *
 */
@Entity
@Table(name = "guard_shift_aps")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuardShift {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shiftId;
	private String guardShift;
	private LocalDate guardDate;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "guardId"),name = "guardId")
	@JsonIgnore
	private Guard guard;
}
