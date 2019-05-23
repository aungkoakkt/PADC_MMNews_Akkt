package com.padcmyanmar.padc7.mmnews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.models.UserModel;
import com.padcmyanmar.padc7.mmnews.data.models.UserModelImpl;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;

public class LoginActivity extends BaseActivity {

    Toolbar toolbar;
    EditText etEmailOrPhone;
    EditText etPassword;

    private UserModel mUserModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    public static Intent newIntentBackKeyEnable(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar=findViewById(R.id.toolbar_login);
        etEmailOrPhone=findViewById(R.id.et_email_or_phone);
        etPassword=findViewById(R.id.et_password);
        mUserModel = UserModelImpl.getInstance();

        setSupportActionBar(toolbar);
    }

    public void onTapLogin(View view) {
        String emailOrPhone = etEmailOrPhone.getText().toString();
        String password = etPassword.getText().toString();

        mUserModel.login(emailOrPhone, password, new LoginDelegate() {
            @Override
            public void onSuccess(LoginUserVO loginUser) {
                Intent intent = MainActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }

            @Override
            public void onFail(String msg) {
                Snackbar.make(toolbar, msg, Snackbar.LENGTH_INDEFINITE).show();
            }
        });
    }
}
