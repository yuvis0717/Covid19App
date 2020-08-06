package com.covid19app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.covid19app.ui.helpline.HelpLineFragment;
import com.covid19app.ui.symptom.SymptomCheckFragment;
import com.covid19app.ui.testcenter.TestCentersFragment;

public class SymtomsCheckActivity extends AppCompatActivity {

    private static String EXTRA_TYPE = "type";

    public static void getInstance(Context context, int type) {
        Intent intent = new Intent(context, SymtomsCheckActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symtoms_check);
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
        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        int type = getIntent().getIntExtra(EXTRA_TYPE, 0);

        loadFragment(type);
    }

    private void loadFragment(int type) {
        TextView tvTitle = findViewById(R.id.tvTitle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (type) {
            case 0: {
                tvTitle.setText("Symptom Checker");
                ft.replace(R.id.fragmentLoad, SymptomCheckFragment.getInstance(true));
                break;
            }
            case 1: {
                tvTitle.setText("Test Centers");
                ft.replace(R.id.fragmentLoad, TestCentersFragment.getInstance(true));
                break;
            }
            case 2: {
                tvTitle.setText("Help Lines");
                ft.replace(R.id.fragmentLoad, HelpLineFragment.getInstance(true));
                break;
            }
        }
        ft.commit();
    }
}
