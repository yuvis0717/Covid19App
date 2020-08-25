package com.covid19app.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.covid19app.AdminListActivity;
import com.covid19app.MainActivity;
import com.covid19app.R;
import com.covid19app.utils.CheckConnection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignup, btnLogin;
    private EditText edtEmail, edtPassword;
    private TextInputLayout tilEmail, tilPassword;
    private TextView tvForgotpassword;
    private String email, password;
    private FirebaseAuth mAuth;
    private ProgressDialog prgDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Window window = getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        TextView tvTitle = toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.app_name);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initControls();

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvForgotpassword.setOnClickListener(this);
    }

    private void initControls() {
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotpassword = findViewById(R.id.btnForgot);
        edtEmail = findViewById(R.id.edtloginEmail);
        edtPassword = findViewById(R.id.edtloginPassword);
        prgDialog = new ProgressDialog(this);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSignup:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLogin:
                if (CheckConnection.checkConnection(this)) {
                    if (checkIsEmptyorNot()) {
                        if ((email.equalsIgnoreCase("yuvrajchavda98@gmail.com") ||
                                email.equalsIgnoreCase("princepatel1792@gmail.com")) &&
                                password.equalsIgnoreCase("admin@123")) {
                            SharedPreferences pref = getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                            pref.edit().putBoolean("is_login_admin", true).apply();
                            AdminListActivity.startActivity(this);
                            finish();
                        } else {
                            prgDialog.setMessage("Please Wait...");
                            prgDialog.setCancelable(false);
                            prgDialog.show();
                            mAuth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            prgDialog.dismiss();
                                            if (task.isSuccessful()) {
                                                if (task.getResult() != null) {
                                                    FirebaseUser user = task.getResult().getUser();
                                                    if (user != null && user.isEmailVerified()) {
                                                        Toast.makeText(LoginActivity.this, "SuccessFully login", Toast.LENGTH_SHORT).show();
                                                        SharedPreferences pref = getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                                                        pref.edit().putBoolean("is_login", true).apply();
                                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                        finish();
                                                    } else {
                                                        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                                        builder.setMessage("This user is not verified. Please verify your email by clicking link we sent to your mail.")
                                                                .setCancelable(false)
                                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {
                                                                        dialog.dismiss();
                                                                    }
                                                                });
                                                        //Creating dialog box
                                                        AlertDialog alert = builder.create();
                                                        //Setting the title manually
                                                        alert.setTitle("Verify Your Email");
                                                        alert.show();
                                                    }
                                                } else {
                                                    tilPassword.setError("Email id or password Wrong");
                                                }
                                            } else {
                                                tilPassword.setError("Email id or password Wrong");
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                } else {
                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnForgot:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;

        }
    }


    private boolean checkIsEmptyorNot() {
        email = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            tilEmail.setError("Please Enter Email");
            edtEmail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            tilEmail.setError(null);
            tilPassword.setError("Please Enter Password");
            edtPassword.requestFocus();
            return false;
        } else
            tilPassword.setError(null);

        return true;
    }
}

