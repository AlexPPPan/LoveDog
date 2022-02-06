package com.homework.lovedog.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.homework.lovedog.R;


public class CustomProgress extends Dialog {
    private static final String TAG = "CustomProgress";
    private static CustomProgress dialog;
    public CustomProgress(Context context) {
        super(context);
    }

    public CustomProgress(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (dialog == null) {
            return;
        }
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    public static CustomProgress show(Context context, int msgResId, boolean cancelable,
                                      boolean cancelableBack, OnCancelListener cancelListener) {
        String msg = context.getString(msgResId);
        return show(context, msg, cancelable, cancelableBack, cancelListener);
    }


    public static CustomProgress show(Context context, CharSequence message, boolean cancelable, boolean
        cancelableBack, OnCancelListener cancelListener) {
        return show(context, message, cancelable, cancelableBack, false, cancelListener);
    }

    public static CustomProgress show(Context context, CharSequence message, boolean cancelable, boolean
        cancelableBack, boolean fullScreen, OnCancelListener cancelListener) {

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() || activity.isDestroyed())
                return null;
        }

        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.setMessage(message);
                dialog.setCancelable(cancelable);
                dialog.setCanceledOnTouchOutside(cancelableBack);
                dialog.setOnCancelListener(cancelListener);
                return dialog;
            }
            dialog = null;
        }

        dialog = new CustomProgress(context, R.style.Custom_Progress);
        dialog.setTitle("");
        dialog.setContentView(R.layout.progress_custom2);
        if (TextUtils.isEmpty(message)) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = (TextView) dialog.findViewById(R.id.message);
            txt.setText(message);
        }

        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelableBack);
        dialog.setOnCancelListener(cancelListener);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        if (fullScreen) {
            View decorView = dialog.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        dialog.show();

        return dialog;
    }

    public static CustomProgress show(Context context, CharSequence message, boolean cancelable, boolean
        cancelableBack, OnCancelListener cancelListener,int layout) {

        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() || activity.isDestroyed())
                return null;
        }

        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.setMessage(message);
                dialog.setCancelable(cancelable);
                dialog.setCanceledOnTouchOutside(cancelableBack);
                dialog.setOnCancelListener(cancelListener);
                return dialog;
            }
            dialog = null;
        }

        dialog = new CustomProgress(context, R.style.Custom_Progress);
        dialog.setTitle("");

        if(layout!=0){
            dialog.setContentView(layout);
        }else{
            dialog.setContentView(R.layout.progress_custom2);
        }

        if (TextUtils.isEmpty(message)) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = dialog.findViewById(R.id.message);
            txt.setText(message);
        }

        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelableBack);
        dialog.setOnCancelListener(cancelListener);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;

        dialog.getWindow().setAttributes(lp);
        dialog.show();

        return dialog;
    }

    public static void dismissDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        }
    }

    public static boolean isShow() {
        if (dialog != null && dialog.isShowing()) {
            return true;
        } else
            return false;
    }

}