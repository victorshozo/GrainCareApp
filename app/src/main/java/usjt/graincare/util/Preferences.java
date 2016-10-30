package usjt.graincare.util;

import android.app.Activity;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {

    public static final String COOKIES = "PREF_COOKIES";

    private static Activity activity;

    public static void putStringSet(String key, Set<String> hashValues) {
        activity.getPreferences(MODE_PRIVATE).edit().putStringSet(key, hashValues).apply();
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValues) {
        return activity.getPreferences(MODE_PRIVATE).getStringSet(key, defaultValues);
    }

    public static void setActivity(Activity activity) {
        Preferences.activity = activity;
    }
}
