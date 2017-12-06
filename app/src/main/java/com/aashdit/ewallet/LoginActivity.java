package com.aashdit.ewallet;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aashdit.ewallet.webservices.JSONfunctions;
import com.aashdit.ewallet.webservices.ServerLinks;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;



import android.content.pm.PackageInfo;
import android.content.pm.Signature;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
/**
 * Created by computer on 1/20/2017.
 */
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SignInButton btnSignIn;
    TextView login,face_book_login;
    EditText userId,password;
    String userName, pass, DialogBox,personPhotoUrl;
    private SpotsDialog progressDialog;
    SharedPreferences sharedpreferences;
    JSONObject locatin_object;
    ProgressDialog progress;
    Double Wallet;
    Integer UserId;
    String token, username,parameterName;
    //Facebook code

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    LoginButton login1;
    ProfilePictureView profile;
    Dialog details_dialog;
    TextView details_txt;
    private String facebook_id,f_name, m_name, l_name, gender, profile_image, full_name, email_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.login_activity);

        progressDialog = new SpotsDialog(LoginActivity.this, R.style.Custom);

      //  new GetToken().execute(ServerLinks.getTokenRef);
        //google plus login
        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        //facebook login
        login1 = (LoginButton) findViewById(R.id.login_button1);

        userId = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.pass_word);


        login = (TextView) findViewById(R.id.login_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        sharedpreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 userName = userId.getText().toString();
                 pass = password.getText().toString();
                if (!InputValidation.isEditTextHasvalue(userId)){
                    Toast.makeText(LoginActivity.this, "please fill all fields", Toast.LENGTH_LONG).show();

                }else if (!InputValidation.isEditTextHasvalue(password)){
                    Toast.makeText(LoginActivity.this, "please fill all fields", Toast.LENGTH_LONG).show();

                }else {

                    Intent in = new Intent(LoginActivity.this,EntryScreenMenu.class);
                    startActivity(in);
                    finish();
                  //  new userloginTask().execute(ServerLinks.getLoggedIn);
                }
             /*   if (userId.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, EntryScreenMenu.class);
                    intent.putExtra("Uniqid", "From_Activity_A");
                    LoginActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "User not Found ", Toast.LENGTH_LONG).show();
                }*/
            }
        });


        callbackManager = CallbackManager.Factory.create();
        login = (LoginButton) findViewById(R.id.login_button1);
     //   profile = (ProfilePictureView) findViewById(R.id.picture);
        shareDialog = new ShareDialog(this);

        //login.setReadPermissions("public_profile email");

        details_dialog = new Dialog(this);
        details_dialog.setContentView(R.layout.dialog_details);
        //details_dialog.setTitle("Details");
        details_txt = (TextView) details_dialog.findViewById(R.id.details);
        if (AccessToken.getCurrentAccessToken() != null) {
            RequestData();
        }
        getKeyHash();
        login1.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                facebook_id = f_name = m_name = l_name = gender = profile_image = full_name = email_id = "";

                if (AccessToken.getCurrentAccessToken() != null) {
                    RequestData();

                    Profile profile = Profile.getCurrentProfile();
                    if (profile != null) {
                        facebook_id = profile.getId();
                        Log.e("facebook_id", facebook_id);
                        f_name = profile.getFirstName();
                        Log.e("f_name", f_name);
                        m_name = profile.getMiddleName();
                        Log.e("m_name", m_name);
                        l_name = profile.getLastName();
                        Log.e("l_name", l_name);
                        full_name = profile.getName();
                        Log.e("full_name", full_name);
                        profile_image = profile.getProfilePictureUri(400, 400).toString();
                        Log.e("profile_image", profile_image);



                    }
                    //   share.setVisibility(View.GONE);
                    //   details.setVisibility(View.GONE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    //android sending through shared preference

                    //  editor.putString("mobile",mobile);
                    editor.putString("FBfName", f_name);
                    editor.putString("FBlLame", l_name);
                    editor.putString("userName", full_name);
                    editor.putString("PHOTO", profile_image);
                    editor.putString("FBId", facebook_id);

                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, EntryScreenMenu.class);
                    intent.putExtra("Uniqid", "From_Activity_A");
                    LoginActivity.this.startActivity(intent);
                    finish();


                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            //Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String id = acct.getId().toString();
           /* try {
                personPhotoUrl = acct.getPhotoUrl().toString();
               *//* Glide.with(getApplicationContext()).load(personPhotoUrl)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgProfilePic);*//*
            }
            catch(Exception e){

               // imgProfilePic.setImageResource(R.drawable.awkward);//awkward.png in drawable folder
            }*/
           String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

        /*    Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);*/
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this,EntryScreenMenu.class);
            intent.putExtra("Uniqid","From_Activity_A");
            LoginActivity.this.startActivity(intent);

            SharedPreferences.Editor editor = sharedpreferences.edit();

            //android sending through shared preference

          //  editor.putString("mobile",mobile);
            editor.putString("userName", personName);
            editor.putString("User_email", email);
           editor.putString("PHOTO", personPhotoUrl);
            editor.putString("Id", id);

            editor.commit();

            //txtName.setText(personName);
            //txtEmail.setText(email);
   /*      Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .into(imgProfilePic);*/


            updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }


    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);

        } else {
            btnSignIn.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
    private void getKeyHash() {

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.aashdit.ewallet", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);


            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    public void RequestData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                JSONObject json = response.getJSONObject();
                System.out.println("Json data :"+json);
                try {
                    if(json != null){
                        String text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
                        //details_txt.setText(Html.fromHtml(text));

                      //  profile.setProfileId(json.getString("id"));

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        //android sending through shared preference

                        //  editor.putString("mobile",mobile);
                        editor.putString("fuserName", json.getString("name"));
                        editor.putString("fUser_email", json.getString("email"));
                        //editor.putString("PHOTO", personPhotoUrl);
                       // editor.putString("Id", id);

                        editor.commit();
                        /*Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, EntryScreenMenu.class);
                        intent.putExtra("Uniqid", "From_Activity_A");
                        LoginActivity.this.startActivity(intent);
                        finish();*/
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }




    private class GetToken extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(LoginActivity.this, "Loading", "please wait", true);

        }

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            try {
                // 1. create HttpClient
                HttpClient httpclient = new DefaultHttpClient();
                // 2. make POST request to the given URL
                HttpGet httpGet = new HttpGet(urls[0]);

                httpGet.setHeader("Accept", "application/json");
                ResponseHandler<String> handler = new BasicResponseHandler();
                result = httpclient.execute(httpGet, handler);

            } catch (Exception e) {
                Log.d("InputStream", e.toString());
            }
            return result;
        }

        @SuppressWarnings("unused")
        @Override
        protected void onPostExecute(String result) {


            try {
                if (!result.equals("")){
                    JSONObject locatin_object = new JSONObject(result);

                   token = locatin_object.getString("token");
                    parameterName = locatin_object.getString("parameterName");


                }

            }
            catch (Exception e) {
                Log.e("String ", e.toString());
            }
            progress.dismiss();

            super.onPostExecute(result);
        }
    }

/*    private class userloginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(LoginActivity.this, "", "Please Wait ...", true);

        }

        @Override
        protected Void doInBackground(Void... params) {

            locatin_object = JSONfunctions.getJSONByHttpGet(ServerLinks.BASE_URL + "getLoggedIn?username=" + userName + "&password=" + pass +"_csrf="+token);

            try{

               // Integer status = locatin_object.getInt("status");

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void args) {




            progress.dismiss();
        }
    }*/

    private class userloginTask extends AsyncTask<String, Void, String> {
        String results = "";
        //onPreExecute is used for background Process for User
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(LoginActivity.this, "Loading", "please wait", true);

        }

        //Doinbackground process is used for Http Get Request for the sports resources
        @Override
        protected String doInBackground(String... urls) {
           // HttpResponse result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                // banner. make POST request to the given URL
                HttpPost httpPost = new HttpPost(urls[0]);

                ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
                nameValuePair.add(new BasicNameValuePair("userid", userName));
                nameValuePair.add(new BasicNameValuePair("password", pass));
                nameValuePair.add(new BasicNameValuePair("_csrf", token));

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));

                //httpPost.setHeader("Accept", "application/json");



                HttpResponse result = httpclient.execute(httpPost);
                results= EntityUtils.toString(result.getEntity());

            } catch (Exception e) {
                Log.d("InputStream", e.toString());
            }
           return results;
        }


        //Here onPost Execute is used to get the Response coming from server in json Parameter .
        //Here resource Image drawable is used for Bind the data according the sports category
        @SuppressWarnings("unused")
        @Override
        protected void onPostExecute(String result) {

      /*      categoryServerlist = new ArrayList<SportsDTO>();
            JSONObject jsonResponse;*/
            try {
                if (!result.equals("")){
                    JSONObject locatin_object = new JSONObject();

                    Wallet = locatin_object.getDouble("amount");
                    JSONObject userObj = locatin_object.getJSONObject("user");
                    UserId = userObj.getInt("id");
                    username = userObj.getString("username");
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Token",token);
                    editor.putString("ParamName", parameterName);
                    editor.putString("UserId", String.valueOf(UserId));
                    editor.commit();
                    Intent in = new Intent(LoginActivity.this,EntryScreenMenu.class);
                    startActivity(in);
                    finish();
                    //JSONObject tokenObj = locatin_object.getJSONObject("token");
                    //Token = tokenObj.getString("token");
                   // parameterName = tokenObj.getString("parameterName");
                }

            }
            catch (Exception e) {
                Log.e("String ", e.toString());
            }
            progress.dismiss();

            super.onPostExecute(result);
        }
    }

}
