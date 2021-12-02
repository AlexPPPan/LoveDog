package com.homework.lovedog.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.homework.lovedog.anotation.ResultCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public abstract class BaseActivity  extends FragmentActivity implements View.OnClickListener {

    //protected static final int NORMAL_FRAGMENT_LAYOUT = R.id.scan_view_content_rl;
    //protected static final int H5_REPORT_FRAGMENT_LAYOUT = R.id.scan_view_content_rl;

    protected static Dialog mAnalyseDialog = null;
    public static final int REQUEST_CODE_INVALID = -1;
    private FragmentManager mFManager;
    private final AtomicInteger mAtomicInteger = new AtomicInteger();
    private final List<BaseFragment> mFragmentStack = new ArrayList<>();
    private final Map<BaseFragment, FragmentStackEntity> mFragmentEntityMap = new HashMap<>();

    static class FragmentStackEntity {
        private FragmentStackEntity() {
        }

        private boolean isSticky = false;
        private int requestCode = REQUEST_CODE_INVALID;
        @ResultCode
        int resultCode = BaseFragment.RESULT_CANCELED;
        Bundle result = null;
    }

    public final <T extends BaseFragment> T fragment(Class<T> fragmentClass) {
        //noinspection unchecked
        return (T) Fragment.instantiate(this, fragmentClass.getName());
    }

    public final <T extends BaseFragment> T fragment(Class<T> fragmentClass, Bundle bundle) {
        //noinspection unchecked
        return (T) Fragment.instantiate(this, fragmentClass.getName(), bundle);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);

        mFManager = getSupportFragmentManager();
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    public void hideNavigationBar() {
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
            | View.SYSTEM_UI_FLAG_FULLSCREEN// hide status bar
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_IMMERSIVE;
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            uiFlags |= 0x00001000;    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars -
            // compatibility:
            // building API level is lower thatn 19, use magic number directly for higher API
            // target level
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

      getWindow().getDecorView().setSystemUiVisibility(uiFlags);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        getWindow().setAttributes(params);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mActivityResultCallBack != null) {
            mActivityResultCallBack.onActivityFragmentResult(requestCode, resultCode, data);
        }

    }

    public ActivityResultCallBack mActivityResultCallBack;

    public void setActivityResultCallBack(ActivityResultCallBack activityResultCallBack) {
        this.mActivityResultCallBack = activityResultCallBack;
    }

    public interface ActivityResultCallBack {
        void onActivityFragmentResult(int requestCode, int resultCode, Intent data);
    }




    /**
     * Show a fragment.
     *
     * @param clazz fragment class.
     */
    public final <T extends BaseFragment> void startFragment(Class<T> clazz) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(null, targetFragment, true, BaseFragment.REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz fragment class.
     */
    public final <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                                 Class<T> clazz) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(fragmentLayoutId, null, targetFragment, true,
                BaseFragment.REQUEST_CODE_INVALID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Show a fragment.
     *
     * @param clazz       fragment class.
     * @param stickyStack sticky to back stack.
     */
    public final <T extends BaseFragment> void startFragment(Class<T> clazz,
                                                                 boolean stickyStack) {
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(null, targetFragment, stickyStack, BaseFragment.REQUEST_CODE_INVALID);
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
        startFragment(null, targetFragment, true, BaseFragment.REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment.
     *
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.
     * @param <T>            {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(T targetFragment,
                                                                 boolean stickyStack) {
        startFragment(null, targetFragment, stickyStack, BaseFragment.REQUEST_CODE_INVALID);
    }

    /**
     * Show a fragment.
     *
     * @param fragmentLayoutId Layout id for Fragment
     * @param targetFragment   fragment to display.
     * @param stickyStack      sticky back stack.
     * @param <T>              {@link BaseFragment}.
     */
    public final <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                                 T targetFragment,
                                                                 boolean stickyStack) {
        startFragment(fragmentLayoutId, null, targetFragment, stickyStack,
            BaseFragment.REQUEST_CODE_INVALID);
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
    public final <T extends BaseFragment> void startFragmentForResquest(Class<T> clazz,
                                                                            int requestCode) {
        startFragmentForResult(clazz, requestCode);
    }

    /**
     * Show a fragment for result.
     *
     * @param targetFragment fragment to display.
     * @param requestCode    requestCode.
     * @param <T>            {@link BaseFragment}.
     * @deprecated use {@link #startFragmentForResult(BaseFragment, int)} instead.
     */
    @Deprecated
    public final <T extends BaseFragment> void startFragmentForResquest(T targetFragment,
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
        if (requestCode == REQUEST_CODE_INVALID)
            throw new IllegalArgumentException("The requestCode must be positive integer.");
        try {
            BaseFragment targetFragment = clazz.newInstance();
            startFragment(null, targetFragment, true, requestCode);
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
        if (requestCode == REQUEST_CODE_INVALID)
            throw new IllegalArgumentException("The requestCode must be positive integer.");
        startFragment(null, targetFragment, true, requestCode);
    }

    /**
     * Show a fragment.
     *
     * @param nowFragment    Now show fragment, can be null.
     * @param targetFragment fragment to display.
     * @param stickyStack    sticky back stack.新打开的Fragment是否入栈
     * @param requestCode    requestCode.
     * @param <T>            {@link BaseFragment}.
     */
    protected final <T extends BaseFragment> void startFragment(T nowFragment,
                                                                    T targetFragment,
                                                                    boolean stickyStack,
                                                                    int requestCode) {
        startFragment(fragmentLayoutId(), nowFragment, targetFragment, stickyStack, requestCode);
    }


    private int mFragmentLayoutId;

    /**
     * Show a fragment.
     *
     * @param fragmentLayoutId Layout id for Fragment
     * @param nowFragment      Now show fragment, can be null.
     * @param targetFragment   fragment to display.
     * @param stickyStack      sticky back stack.新打开的Fragment是否入栈
     * @param requestCode      requestCode.
     * @param <T>              {@link BaseFragment}.
     */
    protected final <T extends BaseFragment> void startFragment(@IdRes int fragmentLayoutId,
                                                                    T nowFragment,
                                                                    T targetFragment,
                                                                    boolean stickyStack,
                                                                    int requestCode) {

        FragmentStackEntity fragmentStackEntity = new FragmentStackEntity();
        fragmentStackEntity.isSticky = stickyStack;
        fragmentStackEntity.requestCode = requestCode;
        targetFragment.setStackEntity(fragmentStackEntity);
        if (mFragmentStack != null) {
            for (BaseFragment fragment : mFragmentStack) {
                mFManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
            }
        }
        FragmentTransaction fragmentTransaction = mFManager.beginTransaction();
        if (nowFragment != null) {
            if (mFragmentEntityMap.get(nowFragment)!=null&&mFragmentEntityMap.get(nowFragment).isSticky) {
                nowFragment.onPause();
                nowFragment.onStop();
                fragmentTransaction.hide(nowFragment);
            } else {
                nowFragment.onPause();
                nowFragment.onStop();
                fragmentTransaction.remove(nowFragment);
                fragmentTransaction.commitAllowingStateLoss();
                mFragmentStack.remove(nowFragment);
                mFragmentEntityMap.remove(nowFragment);
                fragmentTransaction = mFManager.beginTransaction();
            }

        }

        String fragmentTag =
            targetFragment.getClass().getSimpleName() + mAtomicInteger.incrementAndGet();
        //   fragmentTransaction.add(fragmentLayoutId, targetFragment, fragmentTag);
        fragmentTransaction.replace(fragmentLayoutId, targetFragment);

        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commitAllowingStateLoss();

        mFragmentStack.add(targetFragment);
        mFragmentEntityMap.put(targetFragment, fragmentStackEntity);
    }

    /**
     * When the back off.
     */
    protected final boolean onBackStackFragment() {


        if (mFragmentStack.size() > 1) {
            //避免出现java.lang.IllegalStateException: Can not perform this action after onSaveInstance
            try {
//              mFManager.popBackStack();
                mFManager.popBackStackImmediate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BaseFragment inFragment = mFragmentStack.get(mFragmentStack.size() - 2);

            if (mFragmentStack != null) {
                for (BaseFragment fragment : mFragmentStack) {
                    mFManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
                }
            }
            FragmentTransaction fragmentTransaction = mFManager.beginTransaction();
            fragmentTransaction.show(inFragment);
//            fragmentTransaction.replace(mFragmentLayoutId,inFragment);
            fragmentTransaction.commitAllowingStateLoss();

            BaseFragment outFragment = mFragmentStack.get(mFragmentStack.size() - 1);
            inFragment.onResume();

            FragmentStackEntity stackEntity = mFragmentEntityMap.get(outFragment);
            mFragmentStack.remove(outFragment);
            mFragmentEntityMap.remove(outFragment);

            if (stackEntity.requestCode != BaseFragment.REQUEST_CODE_INVALID) {
                inFragment.onFragmentResult(stackEntity.requestCode, stackEntity.resultCode,
                    stackEntity.result);
            }
            return true;
        }
        return false;
    }

    /**
     * When back home.
     */
    protected final boolean clearBackStack() {

        if (mFragmentStack.size() > 0) {
            while (mFragmentStack.size() > 1) {
                //避免出现java.lang.IllegalStateException: Can not perform this action after
                // onSaveInstance
                try {

//                  mFManager.popBackStack();
                    mFManager.popBackStackImmediate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BaseFragment outFragment = mFragmentStack.get(mFragmentStack.size() - 1);
                FragmentTransaction fragmentTransaction1 = mFManager.beginTransaction();
                fragmentTransaction1.remove(outFragment);
                fragmentTransaction1.commitAllowingStateLoss();
                mFManager.getFragments().remove(outFragment);
                mFragmentStack.remove(outFragment);
                mFragmentEntityMap.remove(outFragment);
            }
            BaseFragment inFragment = mFragmentStack.get(0);
            FragmentTransaction fragmentTransaction = mFManager.beginTransaction();
            fragmentTransaction.show(inFragment);
            fragmentTransaction.commitAllowingStateLoss();
            inFragment.onResume();
            return true;
        }

        return false;
    }

    protected BaseFragment getPreFragment() {
        BaseFragment scanBaseFragment = null;
        if (mFragmentStack.size() > 1) {
            scanBaseFragment = mFragmentStack.get(mFragmentStack.size() - 2);
        }
        return scanBaseFragment;
    }

    private boolean mBackPressDisable;

    protected void setBackPressDisable() {
        mBackPressDisable = true;
    }

    private long firstTime;

    public boolean isCanBack = true;

    public void setCanBack(boolean canBack) {
        this.isCanBack = canBack;
    }

    @Override
    public void onBackPressed() {
            onBackStackFragment();
    }

    /**
     * Should be returned to display fragments id of {@link android.view.ViewGroup}.
     *
     * @return resource id of {@link android.view.ViewGroup}.
     */
    protected abstract
    @IdRes
    int fragmentLayoutId();



}
