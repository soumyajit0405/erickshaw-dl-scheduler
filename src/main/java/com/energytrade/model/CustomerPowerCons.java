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
@Table(name = "customer_power_consumption")
@NamedQuery(name = "CustomerPowerCons.findAll", query = "SELECT a FROM CustomerPowerCons a")
public class CustomerPowerCons {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private UserDevices userDevice;
	
	@ManyToOne
	@JoinColumn(name = "timeslot_id")
	private AllTimeslot allTimeSlot;

	@Column(name = "power_consumed")
	private double powerConsumed;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	
	@Column(name="date")
	private Date date;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sync_ts")
	private Date syncTs;

	@Column(name="updated_by")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Date updatedTs;
	
	
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


	public UserDevices getUserDevice() {
		return userDevice;
	}


	public void setUserDevice(UserDevices userDevice) {
		this.userDevice = userDevice;
	}


	public AllTimeslot getAllTimeSlot() {
		return allTimeSlot;
	}


	public void setAllTimeSlot(AllTimeslot allTimeSlot) {
		this.allTimeSlot = allTimeSlot;
	}


	public double getPowerConsumed() {
		return powerConsumed;
	}


	public void setPowerConsumed(double powerConsumed) {
		this.powerConsumed = powerConsumed;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedTs() {
		return createdTs;
	}


	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Date getSyncTs() {
		return syncTs;
	}


	public void setSyncTs(Date syncTs) {
		this.syncTs = syncTs;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getUpdatedTs() {
		return updatedTs;
	}


	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}


	public byte getSoftdeleteflag() {
		return softdeleteflag;
	}


	public void setSoftdeleteflag(byte softdeleteflag) {
		this.softdeleteflag = softdeleteflag;
	}


	private byte softdeleteflag;

}
