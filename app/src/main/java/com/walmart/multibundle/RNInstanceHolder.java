package com.walmart.multibundle;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;

/**
 * Class holds the building blocks of RN App
 */

public class RNInstanceHolder {
    private ReactInstanceManager reactInstanceManager;
    private ReactRootView rootView;

    public RNInstanceHolder() {
    }

    public RNInstanceHolder(ReactInstanceManager reactInstanceManager, ReactRootView rootView) {
        this.reactInstanceManager = reactInstanceManager;
        this.rootView = rootView;
    }

    public ReactInstanceManager getReactInstanceManager() {
        return reactInstanceManager;
    }

    public ReactRootView getRootView() {
        return rootView;
    }

    public void setReactInstanceManager(ReactInstanceManager reactInstanceManager) {
        this.reactInstanceManager = reactInstanceManager;
    }

    public void setRootView(ReactRootView rootView) {
        this.rootView = rootView;
    }
}
