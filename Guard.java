package com.cg.aps.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Naga Vishnu
 * Guard's entity class and mapped by delivery, help, visitor and alerts
 *
 */
@Entity
@Table(name = "guards_aps")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Guard {
	@Id
	private Integer guardId;
	private String guardName;
	private Long mobileNo;
	private String emailId;
	@OneToMany(mappedBy = "guard")
	@JsonIgnore
	private Set<Delivery> deliverySet;
	@OneToMany(mappedBy = "guard")
	@JsonIgnore
	private Set<DomesticHelp> helpSet;
	@OneToMany(mappedBy = "guard")
	@JsonIgnore
	private Set<Visitor> visitorSet;
	@OneToMany(mappedBy = "guard")
	@JsonIgnore
	private Set<SecurityAlert> alertSet;
	@OneToMany(mappedBy = "guard")
	@JsonIgnore
	private Set<GuardShift> guardShiftSet;
	@OneToOne(mappedBy = "guard")
	@JsonIgnore
	private User user;
	
	/*
	 * public Guard(Integer guardId, String guardName, Long mobileNo, String
	 * emailId, User user) { super(); this.guardId = guardId; this.guardName =
	 * guardName; this.mobileNo = mobileNo; this.emailId = emailId; this.user =
	 * user; }
	 */
	
	
}
