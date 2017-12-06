package com.aashdit.ewallet.webservices;


import org.json.JSONObject;


public interface AsynkTaskCommunicationInterface {
	
	void doInBackgroundForComun(int progress);
	 void doPostExecuteForCommu(JSONObject jsonObject);
}