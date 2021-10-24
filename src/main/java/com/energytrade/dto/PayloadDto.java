package com.energytrade.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class PayloadDto {

	private ArrayList<String> device_ids ;
	
	private String duration;
	
	private int startDay;
	
	private HashMap<String,Object> filter_slot;
	
	public ArrayList<String> getDevice_ids() {
		return device_ids;
	}

	public void setDevice_ids(ArrayList<String> device_ids) {
		this.device_ids = device_ids;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getStartDay() {
		return startDay;
	}

	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}

	public HashMap<String, Object> getFilter_slot() {
		return filter_slot;
	}

	public void setFilter_slot(HashMap<String, Object> filter_slot) {
		this.filter_slot = filter_slot;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getGroup_by_minutes() {
		return group_by_minutes;
	}

	public void setGroup_by_minutes(int group_by_minutes) {
		this.group_by_minutes = group_by_minutes;
	}

	private int month;
	
	private int year;
	
	private int group_by_minutes;
	
}
