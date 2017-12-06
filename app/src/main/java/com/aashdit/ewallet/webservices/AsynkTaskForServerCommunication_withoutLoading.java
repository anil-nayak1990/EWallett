package com.aashdit.ewallet.webservices;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.HashMap;

public class AsynkTaskForServerCommunication_withoutLoading extends
		AsyncTask<HashMap<String, String>, JSONObject, JSONObject> {
	Context _context;
	ServerLinkCommunication serverLinkCommunication = null;

	AsynkTaskCommunicationInterface asynkTaskCommunicationInterface;


	public AsynkTaskForServerCommunication_withoutLoading(Activity activity) {
		this._context = activity;

		asynkTaskCommunicationInterface = (AsynkTaskCommunicationInterface) activity;
		serverLinkCommunication = new ServerLinkCommunication(_context);
		
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		// pd.setTitle("Loading...");
		
	}

	@Override
	protected JSONObject doInBackground(HashMap<String, String>... params) {
		// TODO Auto-generated method stub

		JSONObject object = null;
		if (params.length > 1) {

			HashMap<String, String> hashMap = params[0];
			HashMap<String, String> hashMapLink = params[1];

//			if (hashMap
//					.containsKey(AllStaticVariable.servercommunication_variable.logo)) {
//				object = serverLinkCommunication.GetJsonfromServerForImage(
//						hashMap, hashMapLink);
//			} else {
				object = serverLinkCommunication.GetJsonfromServer(hashMap,
						hashMapLink);
//			}
		} 
		
		else {
			HashMap<String, String> hashMapLink = params[0];
			object = serverLinkCommunication
					.GetMethodeGetJsonfromServer(hashMapLink);
		}

		asynkTaskCommunicationInterface.doInBackgroundForComun(1);
		return object;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		
		if (result != null)
			asynkTaskCommunicationInterface.doPostExecuteForCommu(result);

	}

}