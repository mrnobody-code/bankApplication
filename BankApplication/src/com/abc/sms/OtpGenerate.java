package com.abc.sms;

import java.util.Random;

public class OtpGenerate {
	public static int otpGenerate() {
		Random r = new Random();
		int no = r.nextInt(1000);
		int otp = no*6;
		return otp;
	}

}
