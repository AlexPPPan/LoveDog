package com.homework.lovedog.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homework.lovedog.anotation.ResultCode;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public abstract class BaseFragment extends Fragment implements android.view.View.OnClickListener,
    BaseActivity.ActivityResultCallBack {

    public static final int RESULT_OK = Activity.RESULT_OK;
    public static final int RESULT_CANCELED = Activity.RESULT_CANCELED;
    public static final int REQUEST_CODE_INVALID = BaseActivity.REQUEST_CODE_INVALID;


    protected BaseActivity mActivity;
    public View rootView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initMain();
        if (rootView == null) {
            rootView = initView(inflater, container);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    protected abstract void initMain();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initMain();
        mActivity.setActivityResultCallBack(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityFragmentResult(int requestCode, int resultCode, Intent data) {

    }

    protected void goBackHome() {
        clearBackStack();
        finish();
    }


    protected void clearBackStack() {
        mActivity.clearBackStack();
    }

    protected BaseFragment getPreFragment() {
        return mActivity.getPreFragment();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }


    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = getActivity().getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getActivity().getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    @Override
    public void onClick(View view) {

    }


    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as
     * calling its empty
     * constructor.
     *
     * @param context       context.
     * @param fragmentClass class of fragment.
     * @param <T>           subclass of {@link BaseFragment}.
     * @return new instance.
     * @deprecated In {@code Activity} with {@link BaseActivity#fragment(Class)} instead;
     * in the {@code Fragment} width {@link #fragment(Class)} instead.
     */
    @Deprecated
    public static <T extends BaseFragment> T instantiate(Context context,
                                                         Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) instantiate(context, fragmentClass.getName(), null);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as
     * calling its empty
     * constructor.
     *
     * @param context       context.
     * @param fragmentClass class of fragment.
     * @param bundle        argument.
     * @param <T>           subclass of {@link BaseFragment}.
     * @return new instance.
     * @deprecated In {@code Activity} with {@link BaseActivity#fragment(Class, Bundle)}
     * instead;
     * in the {@code Fragment} width {@link #fragment(Class, Bundle)} instead.
     */
    @Deprecated
    public static <T extends BaseFragment> T instantiate(Context context,
                                                         Class<T> fragmentClass,
                                                         Bundle bundle) {
        //noinspection unchecked
        return (T) instantiate(context, fragmentClass.getName(), bundle);
    }

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as
     * calling its empty
     * constructor.
     *
     * @param fragmentClass class of fragment.
     * @param <T>           subclass of {@link BaseFragment}.
     * @return new instance.
     */
    public final <T extends BaseFragment> T fragment(Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) instantiate(getContext(), fragmentClass.getName(), null);
    }

    /**
     * 初始化页面
     *
     * @param inflater  inflater
     * @param container ViewGroup
     * @return View
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * Create a new instance of a Fragment with the given class name.  This is the same as
     * calling its empty
     * constructor.
     *
     * @param fragmentClass class of fragment.
     * @param bundle        argument.
     * @param <T>           subclass of {@link BaseFragment}.
     * @return new instance.
     */
    public final <T extends BaseFragment> T fragment(Class<T> fragmentClass, Bundle bundle) {
        //noinspection unchecked
        return (T) instantiate(getContext(), fragmentClass.getName(), bundle);
    }


    /**
     * Get BaseActivity.
     *
     * @return {@link BaseActivity}.
     */
    protected final BaseActivity getCompatActivity() {
        return mActivity;
    }

    /**
     * Start activity.
     *
     * @param clazz class for activity.
     * @param <T>   {@link Activity}.
     */
    protected final <T extends Activity> void startActivity(Class<T> clazz) {
        startActivity(new Intent(mActivity, clazz));
    }

    /**
     * Start activity and finish my parent.
     *
     * @param clazz class for activity.
     * @param <T>   {@link Activity}.
     */
    protected final <T extends Activity> void startActivityFinish(Class<T> clazz) {
        startActivity(new Intent(mActivity, clazz));
        mActivity.finish();
    }


    /**
     * Destroy me.
     */
    public void finish() {
        mActivity.onBackPressed();
    }

    // ------------------------- Stack ------------------------- //

    /**
     * Stack info.
     */
    private BaseActivity.FragmentStackEntity mStackEntity;

    /**
     * Set result.
     *
     * @param resultCode result code, one of {@link BaseFragment#RESULT_OK},
     *                   {@link BaseActivity#RESULT_CANCELED}.
     */
    protected final void setResult(@ResultCode int resultCode) {
        mStackEntity.resultCode = resultCode;
    }

    /**
     * Set result.
     *
     * @param resultCode resultCode, use {@link }.
     * @param result     {@link Bundle}.
     */
    protected final void setResult(@ResultCode int resultCode, @NonNull Bundle result) {
        mStackEntity.resultCode = resultCode;
        mStackEntity.result = result;
    }

    /**
     * Get the resultCode for requestCode.
     */
    final void setStackEntity(@NonNull BaseActivity.FragmentStackEntity stackEntity) {
        this.mStackEntity = stackEntity;
    }

    /**
     * You should override it.
     *
     * @param resultCode resultCode.
     * @param result     {@link Bundle}.
     */
    public void onFragmentResult(int requestCode, @ResultCode int resultCode,
                                 @Nullable Bundle result) {
    }

    /**
     * Show a fragment.
     *
     * @param clazz fragment class.
     * @param <T>   {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(Class<T> clazz) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, true, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz fragment class.
     * @param <T>   {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                             Class<T> clazz) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(fragmentLayoutId, targetFragment, true, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz       fragment class.
     * @param stickyStack sticky to back stack.
     * @param <T>         {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(Class<T> clazz,
                                                             boolean stickyStack) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, stickyStack, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz fragment class.
     * @param <T>   {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                             Class<T> clazz, boolean
                                                                 stickyStack) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(fragmentLayoutId, targetFragment, stickyStack, REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param <T>            {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(T targetFragment) {
        startFragment(targetFragment, true, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param <T>            {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                             T targetFragment) {
        startFragment(fragmentLayoutId, targetFragment, true, REQUEST_CODE_INVALID);
    }


    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack. 新打开的Fragment是否入栈
     * @param <T>            {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(T targetFragment,
                                                             boolean stickyStack) {
        startFragment(targetFragment, stickyStack, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param <T>            {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                             T targetFragment,
                                                             boolean stickyStack) {
        startFragment(fragmentLayoutId, targetFragment, stickyStack, REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment for result.
     *
     * @param clazz       fragment to display.
     * @param requestCode requestCode.
     * @param <T>         {@link BaseFragment}.
     * @deprecated use {@link #startFragmentForResult(Class, int)} instead.
     */
    @Deprecated
    public final <T extends BaseFragment> void startFragmentForRequest(Class<T> clazz,
                                                                       int requestCode) {
        startFragmentForResult(clazz, requestCode);
    }

    /**
     * Show a fragment for result.
     *
     * @param targetFragment fragment to display.
     * @param requestCode    requestCode.
     * @param <T>            {@link BaseFragment}.
     * @deprecated use {@link #startFragmentForResult(Class, int)} instead.
     */
    @Deprecated
    public final <T extends BaseFragment> void startFragmentForRequest(T targetFragment,
                                                                       int requestCode) {
        startFragmentForResult(targetFragment, requestCode);
    }

    /**
     * Show a fragment for result.
     *
     * @param clazz       fragment to display.
     * @param requestCode requestCode.
     * @param <T>         {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragmentForResult(Class<T> clazz,
                                                                      int requestCode) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(targetFragment, true, requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment for result.
     *
     * @param targetFragment fragment to display.
     * @param requestCode    requestCode.
     * @param <T>            {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragmentForResult(T targetFragment,
                                                                      int requestCode) {
        startFragment(targetFragment, true, requestCode);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.新打开的Fragment是否入栈
     * @param requestCode    requestCode.
     * @param <T>            {@link BaseFragment}.
     */
    private <T extends BaseFragment> void startFragment(T targetFragment, boolean stickyStack
        , int requestCode) {
        mActivity.startFragment(this, targetFragment, stickyStack, requestCode);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.
     * @param requestCode    requestCode.
     * @param <T>            {@link BaseFragment}.
     */
    private <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                        T targetFragment,
                                                        boolean stickyStack,
                                                        int requestCode) {
        mActivity.startFragment(fragmentLayoutId, this, targetFragment, stickyStack, requestCode);
    }


}
