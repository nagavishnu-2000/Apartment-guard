package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entity.GuardShift;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;

/**
 * 
 * @author Naga Vishnu
 * service layer interface for guard shifts
 *
 */
public interface GuardShiftService {

	public Integer addGuardShift(GuardShift guardShift) throws DuplicateRecordException;
	public void updateGuardShift(GuardShift guardShift) throws RecordNotFoundException;
	public void deleteGuardShift(GuardShift guardShift) throws RecordNotFoundException;
	public GuardShift findByPk(Integer id) throws RecordNotFoundException;
	public List<GuardShift> search(Integer pageNo, Integer pageSize) throws DatabaseException;
	public List<GuardShift> search() throws DatabaseException;
}
