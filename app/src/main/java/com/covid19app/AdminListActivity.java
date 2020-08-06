package com.covid19app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.covid19app.adapters.AdminListAdapter;
import com.covid19app.ui.symptom.SymptomCheckModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminListActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AdminListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        Window window = getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        init();
    }

    private void init() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        final RecyclerView rvAdminList = findViewById(R.id.rvAdminList);
        ImageView ivLogout = findViewById(R.id.ivLogout);
        final SwipeRefreshLayout mSwipeRefreshLayout = findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.DKGRAY, Color.GREEN, Color.RED, Color.BLUE);
        mSwipeRefreshLayout.setRefreshing(true);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                pref.edit().putBoolean("is_login", false).apply();
                startActivity(new Intent(AdminListActivity.this, MainActivity.class));
                finishAffinity();
            }
        });
        rvAdminList.setLayoutManager(new LinearLayoutManager(this));
        rvAdminList.setAdapter(new AdminListAdapter());
        final TextView tvNodata = findViewById(R.id.tvNodata);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mSwipeRefreshLayout.setRefreshing(false);
                ArrayList<SymptomCheckModel> list = new ArrayList<>();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    SymptomCheckModel mBean = postSnapshot.getValue(SymptomCheckModel.class);
                    list.add(mBean);
                }
                if (rvAdminList.getAdapter() != null)
                    ((AdminListAdapter) rvAdminList.getAdapter()).submitList(list);
                tvNodata.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
