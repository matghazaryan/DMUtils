package dmutils.com.dmutils.pref;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * To start using DMPrefsCacheManager you should initialize library on Application class
 *  DMPrefsCacheManager.getInstance().initialize(getApplicationContext());
 */

public class DMPrefsCacheManager {

    private static DMPrefsCacheManager instance;
    private SharedPreferences mSharedPreferences;

    private DMPrefsCacheManager() {
    }

    public static DMPrefsCacheManager getInstance() {
        if (instance == null) {
            instance = new DMPrefsCacheManager();
        }

        return instance;
    }

    public void initialize(final Context ctxt) {
        mSharedPreferences = ctxt.getSharedPreferences(ctxt.getPackageName(), Context.MODE_PRIVATE);
    }

    public void putInCashe(final String key, final String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public void putInCashe(final String key, final int value) {
        mSharedPreferences.edit().putString(key, Integer.toString(value)).apply();
    }

    public void putInCashe(final String key, final boolean value) {
        mSharedPreferences.edit().putString(key, Boolean.toString(value)).apply();
    }

    public void putInCashe(final String key, final float value) {
        mSharedPreferences.edit().putString(key, Float.toString(value)).apply();
    }

    public void putInCashe(final String key, final double value) {
        mSharedPreferences.edit().putString(key, Double.toString(value)).apply();
    }

    public void putInCashe(final String key, final long value) {
        mSharedPreferences.edit().putString(key, Long.toString(value)).apply();
    }

    public String getStringFromCashe(final String key, final String defValue) {
        final String v = mSharedPreferences.getString(key, null);
        return v != null ? v : defValue;
    }

    public int getIntFromCashe(final String key, final int defValue) {
        final String v = mSharedPreferences.getString(key, null);
        return v != null ? Integer.parseInt(v) : defValue;
    }

    public boolean getBooleanFromCashe(final String key, final boolean defValue) {
        final String v = mSharedPreferences.getString(key, null);
        return v != null ? Boolean.parseBoolean(v) : defValue;
    }

    public float getFloatFromCache(final String key, final float defValue) {
        final String v = mSharedPreferences.getString(key, null);
        return v != null ? Float.parseFloat(v) : defValue;
    }

    public double getDoubleFromCache(final String key, final double defValue) {
        final String v = mSharedPreferences.getString(key, null);
        return v != null ? Double.parseDouble(v) : defValue;
    }

    public long getLongFromCache(final String key, final long defValue) {
        final String v = mSharedPreferences.getString(key, null);
        return v != null ? Long.parseLong(v) : defValue;
    }

    public void putInCashe(final String pKey, final List<String> pList) {
        Set<String> set = new HashSet<String>();
        set.addAll(pList);
        mSharedPreferences.edit().putStringSet(pKey, set).apply();
    }

    public Set<String> getSetStringFromCashe(final String key, final Set<String> defValue) {
        final Set<String> set = mSharedPreferences.getStringSet(key, defValue);
        return set != null ? set : defValue;
    }

    public void clearCache() {
        mSharedPreferences.edit().clear().apply();
    }

    public void removeCacheItem(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }
}
