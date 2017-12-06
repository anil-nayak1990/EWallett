package com.aashdit.ewallet.webservices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

public class AsynkTaskForServerCommunication extends AsyncTask<HashMap<String, String>, JSONObject, JSONObject> {
	Context _context;
	ServerLinkCommunication serverLinkCommunication = null;

	AsynkTaskCommunicationInterface asynkTaskCommunicationInterface;
	ProgressDialog pd = null;

	public AsynkTaskForServerCommunication(Activity activity) {
		this._context = activity;

		asynkTaskCommunicationInterface = (AsynkTaskCommunicationInterface) activity;
		serverLinkCommunication = new ServerLinkCommunication(_context);
		pd = new ProgressDialog(_context);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		// pd.setTitle("Loading...");
		pd.setMessage("Loading....Please wait");
			pd.show();
	}

	@Override
	protected JSONObject doInBackground(HashMap<String, String>... params) {
		// TODO Auto-generated method stub

		JSONObject object = null;
		if (params.length > 1) {

			HashMap<String, String> hashMap = params[0];
			HashMap<String, String> hashMapLink = params[1];

			if (hashMap.containsKey("filename")) {
				
            String path=hashMap.get("filename");
            
				File f = new File(path);

				 if(f.isFile()){

					 object = serverLinkCommunication.GetJsonfromServerFile(
								hashMap, hashMapLink);

				  }
				 else{
					 object = serverLinkCommunication.GetJsonfromServer(hashMap,hashMapLink);
				 }
				
				
				
			} 
			else if(hashMap.containsKey(AllStaticVariables.CreateMeeting.meeting_image)){
				
				String path=hashMap.get(AllStaticVariables.CreateMeeting.meeting_image);
				
				
				File f = new File(path);

				 if(f.isFile()){

					 object = serverLinkCommunication.GetJsonfromMeetingImage(
								hashMap, hashMapLink);

				  }
				 else{
					 object = serverLinkCommunication.GetJsonfromServer(hashMap,hashMapLink);
				 }
				
				
				
				
			}
			
			else {
				object = serverLinkCommunication.GetJsonfromServer(hashMap,hashMapLink);
			}
		} 
		
		else {
			HashMap<String, String> hashMapLink = params[0];
			object = serverLinkCommunication.GetMethodeGetJsonfromServer(hashMapLink);
		}

		asynkTaskCommunicationInterface.doInBackgroundForComun(1);
		return object;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		pd.dismiss();
		//if (result != null)
			asynkTaskCommunicationInterface.doPostExecuteForCommu(result);

	}

}