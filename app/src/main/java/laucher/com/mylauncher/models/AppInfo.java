package laucher.com.mylauncher.models;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * @credit http://developer.android.com/reference/android/content/AsyncTaskLoader.html
 */
public class AppInfo {

    private final Context mContext;
    private final ApplicationInfo mInfo;

    private String mAppLabel;
    private Drawable mIconApp;
    private Drawable mIconCustom;

    private boolean mMounted;
    private final File mApkFile;

    public AppInfo(Context context, ApplicationInfo info) {
        mContext = context;
        mInfo = info;

        mApkFile = new File(info.sourceDir);
    }

    public ApplicationInfo getAppInfo() {
        return mInfo;
    }

    public String getApplicationPackageName() {
        return getAppInfo().packageName;
    }

    public String getLabel() {
        return mAppLabel;
    }

    public Drawable getIconApp() {
        if (mIconApp == null) {
            if (mApkFile.exists()) {
                mIconApp = mInfo.loadIcon(mContext.getPackageManager());
                return mIconApp;
            } else {
                mMounted = false;
            }
        } else if (!mMounted) {
            // If the app wasn't mounted but is now mounted, reload
            // its icon.
            if (mApkFile.exists()) {
                mMounted = true;
                mIconApp = mInfo.loadIcon(mContext.getPackageManager());
                return mIconApp;
            }
        } else {
            return mIconApp;
        }

        return mContext.getResources().getDrawable(android.R.drawable.sym_def_app_icon);
    }

    public Drawable getIconCustom() {
        return mIconCustom;
    }


    void loadLabel(Context context) {
        if (mAppLabel == null || !mMounted) {
            if (!mApkFile.exists()) {
                mMounted = false;
                mAppLabel = mInfo.packageName;
            } else {
                mMounted = true;
                CharSequence label = mInfo.loadLabel(context.getPackageManager());
                mAppLabel = label != null ? label.toString() : mInfo.packageName;
            }
        }
    }
}
