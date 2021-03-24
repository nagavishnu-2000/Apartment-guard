package com.cg.aps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.aps.entity.GuardShift;

/**
 * 
 * @author Naga Vishnu
 * guard shift repository
 *
 */
@Repository
public interface GuardShiftDAO extends JpaRepository<GuardShift,Integer>{

}
