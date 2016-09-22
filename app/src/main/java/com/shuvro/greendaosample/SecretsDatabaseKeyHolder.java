package com.shuvro.greendaosample;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Author:Mithun Sarker Shuvro
 */
public class SecretsDatabaseKeyHolder {

    private static final String LOG_TAG = "KeyHolder";

    private static final String PREFS_NAME = "default";

    private static final String KEY_DB_KEY = "db_key";

    private final SharedPreferences prefs;

    public SecretsDatabaseKeyHolder(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getKey() {
        String key = prefs.getString(KEY_DB_KEY, null);
        if (key == null) {
            key = SecretsDatabaseKeyGenerator.generateKey();
            prefs.edit().putString(KEY_DB_KEY, key).apply();
        }
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "DB key: " + key);
        }
        return key;
    }
}
