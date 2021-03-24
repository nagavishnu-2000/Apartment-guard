package com.cg.aps.controller;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.cg.aps.entity.Delivery;
import com.cg.aps.entity.DomesticHelp;
import com.cg.aps.entity.GuardShift;
import com.cg.aps.entity.SecurityAlert;
import com.cg.aps.entity.Visitor;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.DeliveryService;
import com.cg.aps.service.DomesticHelpService;
import com.cg.aps.service.GuardShiftService;
import com.cg.aps.service.SecurityAlertService;
import com.cg.aps.service.VisitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @author Naga Vishnu
 *
 */
@Api
@RestController
@RequestMapping("/api/guard")
public class GuardController {
@Autowired
	private GuardShiftService guardShiftService;
	
	/**
	 * @author Naga Vishnu
	 * @param shiftId - id of the guard shift
	 * @return GuardShift
	 */
	@ApiOperation(value = "get guard shift by id",
			response = GuardShift.class,
			tags = "get-guardShift",
			consumes = "shiftId",
			httpMethod = "GET")
	@GetMapping("/guardShift/{shiftId}")
	public ResponseEntity<GuardShift> getGuardShiftById(@PathVariable Integer shiftId){
		try {
			GuardShift guardShift = guardShiftService.findByPk(shiftId);
			return new ResponseEntity<>(guardShift,HttpStatus.OK);
		}catch(RecordNotFoundException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Naga Vishnu
	 * @return all GuardShifts
	 */
	@ApiOperation(value = "get all guard shifts",
			response = GuardShift.class,
			tags = "get-all-guardShift",
			httpMethod = "GET")
	@GetMapping("/guardShift")
	public ResponseEntity<List<GuardShift>> getAllGuardShifts(){
		try {
			List<GuardShift> guardShiftList = guardShiftService.search();
			return new ResponseEntity<>(guardShiftList,HttpStatus.OK);
		}catch(DatabaseException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Naga Vishnu
	 * @param pageNo - page number
	 * @param pageSize - page size
	 * @return GuardShift of the pageNo with pageSize
	 */
	@ApiOperation(value = "get guard shift by page no and page size",
			response = GuardShift.class,
			tags = "get-guardShift",
			consumes = "page no and page size",
			httpMethod = "GET")
	@GetMapping("/guardShift/{pageNo}/{pageSize}")
	public ResponseEntity<List<GuardShift>> getGuardShifts(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		try {
			List<GuardShift> guardShiftList = guardShiftService.search(pageNo, pageSize);
			return new ResponseEntity<>(guardShiftList,HttpStatus.OK);
		}catch(DatabaseException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Naga Vishnu
	 * @param guardShift - shift object
	 * @return adds GuardShift
	 */
	@ApiOperation(value = "add guard shift",
			response = Integer.class,
			tags = "add-guardShift",
			consumes = "guardShift object",
			httpMethod = "POST")
	@PostMapping("/guardShift")
	public ResponseEntity<Integer> addGuardShift(@RequestBody GuardShift guardShift) {
		try {
			Integer guardShiftId = guardShiftService.addGuardShift(guardShift);
			return new ResponseEntity<>(guardShiftId,HttpStatus.OK);
		}catch(DuplicateRecordException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Naga Vishnu
	 * @param guardShift - shift object
	 * @return updating GuardShift
	 */
	@ApiOperation(value = "update guard shift",
			response = String.class,
			tags = "update-guardShift",
			consumes = "guardShift object",
			httpMethod = "PUT")
	@PutMapping("/guardShift")
	public ResponseEntity<String> updateGuardShift(@RequestBody GuardShift guardShift) {
		try {
			guardShiftService.updateGuardShift(guardShift);
			return new ResponseEntity<>("updated successfully",HttpStatus.OK);
		}catch(RecordNotFoundException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	/**
	 * @author Naga Vishnu
	 * @param guardShift - shift object
	 * @return deleting GuardShift
	 */
	@ApiOperation(value = "delete guard shift",
			response = String.class,
			tags = "delete-guardShift",
			consumes = "guardShift object",
			httpMethod = "DELETE")
	@DeleteMapping("/guardShift")
	public ResponseEntity<String> deleteGuardShift(@RequestBody GuardShift guardShift) {
		try {
			guardShiftService.deleteGuardShift(guardShift);
			return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
		}catch(RecordNotFoundException e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}