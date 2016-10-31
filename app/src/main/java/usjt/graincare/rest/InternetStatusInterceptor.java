package usjt.graincare.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class InternetStatusInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return null;
    }
}
