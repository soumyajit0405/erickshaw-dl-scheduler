package com.energytrade.repository;



import com.energytrade.model.UserDevices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevices, Long>
{
	
	@Query("select a from UserDevices a where a.user.id =?1")
	List<UserDevices> findByUser(int userId);
}