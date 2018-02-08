package com.walmart.multibundle.activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.walmart.multibundle.R;
import com.walmart.multibundle.ReactDelegate;

public class CartActivity extends AppCompatActivity {
    private String appName;

    public static void launch(@NonNull Context context) {
        Intent intent = new Intent(context, CartActivity.class);
        ActivityOptions animation = ActivityOptions.makeCustomAnimation(context,
                R.anim.catalyst_fade_in, R.anim.catalyst_fade_out);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent, animation.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appName = getString(R.string.app_cart);
        setContentView(R.layout.activity_cart);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(getString(R.string.app_cart_title));
        View reactRootView = ReactDelegate.startApplication(getApplication(), appName, getString(R.string.asset_name_cart));
        if (reactRootView != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            FrameLayout contentArea = (FrameLayout) findViewById(R.id.fragment_container);
            contentArea.addView(reactRootView, layoutParams);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ReactDelegate.getReactInstanceManager(appName) != null) {
            ReactDelegate.getReactInstanceManager(appName).onHostPause(this);
        }
        ReactDelegate.resetReactRootView(appName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ReactDelegate.getReactInstanceManager(appName) != null) {
            ReactDelegate.getReactInstanceManager(appName).onHostResume(this, new DefaultHardwareBackBtnHandler() {
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
        if (ReactDelegate.getReactInstanceManager(appName) != null) {
            ReactDelegate.getReactInstanceManager(appName).onHostDestroy(this);
        }
        if (ReactDelegate.getReactRootView(appName) != null) {
            ReactDelegate.getReactRootView(appName).unmountReactApplication();
        }
        ReactDelegate.resetRNInstanceHolder(appName);
    }
}
