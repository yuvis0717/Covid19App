package com.covid19app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.covid19app.models.Constant;
import com.covid19app.models.UserInformation;
import com.covid19app.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView tvUserName, tvUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView.getHeaderCount() > 0) {
            tvUserName = navigationView.getHeaderView(0).findViewById(R.id.tvUserName);
            tvUserEmail = navigationView.getHeaderView(0).findViewById(R.id.tvUserEmail);
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_symptom_check, R.id.nav_precautionary_measures,
                R.id.nav_face_mask_tips, R.id.nav_help_line, R.id.nav_test_center, R.id.nav_news)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setUserDetail();


        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                SharedPreferences pref = getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                pref.edit().putBoolean("is_login", false).apply();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finishAffinity();
                return true;
            }
        });
    }

    private void setUserDetail() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.getUid();
            DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference("User");
            databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserInformation info = dataSnapshot.child(user.getUid()).getValue(UserInformation.class);

                    Constant.setInfo(info);

                    if (info != null && tvUserName != null && tvUserEmail != null) {
                        String userName = info.getName() + " " + info.getLastName();
                        tvUserName.setText(userName);
                        tvUserEmail.setText(info.getEmail());
                    }

//                    Log.d("Uri", info.getImagePath());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void openMenuDrawer() {
        if (mAppBarConfiguration.getDrawerLayout() != null && !mAppBarConfiguration.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            mAppBarConfiguration.getDrawerLayout().openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (mAppBarConfiguration.getDrawerLayout() != null && mAppBarConfiguration.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            mAppBarConfiguration.getDrawerLayout().closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
