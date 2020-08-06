package com.covid19app.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.covid19app.AdminListActivity;
import com.covid19app.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextInputLayout tilEmail = root.findViewById(R.id.tilEmail);
        final AppCompatEditText etEmail = root.findViewById(R.id.etEmail);

        final TextInputLayout tilPassword = root.findViewById(R.id.tilPassword);
        final AppCompatEditText etPassword = root.findViewById(R.id.etPassword);

        MaterialButton btnLogin = root.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etEmail.getText().toString().trim())) {
                    tilEmail.setError("Please enter an email");
                    etEmail.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(etPassword.getText().toString().trim())) {
                    tilEmail.setError(null);
                    tilPassword.setError("Please enter an password");
                    etPassword.requestFocus();
                    return;
                } else if ((etEmail.getText().toString().trim().equalsIgnoreCase("yuvrajchavda98@gmail.com") ||
                        etEmail.getText().toString().trim().equalsIgnoreCase("princepatel1792@gmail.com")) &&
                        etPassword.getText().toString().trim().equalsIgnoreCase("admin@123")) {
                    tilEmail.setError(null);
                    tilPassword.setError(null);
                    SharedPreferences pref = getContext().getSharedPreferences("app_pref", Context.MODE_PRIVATE);
                    pref.edit().putBoolean("is_login", true).apply();
                    AdminListActivity.startActivity(getContext());
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "you enter worng email or password", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
