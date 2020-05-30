package dz.islem.mvvmarch.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.ViewModelProvider;

import butterknife.ButterKnife;
import dz.islem.mvvmarch.R;
import dz.islem.mvvmarch.ui.base.BaseActivity;
import dz.islem.mvvmarch.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<SplashViewModel> {
    private final long SPLASH_TIME = 2500;

    @Override
    protected SplashViewModel createViewModel() {
        SplashViewModelFactory factory = new SplashViewModelFactory();
        return new ViewModelProvider(this, factory).get(SplashViewModel.class);
    }


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Handler mHandler = new Handler();
        mHandler.postDelayed(this::startMain,SPLASH_TIME);
    }

    private void startMain(){
        this.startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
