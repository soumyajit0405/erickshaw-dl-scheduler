package com.energytrade.repository;


import com.energytrade.model.UserDevices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllTimeslotRepository extends JpaRepository<UserDevices, Long>
{
	
}