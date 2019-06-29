package com.example.bakingapp;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class BakingIdleResource implements IdlingResource {

    @Nullable private volatile ResourceCallback resourceCallback;
    private AtomicBoolean isIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback=callback;
    }

    public void setIdleState(boolean isIdleNow){
        this.isIdleNow.set(isIdleNow);
        if(isIdleNow&&resourceCallback!=null) resourceCallback.onTransitionToIdle();
    }
}
