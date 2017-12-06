package com.aashdit.ewallet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.aashdit.showcaselibrary.MaterialShowcaseView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;




/**
 * Created by computer on 1/20/2017.
 */
public class AccountActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{


    LinearLayout home_linear, account_linear, more_linear, offer_linear, edit_profile,logout;
    Button add_money, list_of_transactio;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;

    private static final String SHOWCASE_ID = "simple example";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.account_activity);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        home_linear = (LinearLayout) findViewById(R.id.home_linear);
        account_linear = (LinearLayout) findViewById(R.id.account_linear);
        offer_linear = (LinearLayout) findViewById(R.id.offer_linear);
        more_linear = (LinearLayout) findViewById(R.id.more_linear);
        edit_profile = (LinearLayout) findViewById(R.id.edit_profile);
        logout = (LinearLayout) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        add_money = (Button) findViewById(R.id.add_money);
        list_of_transactio = (Button) findViewById(R.id.list_of_transactio);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AccountActivity.this, EditProfileActivity.class);
                startActivity(in);
            }
        });

        add_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                buildDialog(R.style.DialogTheme, "Left - Right Animation!");
              /*  */
            }
        });
        list_of_transactio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AccountActivity.this, ParalaxToobarActivity.class);
                startActivity(in);
            }
        });
        home_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(AccountActivity.this,EntryScreenMenu.class);
                intent.putExtra("Uniqid","From_Activity_B");
                AccountActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.left_out, R.anim.right_in);

            }
        });
        offer_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AccountActivity.this, OfferActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        more_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AccountActivity.this, FaqTermsConditionActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        presentShowcaseView(0);

        //Facebook integration


    }

    private void presentShowcaseView(int withDelay) {

        new MaterialShowcaseView.Builder(this)
                .setTarget(add_money)
                .setTitleText("Hello")
                .setDismissText("GOT IT →")
                .setContentText("Add Money into your wallet")
                .setDelay(withDelay) // optional but starting animations immediately in onCreate can make them choppy
                .singleUse(SHOWCASE_ID) // provide a unique ID used to ensure it is only shown once
                .show();


    }

    private void signOut() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Intent in = new Intent(AccountActivity.this,LoginActivity.class);
                        startActivity(in);
                        finish();
                    }


                });
    }

    private void buildDialog(final int animationSource, String s) {
        LayoutInflater layoutInflater = LayoutInflater.from(AccountActivity.this);
        View promptView = layoutInflater.inflate(R.layout.addmoney_dialog, null);

        final AlertDialog alertD = new AlertDialog.Builder(AccountActivity.this).create();

        alertD.setTitle("Add Money");

        final EditText remarks = (EditText) promptView.findViewById(R.id.enter_money);
        final TextView my_amt = (TextView) promptView.findViewById(R.id.my_amt);

        Button add_money = (Button) promptView.findViewById(R.id.add_money);

        Button cancel_add_money = (Button) promptView.findViewById(R.id.cancel);

        add_money.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!InputValidation.isEditTextHasvalue(remarks)){
                    buildShake(R.style.shake, "Left - Right Animation!");
                  // alertD.getWindow().getAttributes().windowAnimations = animationSource;
                }else {

                }
                // Remarks = remarks.getText().toString();

                //new rejectionAsynk().execute(ServerLinks.androidAdminRejectWorker);
                alertD.dismiss();


            }
        });

        cancel_add_money.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertD.dismiss();


            }
        });

        alertD.setView(promptView);
        alertD.getWindow().getAttributes().windowAnimations = animationSource;
        alertD.show();
    }

    private void buildShake(final int animationSource, String s) {
        LayoutInflater layoutInflater = LayoutInflater.from(AccountActivity.this);
        View promptView = layoutInflater.inflate(R.layout.addmoney_dialog, null);

        final AlertDialog alertD = new AlertDialog.Builder(AccountActivity.this).create();

        alertD.setTitle("Add Money");

        final EditText remarks = (EditText) promptView.findViewById(R.id.enter_money);
        final TextView my_amt = (TextView) promptView.findViewById(R.id.my_amt);

        Button add_money = (Button) promptView.findViewById(R.id.add_money);

        Button cancel_add_money = (Button) promptView.findViewById(R.id.cancel);

        add_money.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!InputValidation.isEditTextHasvalue(remarks)){
                    buildShake(R.style.shake, "Left - Right Animation!");
                    // alertD.getWindow().getAttributes().windowAnimations = animationSource;
                }else {

                }
                // Remarks = remarks.getText().toString();

                //new rejectionAsynk().execute(ServerLinks.androidAdminRejectWorker);
                alertD.dismiss();


            }
        });

        cancel_add_money.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertD.dismiss();


            }
        });



        alertD.setView(promptView);
        alertD.getWindow().getAttributes().windowAnimations = animationSource;
        alertD.show();


    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
