package dmutils.com.dmutils.general;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DMAppInfo {

    public static String getApplicationName(final Context context) {
        final ApplicationInfo applicationInfo = context.getApplicationInfo();
        final int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    public static int getAppVersionCode(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getAppVersionName(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDeviceDensity(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        if (density == 1.0) {
            return "1";
        } else if (density == 1.5 || density == 2.0) {
            return "2";
        } else if (density == 3.0) {
            return "3";
        } else if (density == 4.0) {
            return "4";
        } else {
            return "2";
        }
    }

    @SuppressLint("HardwareIds")
    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
