package com.energytrade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_devices")
@NamedQuery(name = "UserDevices.findAll", query = "SELECT a FROM UserDevices a")
public class UserDevices {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "device_id")
	private String deviceId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}
