package com.aashdit.ewallet.webservices;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ServerLinkCommunication {
	@SuppressWarnings("unused")
	private Context _context;

	public ServerLinkCommunication(Context context) {
		this._context = context;
	}

	public JSONObject GetJsonfromServer(HashMap<String, String> hashMap,
			HashMap<String, String> hashMapLink) {

		List<NameValuePair> nameValuePairs = null;
		nameValuePairs = new ArrayList<NameValuePair>();

		Set<String> keyset = hashMap.keySet();

		for (String string : keyset) {

			String keyValue = hashMap.get(string);
			nameValuePairs.add(new BasicNameValuePair(string, keyValue));

		}

		JSONObject jsonObj = null;

	//	HashMap<String, String> flyerlist = new HashMap<String, String>();

		try {
			HttpClient httpclient = new DefaultHttpClient();
			String URL = hashMapLink.get("LINK");
			HttpPost httppost = new HttpPost(URL);

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
					HTTP.UTF_8));

			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					nameValuePairs);

			httppost.setEntity(formEntity);

			HttpResponse response = httpclient.execute(httppost);
			// HttpEntity entity = response.getEntity();
			int status = response.getStatusLine().getStatusCode();
			Log.i(AllStaticVariables.TAG, status + "");
			// Convert the response into a String
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {
				 String tt = EntityUtils.toString(resEntity, HTTP.UTF_8);
				//Log.i(AllStaticVariables.TAG, tt);
				jsonObj = new JSONObject(tt);
				//Log.i(AllStaticVariables.TAG, response.toString());
			}

		} catch (Exception e) {
			Log.e(AllStaticVariables.TAG,
					"Error in http connection " + e.toString());
			return jsonObj;
		}

		return jsonObj;

	}

	
	
	
	//////////////////////////////////////////
	public JSONObject GetJsonfromServerFile(
			   HashMap<String, String> hashMap, HashMap<String, String> hashMapLink) {

			  JSONObject jsoObj = null;

			  try {
			   String URL = hashMapLink.get("LINK");
			   URL url = new URL(URL);

			   HttpURLConnection conn = null;
			   DataOutputStream dos = null;
			   String lineEnd = "\r\n";
			   String twoHyphens = "--";
			   String boundary = "*****";
			   int bytesRead, bytesAvailable, bufferSize;
			   byte[] buffer;
			   int maxBufferSize = 1 * 1024 * 1024;

			   // Open a HTTP connection to the URL
			   conn = (HttpURLConnection) url.openConnection();
			   conn.setDoInput(true); // Allow Inputs
			   conn.setDoOutput(true); // Allow Outputs
			   conn.setUseCaches(true); // Don't use a Cached Copy
			   conn.setRequestMethod("POST");
			   conn.setRequestProperty("Connection", "Keep-Alive");
			   conn.setRequestProperty("ENCTYPE", "multipart/form-data");
			   conn.setRequestProperty("Content-Type",
			     "multipart/form-data;boundary=" + boundary);

			   if (hashMap
			     .containsKey(AllStaticVariables.image)) {
			    conn.setRequestProperty(AllStaticVariables.image, hashMap
			      .get(AllStaticVariables.image));
			   }

			   dos = new DataOutputStream(conn.getOutputStream());

			   Set<String> keyset = hashMap.keySet();

			   for (String string : keyset) {

			    String keyValue = hashMap.get(string);

			    if (!string
			      .equals(AllStaticVariables.image)) {
			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + string + "\"" + lineEnd);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(keyValue);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + lineEnd);

			    } else {

			     File sourceFile = new File(keyValue);

			     // open a URL connection to the Servlet
			     // FileInputStream fileInputStream = new
			     // FileInputStream(sourceFile);

			     FileInputStream fileInputStream1 = new FileInputStream(
			       sourceFile);

			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     // dos.writeBytes("Content-Disposition: form-data; name="+uploaded_file+";filename=""+ fileName + """
			     // + lineEnd);

			     dos.writeBytes("Content-Disposition: form-data; name=\""+ "image" + "\";filename=\"" + keyValue + "\""+ lineEnd);
			     // System.currentTimeMillis()+
			     dos.writeBytes(lineEnd);

			     // create a buffer of maximum size
			     bytesAvailable = fileInputStream1.available();

			     bufferSize = Math.min(bytesAvailable, maxBufferSize);
			     buffer = new byte[bufferSize];

			     // read file and write it into form...
			     bytesRead = fileInputStream1.read(buffer, 0, bufferSize);

			     while (bytesRead > 0) {

			      dos.write(buffer, 0, bufferSize);
			      bytesAvailable = fileInputStream1.available();
			      bufferSize = Math.min(bytesAvailable, maxBufferSize);
			      bytesRead = fileInputStream1
			        .read(buffer, 0, bufferSize);

			     }

			     // send multipart form data necesssary after file
			     // data...
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			     fileInputStream1.close();

			    }

			   }

			  // int serverResponseCode = conn.getResponseCode();

			   String serverResponseMessage = conn.getResponseMessage();

			   Log.i(AllStaticVariables.TAG, "res" + serverResponseMessage);
			   Log.i(AllStaticVariables.TAG, "res1" + conn.getInputStream());

			//   InputStream in = conn.getInputStream();
			//........
			//   DataInputStream inStream = new DataInputStream(in);
			//   String str;
			//   String path = "";
			//
			//   Log.i(AllStaticVariables.TAG, "res11" + inStream.readLine());
			//
			//   while ((str = inStream.readLine()) != null) {
//			    path = path + str;
			//
			//   }
			   
			   //........
			   
			    // read the output from the server
			   BufferedReader reader = null;
			   StringBuilder stringBuilder;
			        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			        stringBuilder = new StringBuilder();
			   
			        String line = null;
			        while ((line = reader.readLine()) != null)
			        {
			          stringBuilder.append(line + "\n");
			        }
			       //{"response_status":"1",
			       // "image":"http:\/\/superdhobi.pcinfosolutions.in\/assets\/profile_pic\/1444914364926.jpg",
			       // "customerid":"SDU201503","msg":"sucess"}

				  //.....




			   dos.flush();
			   dos.close();
			   jsoObj = new JSONObject(stringBuilder.toString());
			  } catch (Exception e) {
			   Log.e("log_tag", "Error in http connection " + e.toString());
			   return null;
			  }
			  return jsoObj;

			 }

	
	
	/////////////////////////////////////////////////////////////////////
	
	public JSONObject GetJsonfromMeetingImage(
			   HashMap<String, String> hashMap, HashMap<String, String> hashMapLink) {

			  JSONObject jsoObj = null;

			  try {
			   String URL = hashMapLink.get("LINK");
			   URL url = new URL(URL);

			   HttpURLConnection conn = null;
			   DataOutputStream dos = null;
			   String lineEnd = "\r\n";
			   String twoHyphens = "--";
			   String boundary = "*****";
			   int bytesRead, bytesAvailable, bufferSize;
			   byte[] buffer;
			   int maxBufferSize = 1 * 1024 * 1024;

			   // Open a HTTP connection to the URL
			   conn = (HttpURLConnection) url.openConnection();
			   conn.setDoInput(true); // Allow Inputs
			   conn.setDoOutput(true); // Allow Outputs
			   conn.setUseCaches(true); // Don't use a Cached Copy
			   conn.setRequestMethod("POST");
			   conn.setRequestProperty("Connection", "Keep-Alive");
			   conn.setRequestProperty("ENCTYPE", "multipart/form-data");
			   conn.setRequestProperty("Content-Type",
			     "multipart/form-data;boundary=" + boundary);

			   if (hashMap
			     .containsKey(AllStaticVariables.CreateMeeting.meeting_image)) {
			    conn.setRequestProperty(AllStaticVariables.CreateMeeting.meeting_image, hashMap
			      .get(AllStaticVariables.CreateMeeting.meeting_image));
			   }

			   dos = new DataOutputStream(conn.getOutputStream());

			   Set<String> keyset = hashMap.keySet();

			   for (String string : keyset) {

			    String keyValue = hashMap.get(string);

			    if (!string
			      .equals(AllStaticVariables.CreateMeeting.meeting_image)) {
			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + string + "\"" + lineEnd);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(keyValue);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + lineEnd);

			    } else {

			     File sourceFile = new File(keyValue);

			     // open a URL connection to the Servlet
			     // FileInputStream fileInputStream = new
			     // FileInputStream(sourceFile);

			     FileInputStream fileInputStream1 = new FileInputStream(
			       sourceFile);

			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     // dos.writeBytes("Content-Disposition: form-data; name="+uploaded_file+";filename=""+ fileName + """
			     // + lineEnd);

			     dos.writeBytes("Content-Disposition: form-data; name=\""+ "meeting_image" + "\";filename=\"" + keyValue + "\""+ lineEnd);
			     // System.currentTimeMillis()+
			     dos.writeBytes(lineEnd);

			     // create a buffer of maximum size
			     bytesAvailable = fileInputStream1.available();

			     bufferSize = Math.min(bytesAvailable, maxBufferSize);
			     buffer = new byte[bufferSize];

			     // read file and write it into form...
			     bytesRead = fileInputStream1.read(buffer, 0, bufferSize);

			     while (bytesRead > 0) {

			      dos.write(buffer, 0, bufferSize);
			      bytesAvailable = fileInputStream1.available();
			      bufferSize = Math.min(bytesAvailable, maxBufferSize);
			      bytesRead = fileInputStream1
			        .read(buffer, 0, bufferSize);

			     }

			     // send multipart form data necesssary after file
			     // data...
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			     fileInputStream1.close();

			    }

			   }

			  // int serverResponseCode = conn.getResponseCode();

			   String serverResponseMessage = conn.getResponseMessage();

			   Log.i(AllStaticVariables.TAG, "res" + serverResponseMessage);
			   Log.i(AllStaticVariables.TAG, "res1" + conn.getInputStream());

			//   InputStream in = conn.getInputStream();
			//........
			//   DataInputStream inStream = new DataInputStream(in);
			//   String str;
			//   String path = "";
			//
			//   Log.i(AllStaticVariables.TAG, "res11" + inStream.readLine());
			//
			//   while ((str = inStream.readLine()) != null) {
//			    path = path + str;
			//
			//   }
			   
			   //........
			   
			    // read the output from the server
			   BufferedReader reader = null;
			   StringBuilder stringBuilder;
			        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			        stringBuilder = new StringBuilder();
			   
			        String line = null;

			        
			        while ((line = reader.readLine()) != null)
			        {
			          stringBuilder.append(line + "\n");
			        }
			       
			   //.....
			   
			    
			   
			   
			   dos.flush();
			   dos.close();
			   jsoObj = new JSONObject(stringBuilder.toString());
			  } catch (Exception e) {
			   Log.e("log_tag", "Error in http connection " + e.toString());
			   return null;
			  }
			  return jsoObj;

			 }
	
	/////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	public JSONObject GetJsonfromServerForImage(
			   HashMap<String, String> hashMap, HashMap<String, String> hashMapLink) {

			  JSONObject jsoObj = null;

			  try {
			   String URL = hashMapLink.get("LINK");
			   URL url = new URL(URL);

			   HttpURLConnection conn = null;
			   DataOutputStream dos = null;
			   String lineEnd = "\r\n";
			   String twoHyphens = "--";
			   String boundary = "*****";
			   int bytesRead, bytesAvailable, bufferSize;
			   byte[] buffer;
			   int maxBufferSize = 1 * 1024 * 1024;

			   // Open a HTTP connection to the URL
			   conn = (HttpURLConnection) url.openConnection();
			   conn.setDoInput(true); // Allow Inputs
			   conn.setDoOutput(true); // Allow Outputs
			   conn.setUseCaches(true); // Don't use a Cached Copy
			   conn.setRequestMethod("POST");
			   conn.setRequestProperty("Connection", "Keep-Alive");
			   conn.setRequestProperty("ENCTYPE", "multipart/form-data");
			   conn.setRequestProperty("Content-Type",
			     "multipart/form-data;boundary=" + boundary);

			   if (hashMap
			     .containsKey(AllStaticVariables.image)) {
				   conn.setRequestProperty("image", hashMap
			      .get(AllStaticVariables.image));
			   }

			   dos = new DataOutputStream(conn.getOutputStream());

			   Set<String> keyset = hashMap.keySet();

			   for (String string : keyset) {

			    String keyValue = hashMap.get(string);

			    if (!string
			      .equals(AllStaticVariables.image)) {
			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + string + "\"" + lineEnd);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(keyValue);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + lineEnd);

			    } else {

			     File sourceFile = new File(keyValue);

			     // open a URL connection to the Servlet
			     // FileInputStream fileInputStream = new
			     // FileInputStream(sourceFile);

			     FileInputStream fileInputStream1 = new FileInputStream(
			       sourceFile);

			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     // dos.writeBytes("Content-Disposition: form-data; name="+uploaded_file+";filename=""+ fileName + """
			     // + lineEnd);

			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + "image" + "\";filename=\"" + keyValue + "\""
			       + lineEnd);
			     // System.currentTimeMillis()+
			     dos.writeBytes(lineEnd);

			     // create a buffer of maximum size
			     bytesAvailable = fileInputStream1.available();

			     bufferSize = Math.min(bytesAvailable, maxBufferSize);
			     buffer = new byte[bufferSize];

			     // read file and write it into form...
			     bytesRead = fileInputStream1.read(buffer, 0, bufferSize);

			     while (bytesRead > 0) {

			      dos.write(buffer, 0, bufferSize);
			      bytesAvailable = fileInputStream1.available();
			      bufferSize = Math.min(bytesAvailable, maxBufferSize);
			      bytesRead = fileInputStream1
			        .read(buffer, 0, bufferSize);

			     }

			     // send multipart form data necesssary after file
			     // data...
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			     fileInputStream1.close();

			    }

			   }

			  // int serverResponseCode = conn.getResponseCode();

			   String serverResponseMessage = conn.getResponseMessage();

			   Log.i(AllStaticVariables.TAG, "res" + serverResponseMessage);
			   Log.i(AllStaticVariables.TAG, "res1" + conn.getInputStream());

			//   InputStream in = conn.getInputStream();
			//........
			//   DataInputStream inStream = new DataInputStream(in);
			//   String str;
			//   String path = "";
			//
			//   Log.i(AllStaticVariables.TAG, "res11" + inStream.readLine());
			//
			//   while ((str = inStream.readLine()) != null) {
//			    path = path + str;
			//
			//   }
			   
			   //........
			   
			    // read the output from the server
			   BufferedReader reader = null;
			   StringBuilder stringBuilder;
			        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			        stringBuilder = new StringBuilder();
			   
			        String line = null;
			        while ((line = reader.readLine()) != null)
			        {
			          stringBuilder.append(line + "\n");
			        }
			       
			   //.....
			   
			    
			   
			   
			   dos.flush();
			   dos.close();
			   jsoObj = new JSONObject(stringBuilder.toString());
			  } catch (Exception e) {
			   Log.e("log_tag", "Error in http connection " + e.toString());
			   return null;
			  }
			  return jsoObj;

			 }
	
	
	////my photo
	
	public JSONObject GetJsonfromServerForMyPhoto(
			   HashMap<String, String> hashMap, HashMap<String, String> hashMapLink) {

			  JSONObject jsoObj = null;

			  try {
			   String URL = hashMapLink.get("LINK");
			   URL url = new URL(URL);

			   HttpURLConnection conn = null;
			   DataOutputStream dos = null;
			   String lineEnd = "\r\n";
			   String twoHyphens = "--";
			   String boundary = "*****";
			   int bytesRead, bytesAvailable, bufferSize;
			   byte[] buffer;
			   int maxBufferSize = 1 * 1024 * 1024;

			   // Open a HTTP connection to the URL
			   conn = (HttpURLConnection) url.openConnection();
			   conn.setDoInput(true); // Allow Inputs
			   conn.setDoOutput(true); // Allow Outputs
			   conn.setUseCaches(true); // Don't use a Cached Copy
			   conn.setRequestMethod("POST");
			   conn.setRequestProperty("Connection", "Keep-Alive");
			   conn.setRequestProperty("ENCTYPE", "multipart/form-data");
			   conn.setRequestProperty("Content-Type",
			     "multipart/form-data;boundary=" + boundary);

			   if (hashMap
			     .containsKey(AllStaticVariables.image)) {
			    conn.setRequestProperty("image", hashMap
			      .get(AllStaticVariables.image));
			   }

			   dos = new DataOutputStream(conn.getOutputStream());

			   Set<String> keyset = hashMap.keySet();

			   for (String string : keyset) {

			    String keyValue = hashMap.get(string);

			    if (!string
			      .equals(AllStaticVariables.image)) {
			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + string + "\"" + lineEnd);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(keyValue);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + lineEnd);

			    } else {

			     File sourceFile = new File(keyValue);

			     // open a URL connection to the Servlet
			     // FileInputStream fileInputStream = new
			     // FileInputStream(sourceFile);

			     FileInputStream fileInputStream1 = new FileInputStream(
			       sourceFile);

			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     // dos.writeBytes("Content-Disposition: form-data; name="+uploaded_file+";filename=""+ fileName + """
			     // + lineEnd);

			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + "image" + "\";filename=\"" + keyValue + "\""
			       + lineEnd);
			     // System.currentTimeMillis()+
			     dos.writeBytes(lineEnd);

			     // create a buffer of maximum size
			     bytesAvailable = fileInputStream1.available();

			     bufferSize = Math.min(bytesAvailable, maxBufferSize);
			     buffer = new byte[bufferSize];

			     // read file and write it into form...
			     bytesRead = fileInputStream1.read(buffer, 0, bufferSize);

			     while (bytesRead > 0) {

			      dos.write(buffer, 0, bufferSize);
			      bytesAvailable = fileInputStream1.available();
			      bufferSize = Math.min(bytesAvailable, maxBufferSize);
			      bytesRead = fileInputStream1
			        .read(buffer, 0, bufferSize);

			     }

			     // send multipart form data necesssary after file
			     // data...
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			     fileInputStream1.close();

			    }

			   }

			///  int serverResponseCode = conn.getResponseCode();

			   String serverResponseMessage = conn.getResponseMessage();

			   Log.i(AllStaticVariables.TAG, "res" + serverResponseMessage);
			   Log.i(AllStaticVariables.TAG, "res1" + conn.getInputStream());

			  // InputStream in = conn.getInputStream();
			//........
			//   DataInputStream inStream = new DataInputStream(in);
			//   String str;
			//   String path = "";
			//
			//   Log.i(AllStaticVariables.TAG, "res11" + inStream.readLine());
			//
			//   while ((str = inStream.readLine()) != null) {
//			    path = path + str;
			//
			//   }
			   
			   //........
			   
			    // read the output from the server
			   BufferedReader reader = null;
			   StringBuilder stringBuilder;
			        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			        stringBuilder = new StringBuilder();
			   
			        String line = null;
			        while ((line = reader.readLine()) != null)
			        {
			          stringBuilder.append(line + "\n");
			        }
			       
			   //.....
			   
			    
			   
			   
			   dos.flush();
			   dos.close();
			   jsoObj = new JSONObject(stringBuilder.toString());
			  } catch (Exception e) {
			   Log.e("log_tag", "Error in http connection " + e.toString());
			   return null;
			  }
			  return jsoObj;

			 }
	
	
	
	public JSONObject GetJsonfromServerForEventImage(
			   HashMap<String, String> hashMap, HashMap<String, String> hashMapLink) {

			  JSONObject jsoObj = null;

			  try {
			   String URL = hashMapLink.get("LINK");
			   URL url = new URL(URL);

			   HttpURLConnection conn = null;
			   DataOutputStream dos = null;
			   String lineEnd = "\r\n";
			   String twoHyphens = "--";
			   String boundary = "*****";
			   int bytesRead, bytesAvailable, bufferSize;
			   byte[] buffer;
			   int maxBufferSize = 1 * 1024 * 1024;

			   // Open a HTTP connection to the URL
			   conn = (HttpURLConnection) url.openConnection();
			   conn.setDoInput(true); // Allow Inputs
			   conn.setDoOutput(true); // Allow Outputs
			   conn.setUseCaches(true); // Don't use a Cached Copy
			   conn.setRequestMethod("POST");
			   conn.setRequestProperty("Connection", "Keep-Alive");
			   conn.setRequestProperty("ENCTYPE", "multipart/form-data");
			   conn.setRequestProperty("Content-Type",
			     "multipart/form-data;boundary=" + boundary);

			   if (hashMap
			     .containsKey(AllStaticVariables.eventuploadVariable.eventimage)) {
			    conn.setRequestProperty(AllStaticVariables.eventuploadVariable.eventimage, hashMap
			      .get(AllStaticVariables.eventuploadVariable.eventimage));
			   }

			   dos = new DataOutputStream(conn.getOutputStream());

			   Set<String> keyset = hashMap.keySet();

			   for (String string : keyset) {

			    String keyValue = hashMap.get(string);

			    if (!string
			      .equals(AllStaticVariables.eventuploadVariable.eventimage)) {
			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + string + "\"" + lineEnd);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(keyValue);
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + lineEnd);

			    } else {

			     File sourceFile = new File(keyValue);

			     // open a URL connection to the Servlet
			     // FileInputStream fileInputStream = new
			     // FileInputStream(sourceFile);

			     FileInputStream fileInputStream1 = new FileInputStream(
			       sourceFile);

			     dos.writeBytes(twoHyphens + boundary + lineEnd);
			     // dos.writeBytes("Content-Disposition: form-data; name="+uploaded_file+";filename=""+ fileName + """
			     // + lineEnd);

			     dos.writeBytes("Content-Disposition: form-data; name=\""
			       + "p_image" + "\";filename=\"" + keyValue + "\""
			       + lineEnd);
			     // System.currentTimeMillis()+
			     dos.writeBytes(lineEnd);

			     // create a buffer of maximum size
			     bytesAvailable = fileInputStream1.available();

			     bufferSize = Math.min(bytesAvailable, maxBufferSize);
			     buffer = new byte[bufferSize];

			     // read file and write it into form...
			     bytesRead = fileInputStream1.read(buffer, 0, bufferSize);

			     while (bytesRead > 0) {

			      dos.write(buffer, 0, bufferSize);
			      bytesAvailable = fileInputStream1.available();
			      bufferSize = Math.min(bytesAvailable, maxBufferSize);
			      bytesRead = fileInputStream1
			        .read(buffer, 0, bufferSize);

			     }

			     // send multipart form data necesssary after file
			     // data...
			     dos.writeBytes(lineEnd);
			     dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
			     fileInputStream1.close();

			    }

			   }

		//	   int serverResponseCode = conn.getResponseCode();

			   String serverResponseMessage = conn.getResponseMessage();

			   Log.i(AllStaticVariables.TAG, "res" + serverResponseMessage);
			   Log.i(AllStaticVariables.TAG, "res1" + conn.getInputStream());

		//	   InputStream in = conn.getInputStream();
			//........
			//   DataInputStream inStream = new DataInputStream(in);
			//   String str;
			//   String path = "";
			//
			//   Log.i(AllStaticVariables.TAG, "res11" + inStream.readLine());
			//
			//   while ((str = inStream.readLine()) != null) {
//			    path = path + str;
			//
			//   }
			   
			   //........
			   
			    // read the output from the server
			   BufferedReader reader = null;
			   StringBuilder stringBuilder;
			        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			        stringBuilder = new StringBuilder();
			   
			        String line = null;
			        while ((line = reader.readLine()) != null)
			        {
			          stringBuilder.append(line + "\n");
			        }
			       
			   //.....
			   
			    
			   
			   
			   dos.flush();
			   dos.close();
			   jsoObj = new JSONObject(stringBuilder.toString());
			  } catch (Exception e) {
			   Log.e("log_tag", "Error in http connection " + e.toString());
			   return null;
			  }
			  return jsoObj;

			 }
	
		///////////////////////////////////////////////

	public String GetContaint(HashMap<String, String> hashMap,
			HashMap<String, String> hashMapLink) {
		String tt = "";
		List<NameValuePair> nameValuePairs = null;
		nameValuePairs = new ArrayList<NameValuePair>();

		Set<String> keyset = hashMap.keySet();

		for (String string : keyset) {

			String keyValue = hashMap.get(string);
			nameValuePairs.add(new BasicNameValuePair(string, keyValue));

		}

	//	HashMap<String, String> flyerlist = new HashMap<String, String>();

		try {
			HttpClient httpclient = new DefaultHttpClient();
			String URL = hashMapLink.get("LINK");
			HttpPost httppost = new HttpPost(URL);

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_16));

			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
					nameValuePairs);

			httppost.setEntity(formEntity);

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
		//	int status = response.getStatusLine().getStatusCode();

			// Convert the response into a String
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				tt = EntityUtils.toString(entity);

				// Log.i("myApp ", response.toString());
			}

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			return tt;
		}

		return tt;

	}

	public JSONObject GetMethodeGetJsonfromServer(
			HashMap<String, String> hashMapLink) {

		JSONObject jsonObj = null;

	//	HashMap<String, String> flyerlist = new HashMap<String, String>();

		try {
		//	HttpClient httpclient = new DefaultHttpClient();
			String URL = hashMapLink.get("LINK");

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(URL);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();

		//	int status = httpResponse.getStatusLine().getStatusCode();

			// Convert the response into a String
			HttpEntity resEntity = httpResponse.getEntity();
			if (resEntity != null) {
				String tt = EntityUtils.toString(httpEntity);
				jsonObj = new JSONObject(tt);
				// Log.i("myApp ", response.toString());
			}

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
			return jsonObj;
		}

		return jsonObj;

	}
}