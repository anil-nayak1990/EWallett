package com.aashdit.ewallet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aashdit.showcaselibrary.MaterialShowcaseSequence;
import com.aashdit.showcaselibrary.MaterialShowcaseView;
import com.aashdit.showcaselibrary.ShowcaseConfig;

/**
 * Created by computer on 1/20/2017.
 */
public class EntryScreenMenu extends AppCompatActivity{

        LinearLayout home_linear,account_linear,more_linear,offer_linear;

    private static final String SHOWCASE_ID = "sequence example";
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;
    String ParamValue,Token,userId;
    String strdata= null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_screen);

        sharedPreferences = EntryScreenMenu.this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ParamValue = sharedPreferences.getString("ParamName", null);
        Token = sharedPreferences.getString("Token",null);
        userId = sharedPreferences.getString("UserId",null);

        Intent intent = this.getIntent();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if(intent.getExtras()!=null) {
             strdata = intent.getExtras().getString("Uniqid");
            String offer = intent.getExtras().getString("Uniqid");
            String faqterms = intent.getExtras().getString("Uniqid");
            String account = intent.getExtras().getString("Uniqid");
            if (strdata.equals("From_Activity_A")) {

                showWallet();
                //Do Something here...
            }

           else if (account.equals("From_Activity_B")) {


                //Do Something here...
            }

         else   if (offer.equals("From_Activity_C")) {


                //Do Something here...
            }

          else  if (faqterms.equals("From_Activity_D")) {


                //Do Something here...
            }



        }

        //

        home_linear = (LinearLayout) findViewById(R.id.home_linear);
        account_linear = (LinearLayout) findViewById(R.id.account_linear);
        offer_linear = (LinearLayout) findViewById(R.id.offer_linear);
        more_linear = (LinearLayout) findViewById(R.id.more_linear);


        account_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EntryScreenMenu.this, AccountActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });



        offer_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EntryScreenMenu.this, OfferActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        more_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(EntryScreenMenu.this, FaqTermsConditionActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

    }

    private void presentShowcaseSequence() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {
                // Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
            }
        });

        sequence.setConfig(config);

        sequence.addSequenceItem(account_linear, "Click here to go for Account", "GOT IT");

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(offer_linear)
                        .setDismissText("GOT IT →")
                        .setContentText("Click here to go for Offer")
                        .withCircleShape()
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(more_linear)
                        .setDismissText("GOT IT →")
                        .setContentText("Click here to go for Faq and Terms and Condition")
                        .withCircleShape()
                        .build()
        );


        sequence.start();

    }


    private void showWallet() {
        LayoutInflater layoutInflater = LayoutInflater.from(EntryScreenMenu.this);
        View promptView = layoutInflater.inflate(R.layout.after_login_dialog, null);

        final AlertDialog alertD = new AlertDialog.Builder(EntryScreenMenu.this).create();

        alertD.setTitle("Your Current Wallet");


        final TextView my_amt = (TextView) promptView.findViewById(R.id.my_amt);
        final Button cancel = (Button) promptView.findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.dismiss();
                presentShowcaseSequence();
            }
        });

        alertD.setView(promptView

        );
      //  alertD.getWindow().getAttributes().windowAnimations = animationSource;
        alertD.show();
    }

}
