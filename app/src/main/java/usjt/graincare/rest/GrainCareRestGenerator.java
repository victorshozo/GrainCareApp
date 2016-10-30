package usjt.graincare.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import usjt.graincare.util.GrainCareConfig;

public class GrainCareRestGenerator {

    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static ReceivedCookieInterceptor receivedCookie = new ReceivedCookieInterceptor();
    private static AddCookieInterceptor addCookie = new AddCookieInterceptor();
    private static InternetStatusInterceptor internetStatus = new InternetStatusInterceptor();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(GrainCareConfig.BASE_URL)
                    .addConverterFactory(buildGsonConverter());

    public static <T> T create(Class<T> serviceClass) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(receivedCookie);
        httpClient.addInterceptor(addCookie);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    private static Converter.Factory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Calendar.class, new CalendarDeserializer());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }
}
