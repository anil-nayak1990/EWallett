package com.aashdit.ewallet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by computer on 1/20/2017.
 */
public class OfferActivity extends AppCompatActivity{

    LinearLayout home_linear,account_linear,more_linear,offer_linear;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.offer_activity);


        home_linear = (LinearLayout) findViewById(R.id.home_linear);
        account_linear = (LinearLayout) findViewById(R.id.account_linear);
        offer_linear = (LinearLayout) findViewById(R.id.offer_linear);
        more_linear = (LinearLayout) findViewById(R.id.more_linear);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }



        account_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(OfferActivity.this,AccountActivity.class);
                intent.putExtra("Uniqid","From_Activity_C");
                OfferActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });




        more_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OfferActivity.this, FaqTermsConditionActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        home_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(OfferActivity.this,EntryScreenMenu.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_out,R.anim.right_in);

            }
        });
    }
}
