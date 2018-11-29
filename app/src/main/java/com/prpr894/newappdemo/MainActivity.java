package com.prpr894.newappdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prpr894.baseioclibrary.ioc.OnClick;
import com.prpr894.baseioclibrary.ioc.ViewBindId;
import com.prpr894.baseioclibrary.ioc.ViewUtils;
import com.prpr894.newappdemo.login.CheckLogin;
import com.prpr894.newappdemo.net.CheckNet;

public class MainActivity extends AppCompatActivity {

    @ViewBindId(R.id.tv_main)
    private TextView mTvMain;
    @ViewBindId(R.id.img_main)
    private ImageView mImgMain;
    private int mInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        mTvMain.setText("测试成功！");
    }


    @OnClick(R.id.tv_main)
    private void tvMainClick(TextView tvMain) {
//        Toast.makeText(this, "文字点击：" + (mInt++), Toast.LENGTH_LONG).show();
        checkNewt();
    }

    @CheckNet
    private void checkNewt() {
        Log.d("flag", "走了  ====================== ");
    }

    @OnClick(R.id.img_main)
    @CheckLogin
    private void imgMainClick(ImageView imgMain) {
        Toast.makeText(this, "图片点击：" + (mInt++), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
