package com.energytrade.jobs;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.energytrade.dto.KiotRequest;
import com.energytrade.enums.TimeSlots;
import com.energytrade.model.AllTimeslot;
import com.energytrade.model.CustomerPowerCon;
import com.energytrade.model.CustomerPowerCons;
import com.energytrade.model.User;
import com.energytrade.model.UserDevices;
import com.energytrade.repository.CustPowerConRepository;
import com.energytrade.repository.CustPowerConsRepository;
import com.energytrade.repository.UserDeviceRepository;
import com.energytrade.repository.UserRepository;
import com.energytrade.util.ConnectorUtil;
import com.energytrade.util.CreateRequest;

@Service
@Transactional
@Repository
public class RetriveJob {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserDeviceRepository userDeviceRepo;

//		@Autowired
//		CustPowerConsRepository powerrepo;

	@Autowired
	CustPowerConRepository powerrepo;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CreateRequest createRequest;

	@Autowired
	ConnectorUtil connectorUtil;

	@Value("${kiot.url}")
	private String kiotUrl;

	@Value("${time.period}")
	private int timePeriod;

	@Scheduled(cron = "${cron_5_mins}")
	public void cronJobSch() {

		System.out.println("Job Started at " + new Date(System.currentTimeMillis()));

		try {
			initiateJob();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

	private void initiateJob() throws ParseException {
		final long HOUR = 3600 * 1000; // in milli-seconds.
		final long HALFHOUR = 1800 * 1000;
		final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
		Date startDate = new Date(new Date().getTime() - timePeriod * ONE_MINUTE_IN_MILLIS);
		long startTime = startDate.getTime();

		Date endDate = new Date(new Date().getTime());
		long endTime = endDate.getTime();

		Date startDateToSave = new Date(new Date().getTime()+5*HOUR+HALFHOUR - timePeriod * ONE_MINUTE_IN_MILLIS);
		

		Date endDateToSave = new Date(new Date().getTime()+5*HOUR+HALFHOUR);
//			Date startDate=new Date(new Date().getTime()+5*HOUR+HALFHOUR - timePeriod*ONE_MINUTE_IN_MILLIS);
//			long startTime = startDate.getTime();
//			
//			Date endDate=new Date(new Date().getTime()+5*HOUR+HALFHOUR );
//			long endTime = endDate.getTime();
			System.out.println("Date From " + startDate +"      Date To "+endDate);
			System.out.println("Ts From " + startTime +"      Ts To "+endTime);
		// Date dnew=new Date(new Date().getTime()
		// +5*HOUR+HALFHOUR-15*ONE_MINUTE_IN_MILLIS);
		List<User> users = userRepo.findAll();

		for (User user : users) {
			List<UserDevices> userDevices = userDeviceRepo.findByUser(user.getUserId());
			System.out.println(userDevices.size());
			for (UserDevices userdevice : userDevices) {
				// JSONObject kiotRequest=
				// createRequest.createKiotRequest(userdevice.getDeviceId(),dnew);
				JSONObject kiotRequest = createRequest.createNewKiotRequest(userdevice.getDeviceId(), startTime,
						endTime);
				ArrayList<JSONObject> response = connectorUtil.connectToKiot(kiotUrl, kiotRequest,
						user.getBearerToken());
				System.out.println(response);
				// saveData(response, dnew, user, userdevice);
				save(response, startDateToSave, endDateToSave, user, userdevice);
			}
		}
	}

//	   private void saveData(ArrayList<JSONObject> response, Date date, User user, UserDevices userDevice) throws ParseException {
//		   
//				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
//				String newDate = dt1.format(date);
//				if (response.size() > 1 && !(boolean)response.get(1).get("error")) {
//					
//					JSONArray jsArr = (JSONArray)response.get(0).get("data");
//					for (int i =0;i<jsArr.length();i++) {
//						JSONObject jso= (JSONObject) jsArr.get(i);
//						JSONObject js1 = (JSONObject)jso.get("_id");
//						int energy =(int)(jso.get("avg_energy"));
//						String hour = Integer.toString((int)js1.get("hour"));
//						String mins = Integer.toString((int)js1.get("minute"));
//						if (mins.equalsIgnoreCase("0")) {
//							mins = mins.concat("0");
//						} if (hour.length() <= 1)
//						{
//							hour = "0"+hour; 
//						}
//							
//						String value = TimeSlots.valueOf("_"+hour+mins).getTimeSlot();
//						
//						AllTimeslot allTimeSlot = new AllTimeslot();
//						allTimeSlot.setTimeSlotId(Integer.parseInt(value));
//						
//						CustomerPowerCons powercons = new CustomerPowerCons();
//						powercons.setAllTimeSlot(allTimeSlot);
//						powercons.setPowerConsumed(energy);
//						powercons.setDate(dt1.parse(newDate));
//						powercons.setUser(user);
//						powercons.setUserDevice(userDevice);
//						powerrepo.save(powercons);
//					}
//				}
//			
//	   }

	private void save(ArrayList<JSONObject> response, Date startDate, Date endDate, User user, UserDevices userDevice)
			throws ParseException {

		SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sDate = dt1.format(startDate);
		String eDate = dt1.format(endDate);
		if (response.size() > 1 && !(boolean) response.get(1).get("error")) {

			JSONArray jsArr = (JSONArray) response.get(0).get("data");
			for (int i = 0; i < jsArr.length(); i++) {
				JSONObject jso = (JSONObject) jsArr.get(i);
			//	JSONObject js1 = (JSONObject) jso.get("_id");
				int energy = (int) (jso.get("avg_energy"));
//				String hour = Integer.toString((int) js1.get("hour"));
//				String mins = Integer.toString((int) js1.get("minute"));
//				if (mins.equalsIgnoreCase("0")) {
//					mins = mins.concat("0");
//				}
//				if (hour.length() <= 1) {
//					hour = "0" + hour;
//				}
//
//				String value = TimeSlots.valueOf("_" + hour + mins).getTimeSlot();
//
//				AllTimeslot allTimeSlot = new AllTimeslot();
//				allTimeSlot.setTimeSlotId(Integer.parseInt(value));

				int count = powerrepo.findMaxCount()+1;
				CustomerPowerCon powercons = new CustomerPowerCon();
				powercons.setId(count);
				powercons.setPowerConsumed(energy);
				powercons.setStartTs(dt1.parse(sDate));
				powercons.setEndTs(dt1.parse(eDate));
				powercons.setUser(user);
				powercons.setUserDevice(userDevice);
				powerrepo.saveAndFlush(powercons);
				System.out.println();
			}
		}

	}

}
