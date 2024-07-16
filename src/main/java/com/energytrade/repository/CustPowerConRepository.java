package com.energytrade.repository;


import com.energytrade.model.CustomerPowerCon;
import com.energytrade.model.CustomerPowerCons;
import com.energytrade.model.UserDevices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustPowerConRepository extends JpaRepository<CustomerPowerCon, Long>
{

	@Query("select COALESCE(max(id),0) from  CustomerPowerCon a")
	int findMaxCount();
}