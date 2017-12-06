package com.aashdit.ewallet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by computer on 1/23/2017.
 */
public class FaqTermsConditionActivity extends AppCompatActivity {
    ViewPager viewPager ;
    TabLayout tabLayout;
    LinearLayout home_linear,account_linear,more_linear,offer_linear;

    public static boolean viewIsAtHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_terms_condi);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        home_linear = (LinearLayout) findViewById(R.id.home_linear);
        account_linear = (LinearLayout) findViewById(R.id.account_linear);
        offer_linear = (LinearLayout) findViewById(R.id.offer_linear);
        more_linear = (LinearLayout) findViewById(R.id.more_linear);



        account_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FaqTermsConditionActivity.this,EntryScreenMenu.class);
                intent.putExtra("Uniqid","From_Activity_D");
                FaqTermsConditionActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });



        offer_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FaqTermsConditionActivity.this,OfferActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });

        home_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FaqTermsConditionActivity.this,EntryScreenMenu.class);
                startActivity(in);
                overridePendingTransition(R.anim.left_out,R.anim.right_in);

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.faq_toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewIsAtHome = true;
    }
    public void selectFragment(int position){
        viewPager.setCurrentItem(position, true);
// true is to animate the transaction
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FAQActivity(), "FAQ");
        adapter.addFragment(new TermsandConditionActivity(), "Terms and Condition");

        viewPager.setAdapter(adapter);


    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
