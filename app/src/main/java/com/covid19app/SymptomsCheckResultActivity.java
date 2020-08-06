package com.covid19app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.covid19app.ui.symptom.SymptomCheckModel;
import com.covid19app.utils.BulletPointSpan;
import com.google.gson.Gson;

public class SymptomsCheckResultActivity extends AppCompatActivity {

    private static String EXTRA_DATA = "data";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_check_result);

        Window window = getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setResult(RESULT_OK);

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (getIntent().hasExtra(EXTRA_DATA)) {
            String result = getIntent().getStringExtra(EXTRA_DATA);
            SymptomCheckModel mBean = new Gson().fromJson(result, SymptomCheckModel.class);

            AppCompatTextView tvRisk = findViewById(R.id.tvRisk);
            tvRisk.setText(mBean.getRisk());
                if (mBean.getRisk().equalsIgnoreCase("high")) {
                    tvRisk.setTextColor(ContextCompat.getColor(this, R.color.colorHigh));
                } else if (mBean.getRisk().equalsIgnoreCase("medium")) {
                    tvRisk.setTextColor(ContextCompat.getColor(this, R.color.colorMedium));
                } else {
                    tvRisk.setTextColor(ContextCompat.getColor(this, R.color.colorLow));
                }
        }
        setBulletText();
    }

    private void setBulletText() {
        AppCompatTextView tvRec1 = findViewById(R.id.tvRec1);
        AppCompatTextView tvRec2 = findViewById(R.id.tvRec2);
        AppCompatTextView tvRec3 = findViewById(R.id.tvRec3);
        AppCompatTextView tvRec4 = findViewById(R.id.tvRec4);
        AppCompatTextView tvRec5 = findViewById(R.id.tvRec5);
        AppCompatTextView tvRec6 = findViewById(R.id.tvRec6);
        AppCompatTextView tvRec7 = findViewById(R.id.tvRec7);

        tvRec1.setText(getBulletSpanneble(R.string.lbl_rec_1));
        tvRec2.setText(getBulletSpanneble(R.string.lbl_rec_2));
        tvRec3.setText(getBulletSpanneble(R.string.lbl_rec_3));
        tvRec4.setText(getBulletSpanneble(R.string.lbl_rec_4));
        tvRec5.setText(getBulletSpanneble(R.string.lbl_rec_5));
        tvRec6.setText(getBulletSpanneble(R.string.lbl_rec_6));
        tvRec7.setText(getBulletSpanneble(R.string.lbl_rec_7));
    }

    private SpannableString getBulletSpanneble(@StringRes int res) {
        BulletPointSpan bulletPointSpan = new BulletPointSpan(8, ContextCompat.getColor(this, R.color.colorPrimary));
        SpannableString rec1 = new SpannableString(getString(res));
        rec1.setSpan(
                bulletPointSpan,
                0, rec1.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return rec1;
    }
}
