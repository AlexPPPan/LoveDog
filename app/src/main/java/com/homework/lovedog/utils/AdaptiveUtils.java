package com.homework.lovedog.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public class AdaptiveUtils {
    public static final int DESIGN_WIDTH = 360;
    private static float sAppDensity;
    public static float sAppScaledDensity;
    private static DisplayMetrics sAppDisplayMetrics;
    private static float sWidth;

    public static void setDensity(@NonNull final Application application, float width) {
        sAppDisplayMetrics = application.getResources().getDisplayMetrics();
        sWidth = width;

        registerActivityLifecycleCallbacks(application);

        if (sAppDensity == 0) {
            sAppDensity = sAppDisplayMetrics.density;
            sAppScaledDensity = sAppDisplayMetrics.scaledDensity;

            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sAppScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
    }


    private static void setDefault(Activity activity) {
        setAppOrientation(activity);
    }

    private static void setAppOrientation(@NonNull Activity activity) {

        float targetDensity = 0;
        try {
            targetDensity = sAppDisplayMetrics.widthPixels / sWidth;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        float targetScaledDensity = targetDensity * (sAppScaledDensity / sAppDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }


    private static void registerActivityLifecycleCallbacks(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                setDefault(activity);
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

            }
        });
    }

}
