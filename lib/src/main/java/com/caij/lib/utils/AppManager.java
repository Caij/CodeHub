package com.caij.lib.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Caij on 2015/8/25.
 */
public class AppManager {

    private static AppManager appManager = new AppManager();

    private Stack<Activity> mActivityStack;

    public static AppManager getInstance() {
        return appManager;
    }

    private AppManager() {
        mActivityStack = new Stack<>();
    }

    public void push(Activity activity) {
        mActivityStack.push(activity);
    }

    public Activity pop() {
        return mActivityStack.pop();
    }

    public void finishAllActivity() {
        while (!mActivityStack.empty()) {
            mActivityStack.pop().finish();
        }
    }

    public void finishAllActivityExcept(Activity activity) {
        Activity removeActivity;
        while (!mActivityStack.empty()) {
            removeActivity = mActivityStack.pop();
            if (removeActivity != activity) {
                removeActivity.finish();
            }
        }
    }

    public void remove(Activity activity) {
        mActivityStack.remove(activity);
    }

}
