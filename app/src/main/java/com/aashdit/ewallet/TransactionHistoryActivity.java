package com.aashdit.ewallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by computer on 1/23/2017.
 */
public class TransactionHistoryActivity extends AppCompatActivity{



    ListView list_of_transactions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_money_wallet);

        list_of_transactions  = (ListView) findViewById(R.id.list_of_transactions);







    }
}
