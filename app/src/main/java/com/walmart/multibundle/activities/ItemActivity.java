package com.walmart.multibundle.activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.walmart.multibundle.R;
import com.walmart.multibundle.ReactHelper;

public class ItemActivity extends AppCompatActivity {
    private String appName;

    public static void launch(@NonNull Context context) {
        Intent intent = new Intent(context, ItemActivity.class);
        ActivityOptions animation = ActivityOptions.makeCustomAnimation(context,
                R.anim.catalyst_fade_in, R.anim.catalyst_fade_out);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent, animation.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        appName = getString(R.string.app_item);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View reactRootView = ReactHelper.startApplication(getApplication(), appName, getString(R.string.asset_name_item));
        this.setContentView(reactRootView);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (ReactHelper.getReactInstanceManager(appName) != null) {
            ReactHelper.getReactInstanceManager(appName).onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ReactHelper.getReactInstanceManager(appName) != null) {
            ReactHelper.getReactInstanceManager(appName).onHostResume(this, new DefaultHardwareBackBtnHandler() {
                @Override
                public void invokeDefaultOnBackPressed() {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ReactHelper.getReactInstanceManager(appName) != null) {
            ReactHelper.getReactInstanceManager(appName).onHostDestroy(this);
        }
        if (ReactHelper.getReactRootView(appName) != null) {
            ReactHelper.getReactRootView(appName).unmountReactApplication();
            ReactHelper.resetReactRootView(appName);
        }
    }
}
