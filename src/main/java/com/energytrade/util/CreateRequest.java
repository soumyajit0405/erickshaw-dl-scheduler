package com.energytrade.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.energytrade.dto.KiotRequest;
import com.energytrade.dto.PayloadDto;

@Component
public class CreateRequest {

	public final String INTENT = "action.entities.ENERGY_DATA";
	public final String DAY = "day";
	public final int GROUP_BY_MINS = 15;
	
	public JSONObject createKiotRequest(String deviceId, Date dnew ) {
		final long HOUR = 3600 * 1000; // in milli-seconds.
		final long HALFHOUR = 1800 * 1000;
		final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	//	Date dnew=new Date(new Date().getTime() );
		//Date dnew=new Date(new Date().getTime() +5*HOUR+HALFHOUR- 30*ONE_MINUTE_IN_MILLIS);
		  int day = dnew.getDate();
			int year = dnew.getYear() + 1900;
			int month = dnew.getMonth() + 1;
	        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
	       //SimpleDateFormat dt1 = new SimpleDateFormat("2018-03-06");
	        System.out.println(dt1.format(dnew));
	        SimpleDateFormat dt2 = new SimpleDateFormat("HH:mm");
	        String datenew = dt1.format(dnew);
	        String timenew = dt2.format(dnew);
	        String startTime = datenew +" "+timenew;
	        String[] values = startTime.split("-");
	        String[] dateTime =values[2].split(" "); 
	        String[] time = dateTime[1].split(":");
		
		//Creating Request
		ArrayList<String> deviceList = new ArrayList<>();
		deviceList.add(deviceId);
//		KiotRequest kiotRequest = new KiotRequest();
//		kiotRequest.setIntent(INTENT);
//		
//		
//		PayloadDto payloadDto = new PayloadDto();
//		payloadDto.setDuration(DAY);
//		payloadDto.setDevice_ids(deviceList);
//		payloadDto.setStartDay(day);
//		payloadDto.setGroup_by_minutes(GROUP_BY_MINS);
//		payloadDto.setMonth(month);
//		payloadDto.setYear(year);
//		
//		HashMap<String,Object> filterSlot = new HashMap<>();
//		filterSlot.put("hour", Integer.parseInt(time[0]));
//		filterSlot.put("minute", Integer.parseInt(time[1]));
//		payloadDto.setFilter_slot(filterSlot);
//		kiotRequest.setPayload(payloadDto);
		
		JSONObject input = new JSONObject();
		JSONObject payload = new JSONObject();
		JSONObject filterSlots = new JSONObject();
		filterSlots.put("hour", Integer.parseInt(time[0]));
		filterSlots.put("minute", Integer.parseInt(time[1]));
		payload.put("duration", DAY);
		payload.put("group_by_minutes", GROUP_BY_MINS);
		payload.put("filter_slot", filterSlots);
		payload.put("year", year);
		payload.put("month", month);
		payload.put("startDay", day);
		payload.put("device_ids", deviceList);
		input.put("payload", payload);
		input.put("intent", INTENT);
		
		System.out.println(input);
		return input;
	}
}
