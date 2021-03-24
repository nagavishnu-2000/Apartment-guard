package com.cg.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.cg.aps.application.ApartmentSecurityAppApplication;
import com.cg.aps.entity.Guard;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.GuardService;

@SpringBootTest(classes = ApartmentSecurityAppApplication.class)
@Transactional
@Rollback(true)
public class GuardTest {

	@Autowired
	private GuardService guardService;
	
	public Guard addGuard() throws DuplicateRecordException, RecordNotFoundException {
		Guard guard = new Guard();
		guard.setGuardId(1001);
		guard.setGuardName("Anna");
		guard.setMobileNo(9876543210L);
		guard.setEmailId("anna@anna.com");
		Integer id = guardService.addGuard(guard);
		return guardService.findByPk(id);
	} 
	
	/*
	 * check add guard
	 */
	@Test
	public void testAddGuard() throws DuplicateRecordException, RecordNotFoundException {
		Guard guard = addGuard();
		assertEquals(1001, guard.getGuardId());
		assertNotEquals("xyz",guard.getGuardName());
		assertEquals(9876543210L,guard.getMobileNo());
		assertNotEquals("anna@gmail.com",guard.getEmailId());
	}
	
	/*
	 * check edit guard
	 */
	@Test
	public void testEditGuard() throws DuplicateRecordException, RecordNotFoundException {
		Guard guard = addGuard();
		guard.setGuardName("Das");
		guard.setMobileNo(9012345678L);
		guardService.updateGuard(guard);
		assertNotEquals("Anna",guardService.findByPk(guard.getGuardId()).getGuardName());
		assertEquals(9012345678L,guardService.findByPk(guard.getGuardId()).getMobileNo());
	}
	
	/*
	 * check delete guard
	 */
	@Test
	public void testDeleteGuard() throws DuplicateRecordException, RecordNotFoundException {
		Guard guard = addGuard();
		guardService.deleteGuard(guard);
		assertThrows(RecordNotFoundException.class, ()->{
			guardService.findByPk(guard.getGuardId());
			});
	}
}
