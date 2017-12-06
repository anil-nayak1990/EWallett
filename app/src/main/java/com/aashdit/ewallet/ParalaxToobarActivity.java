package com.aashdit.ewallet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aashdit.ewallet.webservices.ServerLinks;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


public class ParalaxToobarActivity extends AppCompatActivity {

    private ArrayList<String> stringArrayList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;
    String ParamValue,Token,userId;
    ProgressDialog progress;
    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_money_wallet);

        setData(); //adding data to array list

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = ParalaxToobarActivity.this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ParamValue = sharedPreferences.getString("ParamName", null);
        Token = sharedPreferences.getString("Token",null);
        userId = sharedPreferences.getString("UserId",null);


       // new GetTransactionDetails().execute(ServerLinks.getTransactionDetails);


        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.expand));

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(this, stringArrayList);
        recyclerView.setAdapter(adapter);

    }

    private void setData() {
        stringArrayList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            stringArrayList.add("Item " + (i + 1));
        }

    }

    private class GetTransactionDetails extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        private Activity context;

        //here onPre Execute execution is to indicates the user to wait for the background process.
        //Here Progress Dialog indicates to user for waiting for response

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(ParalaxToobarActivity.this, "Loading", "please wait", true);

        }

        /**
         *
         * @param urls
         * @return
         */
        @Override
        protected String doInBackground(String... urls) {
            InputStream inputStream = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                // 2. make POST request to the given URL
                HttpPost httpPost = new HttpPost(urls[0]);

                ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

                nameValuePair.add(new BasicNameValuePair("userid", "9"));
                nameValuePair.add(new BasicNameValuePair("_csrf", Token));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                httpPost.setHeader("Accept", "application/json");

                ResponseHandler<String> handler = new BasicResponseHandler();

                result = httpclient.execute(httpPost, handler);

            /*   */



            } catch (Exception e) {
                Log.d("InputStream", e.toString());
            }

            return result;
        }

        //Here result indicates the response coming from server


        //Here On post Execution we get the response from the Server and get the data and bind the Sports Details Facilities .
        //Here Status indicates the integer value which is coming from the server to get the output response.
        @SuppressWarnings("deprecation")
        @Override
        protected void onPostExecute(String result) {

            try {
                if (!result.equals("")) {
                    JSONObject jObject = new JSONObject(result);

                    int status = jObject.getInt("status");

                    if (status == 1){
                        /*String retMsg =  AESCrypt.decrypt(ServerLinks.password,jObject.getString("data"));
                        JSONObject obj = new JSONObject(retMsg);

                        agencyName.setText(obj.getString("agencyName"));
                        contactNumber.setText( obj.getString("contactNumber"));
                        contactPerson.setText( obj.getString("contactPerson"));
                        facilityName.setText( obj.getString("facilityName"));
                        generalFee.setText( obj.getString("generalFee"));
                        navyugFee.setText( obj.getString("navyugFee"));
                        sportName.setText(obj.getString("sportName"));*/
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Server down please try later..", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                Log.d("ERROR", e.toString());
            }
            progress.dismiss();
            super.onPostExecute(result);

        }
    }
}
