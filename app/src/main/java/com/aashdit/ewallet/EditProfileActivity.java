package com.aashdit.ewallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.util.StringTokenizer;

/**
 * Created by computer on 1/23/2017.
 */
public class EditProfileActivity extends AppCompatActivity{


    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    ImageView profile_pic;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    String Profile_pic,Email,Dob,UserName,FirstName,Lastname,Mobile,first,second;
    TextView firstname,lastName,userName,dob,mobile,email;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_activity);
        sharedpreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        Email = sharedpreferences.getString("User_email", null);
//        UserName = sharedpreferences.getString("userName", null);
//        first = sharedpreferences.getString("FBfName", null);
//        second = sharedpreferences.getString("FBlName", null);
//
//
//        Profile_pic = sharedpreferences.getString("PHOTO", null);
//
//
//        StringTokenizer tokens = new StringTokenizer(UserName, " " + " ");
//        //String first = tokens.nextToken();// this will contain "Fruit"
//        //String second = tokens.nextToken();
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//        profile_pic = (ImageView) findViewById(R.id.profile_id);
//        firstname = (TextView) findViewById(R.id.first_name);
//        lastName = (TextView) findViewById(R.id.last_name);
//        userName = (TextView) findViewById(R.id.user_name);
//        dob = (TextView) findViewById(R.id.dob);
//        mobile = (TextView) findViewById(R.id.mob);
//        email = (TextView) findViewById(R.id.email);
//
//        email.setText(Email);
//        userName.setText(UserName);
//        firstname.setText(first);
//        lastName.setText(second);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//
//
//
//        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle(getResources().getString(R.string.user_name));
//
//        dynamicToolbarColor();
//
//        toolbarTextAppernce();
//
//        Glide.with(getApplicationContext()).load(Profile_pic)
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(profile_pic);

    }

    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.profile_pic);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark));
            }
        });
    }


    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
