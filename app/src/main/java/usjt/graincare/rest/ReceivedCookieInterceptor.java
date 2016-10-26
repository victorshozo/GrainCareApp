package usjt.graincare.rest;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;
import usjt.graincare.util.Preferences;

import static usjt.graincare.util.Preferences.COOKIES;

public class ReceivedCookieInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            Preferences.putStringSet(COOKIES, cookies);
        }

        return originalResponse;
    }
}
