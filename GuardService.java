package com.cg.aps.service;

import java.util.List;

import com.cg.aps.entity.Guard;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
/**
 * 
 * @author Naga Vishnu
 * guard service layer interface
 *
 */
public interface GuardService {

	public Integer addGuard(Guard guard) throws DuplicateRecordException;
	public void updateGuard(Guard guard) throws RecordNotFoundException;
	public void deleteGuard(Guard guard) throws RecordNotFoundException;
	public List<Guard> findByGuardName(String name) throws RecordNotFoundException;
	public Guard findByPk(Integer id) throws RecordNotFoundException;
	public List<Guard> search(Integer pageNo, Integer pageSize) throws DatabaseException;
	public List<Guard> search() throws DatabaseException;
}
