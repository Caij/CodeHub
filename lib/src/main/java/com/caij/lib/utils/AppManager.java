package com.caij.lib.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Caij on 2015/8/25.
 */
public class AppManager {

    private static AppManager appManager = new AppManager();

    private Stack<Activity> activityStack;

    public static AppManager getInstance() {
        return appManager;
    }

    private AppManager() {
        activityStack = new Stack<>();
    }

    public void add(Activity activity) {
        activityStack.push(activity);
    }

    public void finishAllActivity() {
        while (!activityStack.empty()) {
            activityStack.pop().finish();
        }
    }

    public void remove(Activity activity) {
        activityStack.remove(activity);
    }

}
