package com.energytrade.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.energytrade.dto.KiotRequest;
import com.energytrade.dto.PayloadDto;

@Component
public class ConnectorUtil {
	
	@Autowired
	RestTemplate restTemplate;

	public ArrayList<JSONObject> connectToKiot(String url, JSONObject kiotRequest, String bearerToken ) {
		
		ArrayList<JSONObject> listOfObjects = new ArrayList<>();
		JSONObject jsonObject1 = null;
		JSONObject js2= new JSONObject();
		try {
		HashMap<String,String> map = new HashMap<>();
		JSONArray jsonObject = null;
		System.out.println("Start POST Request " );
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "Bearer "+bearerToken);
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		System.out.println(kiotRequest);
		os.write(kiotRequest.toString().getBytes());	
		os.flush();
		os.close();
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				JSONArray arr = new JSONArray(response.toString());
				jsonObject1 = new JSONObject();
				jsonObject1.put("data",arr);
			if (responseCode != 200) {
				js2.put("error", true);
			} else {
				js2.put("error", false);
			}
				in.close();
				con.disconnect();
				listOfObjects.add(jsonObject1);
				listOfObjects.add(js2);
				// print result
			//	System.out.println(response.toString());
			} 
			
		else {
			js2.put("error", true);
			listOfObjects.add(js2);
				System.out.println("POST request not worked");
			}
			
		
		
	}
		catch(Exception e) {
			System.out.println(e.getMessage());
			js2.put("error", true);
			listOfObjects.add(js2);
			return listOfObjects;
			
		}
		return listOfObjects;
	}

}