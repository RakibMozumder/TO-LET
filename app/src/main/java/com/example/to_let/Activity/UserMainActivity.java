package com.example.to_let.Activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.to_let.Fragment.HomeFragment;
import com.example.to_let.Fragment.LoginFragment;
import com.example.to_let.Fragment.ProfileFragment;
import com.example.to_let.Fragment.SignupFragment;
import com.example.to_let.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class UserMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int IMAGE_CAPTURE_CODE =1001 ;// implements AdapterView.OnItemClickListener
    private static final int PERMISSION_CODE = 2001;

    private FirebaseAuth mAuth;
    private DrawerLayout drawer;
    private ListView listView;
    private String[] aaa;//android:entries="@array/aa"
    private ActionBarDrawerToggle drawerToggle;
    private Button searchButton;

    private static final int RESULT_LOAD_IMAGE= 1;
    private Uri imageUri;
    private ImageView imageView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        searchButton = (Button) findViewById(R.id.searchId);


        mAuth = FirebaseAuth.getInstance();
        //aaa = getResources().getStringArray(R.array.aa);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      /*  listView = (ListView) findViewById(R.id.drawerListMain);
        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, aaa));
        listView.setOnItemClickListener(MainActivity.this);*/
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle = new ActionBarDrawerToggle(UserMainActivity.this, drawer, toolbar,
                R.string.openDrawer, R.string.closeDrawer); /*{

            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this, "Drawer Opened", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this, "Drawer Closed", Toast.LENGTH_SHORT).show();

            }
        };*/
        // drawer.setDrawerListener(drawerToggle);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


       /* if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }*/

/*

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMainActivity.this, SearchCriteria.class);
                startActivity(intent);
            }
        });



      /*  imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_profile:
                Intent intent = new Intent(UserMainActivity.this,LoginActivity.class);
                startActivity(intent);     /* Intent intent = new Intent(MainActivity.this, ProfileActivity1.class);
                startActivity(intent);*/
                break;
            case R.id.nav_login:
                Intent intent2= new Intent(UserMainActivity.this, LoginActivity.class);
                startActivity(intent2);  break;
            case R.id.nav_signUp:
                Intent intent3 = new Intent(UserMainActivity.this, SignupActivity.class);
                startActivity(intent3);  break;
            case R.id.nav_post:
                //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PostFragment()).commit();

                /*FirebaseUser currentUser = mAuth.getCurrentUser();
                if (mAuth == null) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }*/

//               /* if (currentUser != null) {
//                    //loginUserAndUpdateInstanceId(currentUser.getUid());
//                    finish();
//
//                }*/
                Intent intent4 = new Intent(UserMainActivity.this, PostActivity.class);
                startActivity(intent4);
                break;



        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
  /*  @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(UserMainActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (currentUser == null) {

        }

    }*/






}

