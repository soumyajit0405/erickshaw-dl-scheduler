package com.energytrade.repository;

import com.energytrade.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	
	@Query("select a from User a")
	List<User> findAll();
}