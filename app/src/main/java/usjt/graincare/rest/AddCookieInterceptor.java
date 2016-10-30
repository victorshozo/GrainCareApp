package usjt.graincare.rest;

import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import usjt.graincare.util.Preferences;

import static usjt.graincare.util.Preferences.COOKIES;

public class AddCookieInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> preferences = Preferences.getStringSet(COOKIES, new HashSet<String>());

        String finalCookie = "";

        for (String cookie : preferences) {
            finalCookie += cookie + "; ";
        }

        builder.addHeader("Cookie", finalCookie);

        Log.v("OkHttp", "Adding Header: " + finalCookie);
        return chain.proceed(builder.build());
    }
}
