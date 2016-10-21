package usjt.graincare.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import usjt.graincare.util.GrainCareConfig;

public class GrainCareRestGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(GrainCareConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <T> T create(Class<T> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
