package com.aashdit.ewallet.webservices;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Amiya Dash on 09-12-2016.
 */

public class AsyncTask4AllGrievances extends AsyncTask<String, Void, String> {

//    private class RegisterAsyncTask {
        ProgressDialog progress;
        private Activity activity;
        String citizen, category, ward_id, Descr;
        JSONArray jsonArray;

    public AsyncTask4AllGrievances(Activity activity_) {
        activity = activity_;
    }
        public void setCitizen(String citizen_){
            citizen = citizen_;
        }
        public void setCategory(String category_){
            category = category_;
        }
        public void setWardId(String ward_id_){
            ward_id = ward_id_;
        }
        public void setComplaint(String desc_){
            Descr = desc_;
        }
        public void setJsonArrayPhoto(JSONArray jsonArray_){
            jsonArray = jsonArray_;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(activity, "Loading", "please wait", true);

        }

        @Override
        protected String doInBackground(String... urls) {
            InputStream inputStream = null;
            String result = "";
            try {




                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urls[0]);

                httpPost.setHeader("Accept", "application/json");

                httpPost.addHeader("content-type", "application/json");

                ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

                JSONObject Obj=new JSONObject();

                Obj.put("citizen", citizen);
                Obj.put("category", category);
                Obj.put("ward", ward_id);
                Obj.put("complaint", Descr);
                Obj.put("photos", jsonArray);

System.out.println("Citizen :: "+1+" category:: "+1+"  Ward:: "+ward_id+" complaint:: "+Descr+"  photo:: "+jsonArray);

                StringEntity params = new StringEntity(Obj.toString());

                httpPost.setEntity(params);

                ResponseHandler<String> handler = new BasicResponseHandler();

                result = httpclient.execute(httpPost, handler);

             /*   // 1. create HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                // 2. make POST request to the given URL
                HttpPost httpPost = new HttpPost(urls[0]);

                ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
                nameValuePair.add(new BasicNameValuePair("citizen","1"));
                nameValuePair.add(new BasicNameValuePair("category","1"));
                nameValuePair.add(new BasicNameValuePair("ward","1"));
                nameValuePair.add(new BasicNameValuePair("complaint",Descr));
                nameValuePair.add(new BasicNameValuePair("photos",jsonArray.toString()));



                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));

                httpPost.setHeader("Accept", "application/json");

                ResponseHandler<String> handler = new BasicResponseHandler();

                result = httpclient.execute(httpPost, handler);*/



            } catch (Exception e) {
                Log.d("InputStream", e.toString());
            }

            return result;
        }
        @SuppressWarnings("deprecation")
        @Override
        protected void onPostExecute(String result) {

            try {
                if (!result.equals("")){

                    JSONObject jobj = new JSONObject(result);

                    int status = jobj.getInt("status");
                    if(status == 1){
                        Toast.makeText(activity.getApplicationContext(), "Grivance added sucessfully", Toast.LENGTH_SHORT).show();
                       /* Intent inn1 = new Intent(EditProfile.this, LoginActivity.class);
                        startActivity(inn1);*/
                        activity.finish();
                    }
                    else{
                        Toast.makeText(activity.getApplicationContext(),"An error has been occured", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(activity.getApplicationContext(),"Server Down, Try Later", Toast.LENGTH_SHORT).show();

                }


            }
            catch (Exception e) {
                Log.d("ERROR", e.toString());
                Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            progress.dismiss();
            super.onPostExecute(result);

        }


    }

