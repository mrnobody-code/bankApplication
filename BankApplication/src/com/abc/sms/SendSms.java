package com.abc.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class SendSms {
	public static void sendSms(String message, String number)  {
		String apiKey = "gjq6McnN0Ab21lT53ia4VOhsxJZYBkevo8uX7twpUSEmKIDGFCD6m23CyBItcMWVafQNlFqwgouhpnZs";
		String senderId = "FSTSMS";
		
		try {
			//Encoding message
			message = URLEncoder.encode(message, "UTF-8");
			
			String language = "english";
			String route = "p"; //promotional sms p transaction sms t
			
			String myUrl = " https://www.fast2sms.com/dev/bulk?authorization="+apiKey+
					"&sender_id="+senderId+"&message="+message+"&language="+language+"&route="+route+
					"&numbers="+number;
			
			//Sending get request using java.
			URL url = new URL(myUrl);
			
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			System.out.println("Sending sms...");
			int code = con.getResponseCode();
			
			System.out.println("Response code :"+code);
			
			StringBuffer response = new StringBuffer();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				response.append(line);
			}
			System.out.println(response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
