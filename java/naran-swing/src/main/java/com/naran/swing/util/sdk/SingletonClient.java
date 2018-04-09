package com.naran.swing.util.sdk;

public class SingletonClient {
	
	private static Client client = null;

	private static final String SMS_SN = "emay_sms_sn";
	private static final String SMS_KEY = "emay_sms_key";
	
	private SingletonClient() {
	}

	public synchronized static Client getClient(String softwareSerialNo,
			String key) {
		if (client == null) {
			try {
				client = new Client(softwareSerialNo, key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	public synchronized static Client getClient() {
		if (client == null) {
			try {
				client = new Client ("6SDK-EMY-6688-JDVRK","399169");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}

}
