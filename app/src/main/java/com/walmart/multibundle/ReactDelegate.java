package com.walmart.multibundle;

import android.app.Application;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to react native operations
 */

public class ReactDelegate {

    private static Map<String, RNInstanceHolder> viewMap = new HashMap<>();

    public static synchronized ReactRootView startApplication(Application application, String appName, String assetName) {
        if (viewMap.get(appName) == null || viewMap.get(appName).getRootView() == null) {
            ReactRootView rootView = new ReactRootView(application.getApplicationContext());
            ReactInstanceManager reactInstanceManager = getReactInstanceManager(application, assetName);
            viewMap.put(appName, new RNInstanceHolder(reactInstanceManager, rootView));
            rootView.startReactApplication(reactInstanceManager, appName);
            return rootView;
        }
        return viewMap.get(appName).getRootView();

    }

    public static ReactInstanceManager getReactInstanceManager(String appName) {
        if (viewMap.get(appName) != null) {
            return viewMap.get(appName).getReactInstanceManager();
        }
        return null;
    }

    public static ReactRootView getReactRootView(String appName) {
        if (viewMap.get(appName) != null) {
            return viewMap.get(appName).getRootView();
        }
        return null;
    }

    public static void resetReactRootView(String appName) {
        if (viewMap.get(appName) != null) {
            RNInstanceHolder holder = viewMap.get(appName);
            holder.setRootView(null);
            viewMap.put(appName, holder);
        }
    }

    public static void resetRNInstanceHolder(String appName) {
        if (viewMap.get(appName) != null) {
            viewMap.remove(appName);
        }
    }

    private static ReactInstanceManager getReactInstanceManager(Application application, String assetName) {
        return ReactInstanceManager.builder()
                .setApplication(application)
                .setBundleAssetName(assetName)
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(true)
                .setInitialLifecycleState(LifecycleState.BEFORE_RESUME)
                .build();

    }
}
