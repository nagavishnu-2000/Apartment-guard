package com.cg.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.cg.aps.application.ApartmentSecurityAppApplication;
import com.cg.aps.entity.GuardShift;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.GuardShiftService;

@SpringBootTest(classes = ApartmentSecurityAppApplication.class)
@Transactional
@Rollback(true)
public class GuardShiftTest {

	@Autowired
	private GuardShiftService guardShiftService;
	
	public GuardShift addGuardShift() throws DuplicateRecordException, RecordNotFoundException {
		GuardShift guardShift = new GuardShift();
		guardShift.setGuardShift("Day");
		guardShift.setGuardDate(LocalDate.now());
		Integer id = guardShiftService.addGuardShift(guardShift);
		return guardShiftService.findByPk(id);
	} 
	
	/*
	 * check add guardShift
	 */
	@Test
	public void testAddGuardShift() throws DuplicateRecordException, RecordNotFoundException {
		GuardShift guardShift = addGuardShift();
		assertEquals("Day", guardShift.getGuardShift());
		assertNotEquals("xyz",guardShift.getGuardShift());
	}
	
	/*
	 * check edit guardShift
	 */
	@Test
	public void testEditGuardShift() throws DuplicateRecordException, RecordNotFoundException {
		GuardShift guardShift = addGuardShift();
		guardShift.setGuardShift("Night");
		guardShiftService.updateGuardShift(guardShift);
		assertEquals("Night",guardShiftService.findByPk(guardShift.getShiftId()).getGuardShift());
	}
	
	/*
	 * check delete guard
	 */
	@Test
	public void testDeleteGuardShift() throws DuplicateRecordException, RecordNotFoundException {
		GuardShift guardShift = addGuardShift();
		guardShiftService.deleteGuardShift(guardShift);
		assertThrows(RecordNotFoundException.class, ()->{
			guardShiftService.findByPk(guardShift.getShiftId());
			});
	}
}
