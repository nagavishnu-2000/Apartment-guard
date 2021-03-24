package com.cg.aps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.aps.entity.Guard;
/**
 * 
 * @author Naga Vishnu
 * guard's repository
 *
 */
@Repository
public interface GuardDAO extends JpaRepository<Guard,Integer> {

	@Query("select g from Guard g where g.guardName = :name")
	public List<Guard> findByGuardName(String name) throws Exception;
}
