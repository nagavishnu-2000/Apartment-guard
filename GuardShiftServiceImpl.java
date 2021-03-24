package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.aps.dao.GuardShiftDAO;
import com.cg.aps.entity.GuardShift;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;

/**
 * 
 * @author Naga Vishnu
 * guard shift service implementation class
 *
 */
@Service
@Transactional
public class GuardShiftServiceImpl implements GuardShiftService{

	@Autowired
	private GuardShiftDAO guardShiftDao;
	
	
	@Override
	public Integer addGuardShift(GuardShift guardShift) throws DuplicateRecordException {
		try {	
			guardShiftDao.save(guardShift);
			return guardShift.getShiftId();
		}catch(DataAccessException e) {
			throw new DuplicateRecordException(e.getMessage());
		}catch(Exception e) {
			throw new DuplicateRecordException(e.getMessage());
		}
	}

	@Override
	public void updateGuardShift(GuardShift guardShift) throws RecordNotFoundException {
		try {			
			guardShiftDao.save(guardShift);
		}catch(DataAccessException e) {
			throw new RecordNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new RecordNotFoundException(e.getMessage());
		}
	}

	@Override
	public void deleteGuardShift(GuardShift guardShift) throws RecordNotFoundException {
		try {			
			guardShiftDao.delete(guardShift);
		}catch(DataAccessException e) {
			throw new RecordNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new RecordNotFoundException(e.getMessage());
		}
	}

	@Override
	public GuardShift findByPk(Integer id) throws RecordNotFoundException {
		try {			
			Optional<GuardShift> optional = guardShiftDao.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new Exception("Invalid id");
			}
		}catch(DataAccessException e) {
			throw new RecordNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new RecordNotFoundException(e.getMessage());
		}
	}

	@Override
	public List<GuardShift> search(Integer pageNo, Integer pageSize) throws DatabaseException {
		try {	
			PageRequest paging = PageRequest.of(pageNo, pageSize);
			Page<GuardShift> pagedResult = guardShiftDao.findAll(paging);
			if(pagedResult.hasContent()) {
				return pagedResult.getContent();
			}else {
				throw new Exception("Invalid pageNo and pageSize");
			}
		}catch(DataAccessException e) {
			throw new DatabaseException(e.getMessage());
		}catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public List<GuardShift> search() throws DatabaseException {
		try {			
			return guardShiftDao.findAll();
		}catch(DataAccessException e) {
			throw new DatabaseException(e.getMessage());
		}catch(Exception e) {
			throw new DatabaseException(e.getMessage());
		
		}
	}

}
