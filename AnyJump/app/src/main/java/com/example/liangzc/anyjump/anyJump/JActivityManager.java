package com.example.liangzc.anyjump.anyJump;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by liangzc on 2019/1/11.
 */

public class JActivityManager implements Application.ActivityLifecycleCallbacks {

    private static Stack<Activity> stack; //activity 栈

    private static class ActivityHelper {
        private static final JActivityManager instance = new JActivityManager();
    }
    /***
     * 获取单例
     * @return
     */
    public static JActivityManager getInstance() {
        if (stack == null) {
            stack = new Stack<>();
        }
        return ActivityHelper.instance;
    }

    /***
     * 初始化
     * @param application
     */
    public void init(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        addStack(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeStack(activity);
    }

    /***
     * activity 入栈，出栈
     * @param activity
     */
    public void addStack(Activity activity) {
        stack.add(activity);
    }
    public void removeStack(Activity activity) {
        stack.remove(activity);
    }

    /***
     * 栈顶 Activity  Back
     */
    public void popActivity() {
        if (stack == null || stack.size() == 0) {
            return;
        }
        Activity activity = topActivity();
        finishActivity(activity);
    }

    /***
     * 返回到 Activity 栈中的指定位置
     * @param index
     */
    public void popToActivityAtIndex(int index) {
        if (stack == null || stack.size() == 0 || index > stack.size()) {
            return;
        }
        Stack<Activity> temp = stack;
        for (int i = stack.size() - 1; i > index; i --) {
            Activity activity = stack.get(i);
            temp.remove(activity);
            finishActivity(activity);
        }
        stack = temp;
    }

    /***
     * 返回到指定 Activity
     * @param cls
     */
    public void popToActivity(Class<?> cls) {
        for (int i = stack.size() - 1; i > 0; i --) {
            Activity activity = stack.get(i);
            if (cls.isAssignableFrom(activity.getClass())) {
                break;
            }
            stack.remove(activity);
            finishActivity(activity);
        }
    }

    /***
     * Finish Activity
     * @param activity
     */
    private void finishActivity(Activity activity) {
        if (activity != null) {
            stack.remove(activity);
            activity.finish();
        }
    }

    /***
     * 获取栈顶 Activity
     * @return
     */
    public Activity topActivity() {
        return stack.get(stack.size() - 1);
    }

    /***
     * 获取 Activity 栈中所有元素
     * @return
     */
    public List<Activity> activities() {
        List<Activity> activityList = new ArrayList<>();
        activityList.addAll(stack);
        return activityList;
    }
}
