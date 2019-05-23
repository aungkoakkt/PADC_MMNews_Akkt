package com.padcmyanmar.padc7.mmnews.views.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.ViewPodDelegate;

public class LoginUserViewPod extends RelativeLayout
        implements ViewPodDelegate<LoginUserViewPod.LoginUserViewPodDelegate> {

    ImageView ivLoginUserBg;

    ImageView ivLoginUser;

    TextView tvName;

    TextView tvPhoneNo;

    Button btnLogout;

    private LoginUserViewPodDelegate mDelegate;

    public LoginUserViewPod(Context context) {
        super(context);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivLoginUserBg=findViewById(R.id.iv_login_user_bg);
        ivLoginUser=findViewById(R.id.iv_login_user);
        tvName=findViewById(R.id.tv_name);
        tvPhoneNo=findViewById(R.id.tv_phone_no);
        btnLogout=findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(mDelegate == null) {
                    throw new RuntimeException("Need to set the delegate for LoginUserViewPod first.");
                }
                mDelegate.onTapLogout();
            }
        });
    }

    public void setData(LoginUserVO loginUser) {
        tvName.setText(loginUser.getName());
        tvPhoneNo.setText(loginUser.getPhoneNo());
    }

    @Override
    public void setDelegate(LoginUserViewPodDelegate delegate) {
        mDelegate = delegate;
    }

    public interface LoginUserViewPodDelegate {
        void onTapLogout();
    }
}
